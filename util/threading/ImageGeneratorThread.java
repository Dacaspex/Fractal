package util.threading;

import java.awt.image.BufferedImage;

import fractals.AbstractFractal;
import fractals.FractalManager;
import fractals.Scale;

public class ImageGeneratorThread extends Thread {

	private int width;
	private int height;
	private int number;

	private Scale scale;

	private AbstractFractal fractal;

	public static FractalManager fractalManager;

	public ImageGeneratorThread(Scale scale, int width, int height, AbstractFractal fractal, int number) {

		this.scale = scale;
		this.width = width;
		this.height = height;
		this.fractal = fractal;
		this.number = number;

	}

	public void run() {

		BufferedImage image = fractal.generateImage(width, height, scale);
		finilize(image);

	}

	private void finilize(BufferedImage image) {

		synchronized (fractalManager) {

			fractalManager.updateProgress(image, number);

		}

		this.interrupt();

	}

}
