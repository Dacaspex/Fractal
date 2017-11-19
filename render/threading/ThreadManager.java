package render.threading;

import java.awt.image.BufferedImage;
import java.util.Arrays;

import fractals.AbstractFractal;
import render.Renderer;
import util.math.Point;

public class ThreadManager {

	private static int threadCount;

	private Renderer renderer;
	private int threadsDone;
	private ImageGeneratorThread[] threads;
	private BufferedImage[] images;

	private ThreadState state;

	public ThreadManager(Renderer renderer) {

		this.renderer = renderer;
		this.state = ThreadState.WAITING;

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

		state = ThreadState.RUNNING;

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

		if (state == ThreadState.STOPPING) {
			return;
		}

		images[number] = image;
		threadsDone += 1;

		if (isDone()) {
			renderer.finish();
		}

	}

	public BufferedImage[] getImages() {
		return images;
	}

	public void stopThreads() {

		state = ThreadState.STOPPING;

		for (ImageGeneratorThread thread : threads) {
			thread.interrupt();
		}

	}

	public static void setThreadCount(int threadCount) {
		ThreadManager.threadCount = threadCount;
	}

	public static int getThreadCount() {
		return threadCount;
	}

	private enum ThreadState {
		RUNNING, STOPPING, WAITING
	}

}
