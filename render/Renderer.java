package render;

import java.awt.image.BufferedImage;

import fractals.AbstractFractal;
import render.threading.ImageStitcher;
import render.threading.ThreadManager;
import util.logger.Logger;

public class Renderer {

	private ThreadManager threadManager;
	private ImageStitcher imageStitcher;
	private PostRenderer postRenderer;
	private BufferedImage[] partialImages;

	private RenderState renderState;
	private RenderListener renderListener;

	private AbstractFractal requestedFractal;
	private int requestedWidth;
	private int requestedHeight;
	
	private long renderStartTime;

	public Renderer(RenderListener renderListener) {

		this.threadManager = new ThreadManager(this);
		this.imageStitcher = new ImageStitcher();
		this.postRenderer = new PostRenderer();
		this.renderListener = renderListener;
		this.renderState = RenderState.IDLE;

		ThreadManager.setThreadCount(9); // TODO should be moved

	}

	public void render(AbstractFractal fractal, int width, int height) {

		if (renderState == RenderState.IDLE) {

			renderState = RenderState.RENDERING;

			// Save information, create and start threads
			requestedFractal = fractal;
			requestedWidth = width;
			requestedHeight = height;
			Logger.log(this, "Requested image (" + width + " x " + height + ") for " + fractal.getClass().getSimpleName());
			renderStartTime = System.currentTimeMillis();
			threadManager.createThreads(fractal, width, height);

		} else {
			return;
		}

	}

	public void finish() {

		// Get result images, stitch them together and apply post render effects
		renderState = RenderState.STITCHING;
		partialImages = threadManager.getImages();
		BufferedImage image = imageStitcher.stitch(partialImages, requestedWidth, requestedHeight);
		image = postRenderer.render(image, requestedFractal);

		Logger.log(this, "Completed render. Time: " + (System.currentTimeMillis() - renderStartTime) + "ms");
		requestedFractal = null;
		renderListener.renderFinished(image);
		renderState = RenderState.IDLE;

	}

	private enum RenderState {
		IDLE, RENDERING, STITCHING
	}

}
