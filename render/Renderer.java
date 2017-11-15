package render;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import fractals.AbstractFractal;
import render.threading.ImageStitcher;
import render.threading.ThreadManager;
import util.logger.Logger;

/**
 * The renderer class for rendering images. This class contains the complete
 * procedure to render a fractal and apply the post processing effects. It
 * receives all information before sending this to the {@link ThreadManager}.
 * When the thread manager is done, the this{@link #finish()} is called. All
 * partial images of the threads are combined into the complete image.
 * 
 * All actions are logged, as well as the render time.
 * 
 * @author Casper
 *
 */
public class Renderer {

	/**
	 * Thread manager to manage the threads
	 */
	private ThreadManager threadManager;

	/**
	 * ' Image stitcher to stitch all partial images together into one image
	 */
	private ImageStitcher imageStitcher;

	/**
	 * Post renderer to apply post render effects
	 */
	private PostRenderer postRenderer;

	/**
	 * Stores the current render state
	 */
	private RenderState renderState;

	/**
	 * Stores the listener associated with this particular renderer
	 */
	private RenderListener renderListener;

	/**
	 * Variable to store the fractal that was requested to render
	 */
	private AbstractFractal requestedFractal;

	/**
	 * Variable to store the dimension that was requested
	 */
	private Dimension requestedDimension;

	/**
	 * Stores the start time of the render, for logging purposes
	 */
	private long renderStartTime;

	public Renderer(RenderListener renderListener) {

		this.threadManager = new ThreadManager(this);
		this.imageStitcher = new ImageStitcher();
		this.postRenderer = new PostRenderer();
		this.renderListener = renderListener;
		this.renderState = RenderState.IDLE;

		// Init the thread count
		ThreadManager.setThreadCount(9);

	}

	/**
	 * Request to render a new fractal image with the desired with and height if
	 * the renderer is idle. This method instructs the {@link ThreadManager} to
	 * create the worker threads and start rendering.
	 * 
	 * The request is denied if the renderer was already busy rendering another
	 * image. WHen the render is done, the {@link #finish()} method is called.
	 * 
	 * @param fractal
	 *            The desired fractal to render
	 * @param width
	 *            The desired width
	 * @param height
	 *            The desired height
	 * @return True if the request is accepted, false otherwise
	 */
	public boolean render(AbstractFractal fractal, int width, int height) {

		if (renderState != RenderState.IDLE) {

			// Stop current render
			synchronized (threadManager) {
				threadManager.stopThreads();
			}
			
			renderState = RenderState.IDLE;
			return false;
			
		}
		
		renderState = RenderState.RENDERING;

		// Save information, create and start threads
		requestedFractal = fractal;
		requestedDimension = new Dimension(width, height);
		Logger.log(this,
				"Requested image (" + width + " x " + height + ") for " + fractal.getClass().getSimpleName());
		renderStartTime = System.currentTimeMillis();
		threadManager.createThreads(fractal, width, height);
		return true;

	}

	/**
	 * WHen the threads are done rendering, this method is called. It gathers
	 * all partial images and stitches them together by means of the
	 * {@link ImageStitcher}. If requested, post render effects are added by the
	 * {@link PostRenderer}. Afterwards, the
	 * {@link RenderListener#renderFinished(BufferedImage)} is called.
	 */
	public void finish() {

		// Get result images, stitch them together and apply post render effects
		renderState = RenderState.STITCHING;
		BufferedImage[] partialImages = threadManager.getImages();
		BufferedImage image = imageStitcher.stitch(partialImages, requestedDimension.width, requestedDimension.height);
		image = postRenderer.render(image, requestedFractal);

		// Finish and log result
		Logger.log(this, "Completed render. Time: " + (System.currentTimeMillis() - renderStartTime) + "ms");
		requestedFractal = null;
		renderListener.renderFinished(image);
		renderState = RenderState.IDLE;

	}

	/**
	 * The render state
	 */
	private enum RenderState {
		IDLE, RENDERING, STITCHING
	}

}
