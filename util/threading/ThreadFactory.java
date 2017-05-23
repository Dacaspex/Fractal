package util.threading;

import java.util.Arrays;

import fractals.AbstractFractal;
import util.math.Point;
import util.math.Scale;

public class ThreadFactory {

	private int numberOfThreads;
	private ImageGeneratorThread[] threads;

	public ThreadFactory(int numberOfThreads) {

		this.numberOfThreads = numberOfThreads;

	}

	/**
	 * Create a pool of threads that will execute the task of drawing the
	 * fractal with a given scale. It subdivides the work into the given number
	 * of threads
	 * 
	 * @param scale
	 *            The scale to use for the fractal
	 * @param width
	 *            The width of the requested image
	 * @param height
	 *            The height of the requested image
	 * @param fractal
	 *            The fractal to generate
	 */
	public void createThreads(Scale scale, int width, int height, AbstractFractal fractal) {

		// Setup thread array
		threads = new ImageGeneratorThread[numberOfThreads];
		ImageGeneratorThread thread;
		Point[][] points = scale.getPointsOnScreen(width, height);
		
		System.out.println(width);

		for (int i = 0; i < numberOfThreads; i++) {
			
			Point[][] partialPoints = Arrays.copyOfRange(points, (width / numberOfThreads) * i,
					Math.min(points.length, (width / numberOfThreads) * (i + 1)));
			
			thread = new ImageGeneratorThread(partialPoints, width / numberOfThreads, height, fractal, i);
			threads[i] = thread;
			thread.start();

		}

	}

	/**
	 * Removes the pointer to the thread array for memory efficiency
	 */
	public void killThreads() {

		this.threads = null;

	}

}
