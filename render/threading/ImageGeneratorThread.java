package render.threading;

import java.awt.image.BufferedImage;

import fractals.AbstractFractal;
import fractals.FractalManager;
import util.math.Point;

public class ImageGeneratorThread extends Thread {

	private int width;
	private int height;
	private int number;

	private Point[][] points;
	private AbstractFractal fractal;

	public static FractalManager fractalManager;

	public ImageGeneratorThread(Point[][] points, int width, int height, AbstractFractal fractal, int number) {

		this.points = points;
		this.width = width;
		this.height = height;
		this.fractal = fractal;
		this.number = number;

	}

	public void run() {

		BufferedImage image = fractal.generateImage(width, height, points);
		finilize(image);

	}

	private void finilize(BufferedImage image) {

		synchronized (fractalManager) {

			fractalManager.updateProgress(image, number);

		}

		this.interrupt();

	}

}
