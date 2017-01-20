package fractals;

import java.awt.image.BufferedImage;

import complex.Complex;
import fractals.coloring.JuliaColoring;

public class JuliaFractal implements AbstractFractal {

	private Complex constant;
	private int maxIterations;
	private double escapeValue;
	private JuliaColoring juliaColoring;

	public JuliaFractal() {

		constant = new Complex(-0.7269, 0.1889);
		maxIterations = 512;
		escapeValue = 2.0;
		juliaColoring = new JuliaColoring(true, 512);

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

	@Override
	public BufferedImage getImage(Scaling scaling, int imageWidth, int imageHeight) {

		BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);

		double xTransformFactor = ((scaling.getxDifference()) / (double) (imageWidth - 1));
		double yTransformFactor = ((scaling.getyDifference()) / (double) (imageHeight - 1));

		for (double i = 0; i < imageHeight; i++) {

			for (double j = 0; j < imageWidth; j++) {

				double x = scaling.getxMin() + j * xTransformFactor;
				double y = scaling.getyMin() + i * yTransformFactor;

				int escapeNumber = getEscapeNumber(new Complex(x, y));
				int colorValue = getRGBValue(escapeNumber);
				image.setRGB((int) j, (int) i, colorValue);

			}

		}

		return image;

	}

	public int getEscapeNumber(Complex complex) {

		Complex currentValue = complex;
		int i;

		for (i = 0; i < maxIterations - 1; i++) {

			currentValue = currentValue.power(2).add(constant);

			if (currentValue.getMagnitude() > escapeValue) {

				return i;

			}

		}

		return i;

	}

	@Override
	public int getRGBValue(int referenceNumber) {
		
		return juliaColoring.getRGBValue(referenceNumber);
		
	}
}
