package fractals.threading;

import fractals.AbstractFractal;
import fractals.Scale;

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

		int partialWidth = (int) (width / Math.sqrt(numberOfThreads));
		int partialHeight = (int) (height / Math.sqrt(numberOfThreads));

		double xScaleStep = (scale.getxMax() - scale.getxMin()) / Math.sqrt(numberOfThreads);
		double yScaleStep = (scale.getyMax() - scale.getyMin()) / Math.sqrt(numberOfThreads);
		
		int xFactor;
		int yFactor;

		for (int i = 0; i < numberOfThreads; i++) {

			xFactor = Math.floorMod(i, (int) Math.sqrt(numberOfThreads));
			yFactor = Math.floorDiv(i, (int) Math.sqrt(numberOfThreads));

			Scale _scale = new Scale(scale.getxMin() + xFactor * xScaleStep,
					scale.getxMin() + (xFactor + 1) * xScaleStep, scale.getyMin() + yFactor * yScaleStep,
					scale.getyMin() + (yFactor + 1) * yScaleStep);

			thread = new ImageGeneratorThread(_scale, partialWidth, partialHeight, fractal, i);
			thread.start();
			threads[i] = thread;

		}

	}

	/**
	 * Removes the pointer to the thread array for memory efficiency
	 */
	public void killThreads() {

		this.threads = null;

	}

}
