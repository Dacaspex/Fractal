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
//		constant = new Complex(0.285, 0.01);
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
				int colorValue = getColor(escapeNumber);
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

	@Override
	public int getRGBValue(int referenceNumber) {

		return juliaColoring.getRGBValue(referenceNumber);

	}
	
	public int getColor(int referenceNumber) {
		
		double continuousIndex = referenceNumber + 1 - (Math.log10(2) / lastEscapeComplexValue.getMagnitude()) / Math.log10(2);
		
		int r = (int) Math.abs((Math.sin(0.016 * continuousIndex + 4) * 230 + 25));
		int g = (int) Math.abs((Math.sin(0.013 * continuousIndex + 2) * 230 + 25));
		int b = (int) Math.abs((Math.sin(0.01 * continuousIndex + 1) * 230 + 25));
		
//		System.out.println("r: " + r + ", g: " + g + ", b: " + b);
		
		return new Color(r, g, b).getRGB();
		
	}
}
