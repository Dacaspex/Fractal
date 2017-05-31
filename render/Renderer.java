package render;

import java.awt.image.BufferedImage;

import fractals.AbstractFractal;
import render.threading.ImageStitcher;
import render.threading.ThreadManager;

public class Renderer {

	private ThreadManager threadManager;
	private ImageStitcher imageStitcher;
	private PostRenderer postRenderer;
	private BufferedImage[] partialImages;

	private RenderOptions[] renderOptions;
	private RenderState renderState;

	public Renderer() {

		this.threadManager = new ThreadManager();
		this.imageStitcher = new ImageStitcher();
		this.postRenderer = new PostRenderer();
		this.renderState = RenderState.IDLE;

		ThreadManager.setThreadCount(9); // TODO should be moved

	}

	public BufferedImage render(AbstractFractal fractal, int width, int height) {

		if (renderState == RenderState.IDLE) {

			renderState = RenderState.RENDERING_IMAGE;

			// Create and start thread, wait until done
			threadManager.createThreads(fractal, width, height);
			haltUntillFinished();

			// Get result images, stitch them together and apply post render
			// effects
			partialImages = threadManager.getImages();
			BufferedImage image = imageStitcher.stitch(partialImages, width, height);
			image = postRenderer.render(image, fractal);
			
			renderState = RenderState.IDLE;
			return image;

		} else {

			return null;

		}

	}

	private void haltUntillFinished() {

		while (!threadManager.isDone()) {

			// Wait until threads are done;
			// Update progress...
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	public enum RenderOptions {
		ENABLE_MULTI_THREADING
	}

	private enum RenderState {
		IDLE, RENDERING_IMAGE
	}

}
