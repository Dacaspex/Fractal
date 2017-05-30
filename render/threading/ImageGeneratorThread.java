package render.threading;

import java.awt.image.BufferedImage;

import fractals.AbstractFractal;
import fractals.FractalManager;
import util.math.Point;

public class ImageGeneratorThread extends Thread {

	private int number;
	private int width;
	private int height;
	private Point[][] points;
	private ThreadManager threadManager;
	private AbstractFractal fractal;
	private BufferedImage resultImage;

	public static FractalManager fractalManager;

	public ImageGeneratorThread(ThreadManager threadManager, Point[][] points, int width, int height,
			AbstractFractal fractal, int number) {

		this.threadManager = threadManager;
		this.points = points;
		this.width = width;
		this.height = height;
		this.fractal = fractal;
		this.number = number;

	}

	public BufferedImage getResultImage() {
		return resultImage;
	}

	public void run() {

		resultImage = fractal.generateImage(width, height, points);
		notifyDone();

	}

	private void notifyDone() {

		synchronized (threadManager) {
			threadManager.notifyDone(resultImage, number);
		}
		
		interrupt();

	}

}
