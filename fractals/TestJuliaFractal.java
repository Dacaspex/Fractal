package fractals;

import java.awt.image.BufferedImage;

import complex.Complex;
import fractals.colorSchemes.JuliaColorScheme;

public class TestJuliaFractal extends AbstractFractal {

	private Complex constant;
	private int maxIterations;
	private double escapeValue;
	private JuliaColorScheme juliaColoring;
	
	private Complex lastEscapeComplexValue;

	public TestJuliaFractal() {

		constant = new Complex(-0.7269, 0.1889);
		constant = new Complex(0.285, 0.01);
		constant = Complex.createFromCartesianForm(0.285, 0.01);
		maxIterations = 256;
		escapeValue = 2.0;
		juliaColoring = new JuliaColorScheme(true, 512);
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

	public JuliaColorScheme getJuliaColoring() {

		return juliaColoring;

	}

	public void setJuliaColoring(JuliaColorScheme juliaColoring) {

		this.juliaColoring = juliaColoring;

	}
	
	long time = System.currentTimeMillis();
	long timerCount = 0;

	@Override
	public BufferedImage getImage(Scale scaling, int imageWidth, int imageHeight) {

		BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);

		double xTransformFactor = ((scaling.getxDifference()) / (double) (imageWidth - 1));
		double yTransformFactor = ((scaling.getyDifference()) / (double) (imageHeight - 1));

		for (double i = 0; i < imageHeight; i++) {

			for (double j = 0; j < imageWidth; j++) {
				
				double x = scaling.getxMin() + j * xTransformFactor;
				double y = scaling.getyMin() + i * yTransformFactor;

				long time = System.currentTimeMillis();
				
				int escapeNumber = getEscapeNumber(Complex.createFromCartesianForm(x, y));
				
				this.time += System.currentTimeMillis() - time;
				timerCount++;
				
				double continuousIndex = escapeNumber + 1
						- (Math.log10(2) / lastEscapeComplexValue.getModulus()) / Math.log10(2);
				
				int colorValue = getRGBValue(continuousIndex);
				image.setRGB((int) j, (int) i, colorValue);

			}

		}
		
		System.out.println("average escape number time: ");
		System.out.println(this.time / timerCount);

		return image;

	}

	public int getEscapeNumber(Complex complex) {

		Complex currentValue = complex;
		int i;

		for (i = 0; i < maxIterations - 1 && currentValue.getModulus() < escapeValue; i++) {

			currentValue = currentValue.power(2).add(constant);

		}
		
		lastEscapeComplexValue = currentValue;

		return i;

	}

	public int getRGBValue(double continuousIndex) {

		return juliaColoring.getRGBValue(continuousIndex);

	}
}
