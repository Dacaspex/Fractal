package fractals.threads;

import java.awt.image.BufferedImage;

import complex.Complex;
import fractals.JuliaFractal;
import fractals.Scale;

public class Worker extends Thread {
	
	private Scale scale;
	private BufferedImage image;
	
	private int width;
	private int height;
	
	private JuliaFractal fractal;
	
	private Complex lastEscapeComplexValue;
	
	public Worker(Scale scale, int width, int height, BufferedImage image, JuliaFractal fractal) {
		
		this.scale = scale;
		this.image = image;
		this.width = width;
		this.height = height;
		this.fractal = fractal;
		
		lastEscapeComplexValue = new Complex();
		
	}
	
	public void run() {
		
		double xTransformFactor = ((scale.getxDifference()) / (double) (width - 1));
		double yTransformFactor = ((scale.getyDifference()) / (double) (height - 1));

		for (double i = 0; i < width; i++) {

			for (double j = 0; j < height; j++) {
				
				double x = scale.getxMin() + j * xTransformFactor;
				double y = scale.getyMin() + i * yTransformFactor;

				int escapeNumber = fractal.getEscapeNumber(new Complex(x, y));
				
				double continuousIndex = escapeNumber + 1
						- (Math.log10(2) / lastEscapeComplexValue.getModulus()) / Math.log10(2);
				
				int colorValue = fractal.getRGBValue(continuousIndex);
				image.setRGB((int) j, (int) i, colorValue);

			}

		}
		
	}

}
