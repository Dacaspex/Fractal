package fractals;

import java.awt.Color;
import java.awt.image.BufferedImage;

import complex.Complex;
import fractals.coloring.JuliaColoring;

public class JuliaFractal implements AbstractFractal {

	private Complex constant;
	private int maxIterations;
	private double escapeValue;
	private JuliaColoring juliaColoring;
	
	private Complex lastEscapeComplexValue;

	public JuliaFractal() {

		constant = new Complex(-0.7269, 0.1889);
		constant = new Complex(0.285, 0.01);
		maxIterations = 512;
		escapeValue = 2.0;
		juliaColoring = new JuliaColoring(true, 512);
		lastEscapeComplexValue = new Complex();

	}

	public int getMaxIterations() {

		return maxIterations;

	}

	public void setMaxIterations(int maxIterations) {

		this.maxIterations = maxIterations;

	}

	public double getEscapeValue() {

		return escapeValue;

	}

	public void setEscapeValue(double escapeValue) {

		this.escapeValue = escapeValue;

	}

	public JuliaColoring getJuliaColoring() {

		return juliaColoring;

	}

	public void setJuliaColoring(JuliaColoring juliaColoring) {

		this.juliaColoring = juliaColoring;

	}

	@Override
	public BufferedImage getImage(Scale scaling, int imageWidth, int imageHeight) {

		BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);

		double xTransformFactor = ((scaling.getxDifference()) / (double) (imageWidth - 1));
		double yTransformFactor = ((scaling.getyDifference()) / (double) (imageHeight - 1));

		for (double i = 0; i < imageHeight; i++) {

			for (double j = 0; j < imageWidth; j++) {

				double x = scaling.getxMin() + j * xTransformFactor;
				double y = scaling.getyMin() + i * yTransformFactor;

				int escapeNumber = getEscapeNumber(new Complex(x, y));
				
				double continuousIndex = escapeNumber + 1
						- (Math.log10(2) / lastEscapeComplexValue.getMagnitude()) / Math.log10(2);
				
				int colorValue = getRGBValue(continuousIndex);
				image.setRGB((int) j, (int) i, colorValue);

			}

		}

		return image;

	}

	public int getEscapeNumber(Complex complex) {

		Complex currentValue = complex;
		int i;

		for (i = 0; i < maxIterations - 1 && currentValue.getMagnitude() < escapeValue; i++) {

			currentValue = currentValue.power(2).add(constant);

		}
		
		lastEscapeComplexValue = currentValue;

		return i;

	}

	public int getRGBValue(double continuousIndex) {

		return juliaColoring.getRGBValue(continuousIndex);

	}
}
