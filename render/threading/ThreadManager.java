package render.threading;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import fractals.AbstractFractal;
import util.math.Point;

public class ThreadManager {

	private static int threadCount;
	private int threadsDone;
	private ImageGeneratorThread[] threads;
	private BufferedImage[] images;

	public static void setThreadCount(int threadCount) {
		ThreadManager.threadCount = threadCount;
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
		threadsDone = 0;
		threads = new ImageGeneratorThread[threadCount];
		images = new BufferedImage[threadCount];
		ImageGeneratorThread thread;
		Point[][] points = fractal.getScale().getPointsOnScreen(width, height);
		
		for (int i = 0; i < threadCount; i++) {

			Point[][] partialPoints = Arrays.copyOfRange(points, (width / threadCount) * i,
					Math.min(points.length, (width / threadCount) * (i + 1)));

			thread = new ImageGeneratorThread(this, partialPoints, width / threadCount, height, fractal, i);
			threads[i] = thread;
			thread.start();

		}

	}
	
	public boolean isDone() {
		return (threadCount - threadsDone == 0);
	}
	
	public void notifyDone(BufferedImage image, int number) {
		images[number] = image;
		threadsDone += 1;
	}
	
	public BufferedImage[] getImages() {
		return images;
	}

	/**
	 * Removes the pointer to the thread array for memory efficiency
	 */
	public void kill() {

		this.threads = null;
		this.images = null;

	}

}
