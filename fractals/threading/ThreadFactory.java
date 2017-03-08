package fractals.threading;

import fractals.AbstractFractal;
import fractals.Scale;

public class ThreadFactory {

	private int numberOfThreads;
	private ImageGeneratorThread[] threads;
	
	public ThreadFactory(int numberOfThreads) {
		
		this.numberOfThreads = numberOfThreads;
		
	}

	public void createThreads(Scale scale, int width, int height, AbstractFractal fractal) {

		threads = new ImageGeneratorThread[numberOfThreads];
		ImageGeneratorThread thread;

		int partialWidth = (int) (width / Math.sqrt(numberOfThreads));
		int partialHeight = (int) (height / Math.sqrt(numberOfThreads));

		double xScaleStep = (scale.getxMax() - scale.getxMin()) / Math.sqrt(numberOfThreads);
		double yScaleStep = (scale.getyMax() - scale.getyMin()) / Math.sqrt(numberOfThreads);

		for (int i = 0; i < numberOfThreads; i++) {

			int xFactor = Math.floorMod(i, (int) Math.sqrt(numberOfThreads));
			int yFactor = Math.floorDiv(i, (int) Math.sqrt(numberOfThreads));

			Scale _scale = new Scale(scale.getxMin() + xFactor * xScaleStep,
					scale.getxMin() + (xFactor + 1) * xScaleStep, scale.getyMin() + yFactor * yScaleStep,
					scale.getyMin() + (yFactor + 1) * yScaleStep);

			thread = new ImageGeneratorThread(_scale, partialWidth, partialHeight, fractal, i);
			thread.start();
			threads[i] = thread;

		}

	}

	public void destroyThreads() {

		this.threads = null;

	}

}
