package render.threading;

import java.util.Arrays;

import fractals.AbstractFractal;
import util.math.Point;

public class ThreadFactory {

	private static int threadCount;
	private ImageGeneratorThread[] threads;

	public static void setThreadCount(int threadCount) {
		ThreadFactory.threadCount = threadCount;
	}

	public static int getThreadCount() {
		return threadCount;
	}

	/**
	 * Create a pool of threads that will execute the task of drawing the
	 * fractal. It subdivides the work into the given number of threads and
	 * starts up these threads.
	 * 
	 * @param fractal
	 *            The fractal to generate
	 * @param width
	 *            The width of the requested image
	 * @param height
	 *            The height of the requested image
	 * @param threadCount
	 *            The number of threads to use
	 */
	public void createThreads(AbstractFractal fractal, int width, int height) {

		// Setup thread array
		threads = new ImageGeneratorThread[threadCount];
		ImageGeneratorThread thread;
		Point[][] points = fractal.getScale().getPointsOnScreen(width, height);

		for (int i = 0; i < threadCount; i++) {

			Point[][] partialPoints = Arrays.copyOfRange(points, (width / threadCount) * i,
					Math.min(points.length, (width / threadCount) * (i + 1)));

			thread = new ImageGeneratorThread(partialPoints, width / threadCount, height, fractal, i);
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
