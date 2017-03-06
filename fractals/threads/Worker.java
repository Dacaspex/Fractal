package fractals.threads;

import java.awt.image.BufferedImage;

import fractals.AbstractFractal;
import fractals.FractalManager;
import fractals.Scale;

public class Worker extends Thread {

	private int width;
	private int height;
	private int number;

	private Scale scale;

	private AbstractFractal fractal;

	public static FractalManager fractalManager;

	public Worker(Scale scale, int width, int height, AbstractFractal fractal, int number) {

		this.scale = scale;
		this.width = width;
		this.height = height;
		this.fractal = fractal;
		this.number = number;

	}

	public void run() {

		BufferedImage image = fractal.generateImage(width, height, scale);
		notifyDone(image);

	}

	public void notifyDone(BufferedImage image) {

		fractalManager.updateProgress(image, number);
		this.interrupt();

	}

}
