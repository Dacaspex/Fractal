package render;

import java.awt.image.BufferedImage;

import fractals.AbstractFractal;
import render.threading.ThreadFactory;

public class Renderer {

	private BufferedImage[] partialImages;

	private RenderOptions[] renderOptions;
	private RenderState renderState;

	public Renderer() {

		// TODO Should be moved
		ThreadFactory.setThreadCount(9);

	}

	public BufferedImage render(AbstractFractal fractal, int width, int height) {

		if (renderState == RenderState.IDLE) {

			renderState = RenderState.RENDERING_IMAGE;
			ThreadFactory threadFactory = new ThreadFactory();

		}
		
		return null;

	}

	public enum RenderOptions {
		ENABLE_MULTI_THREADING
	}

	private enum RenderState {
		IDLE, RENDERING_IMAGE
	}

}
