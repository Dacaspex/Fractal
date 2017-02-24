package fractals.threads;

import java.awt.image.BufferedImage;

import complex.Complex;
import fractals.FractalManager;
import fractals.JuliaFractal;
import fractals.Scale;

public class Worker extends Thread {
	
	private Scale scale;
	private BufferedImage image;
	
	private int width;
	private int height;
	
	private int number;
	
	private JuliaFractal fractal;
	
	public static FractalManager fractalManager;
	
	public Worker(Scale scale, int width, int height, JuliaFractal fractal, int number) {
		
		scale.debugOut();
		
		this.scale = scale;
		this.width = width;
		this.height = height;
		this.fractal = fractal;
		this.number = number;
		
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
	}
	
	public void run() {
		
		double xTransformFactor = ((scale.getxDifference()) / (double) (width - 1));
		double yTransformFactor = ((scale.getyDifference()) / (double) (height - 1));

		for (double i = 0; i < height - 1; i++) {

			for (double j = 0; j < width - 1; j++) {
				
				double x = scale.getxMin() + j * xTransformFactor;
				double y = scale.getyMin() + i * yTransformFactor;

				int escapeNumber = fractal.getEscapeNumber(new Complex(x, y));
				
				double continuousIndex = escapeNumber;
				
				int colorValue = fractal.getRGBValue(continuousIndex);
//				System.out.println(number + ", " + j + ", " + i);
				image.setRGB((int) j, (int) i, colorValue);

			}

		}
		
		notifyDone();
		
	}
	
	public void notifyDone() {
		
		fractalManager.updateProgress(image, number);
		this.interrupt();
		
	}

}
