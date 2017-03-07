package fractals.threading;

import fractals.AbstractFractal;
import fractals.Scale;

public class ThreadFactory {

	private ImageGeneratorThread[] threads;

	public void createThreads(int amount, Scale scale, int width, int height, AbstractFractal fractal) {

		threads = new ImageGeneratorThread[amount];
		ImageGeneratorThread thread;

		int partialWidth = (int) (width / Math.sqrt(amount));
		int partialHeight = (int) (height / Math.sqrt(amount));

		double xScaleStep = (scale.getxMax() - scale.getxMin()) / Math.sqrt(amount);
		double yScaleStep = (scale.getyMax() - scale.getyMin()) / Math.sqrt(amount);

		for (int i = 0; i < amount; i++) {

			int xFactor = Math.floorMod(i, (int) Math.sqrt(amount));
			int yFactor = Math.floorDiv(i, (int) Math.sqrt(amount));

			Scale _scale = new Scale(scale.getxMin() + xFactor * xScaleStep,
					scale.getxMin() + (xFactor + 1) * xScaleStep, scale.getyMin() + yFactor * yScaleStep,
					scale.getyMin() + (yFactor + 1) * yScaleStep);
			
			_scale.debugOut();

			thread = new ImageGeneratorThread(_scale, partialWidth, partialHeight, fractal, i);
			thread.start();
			threads[i] = thread;

		}

	}

	public void destroyThreads() {

		this.threads = new ImageGeneratorThread[0];

	}

}
