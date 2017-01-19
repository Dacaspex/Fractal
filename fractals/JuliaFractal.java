package fractals;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Arrays;

import complex.Complex;
import fractals.coloring.JuliaColoring;

public class JuliaFractal implements AbstractFractal {

	private Complex constant;
	private int maxIterations;
	private double escapeValue;
	private JuliaColoring fractalColoring;

	// TODO change to fractalColoring
	Color[] colors;
	int[] colorMap;

	public JuliaFractal() {

		constant = new Complex(-0.7269, 0.1889);
		maxIterations = 512;
		escapeValue = 2.0;
		fractalColoring = new JuliaColoring();

		this.colors = new Color[] { new Color(0, 0, 0), new Color(255, 200, 0), new Color(255, 255, 255),
				new Color(0, 0, 255), new Color(0, 0, 128) };

		createColorArray();

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

	public JuliaColoring getFractalColoring() {
		return fractalColoring;
	}

	public void setFractalColoring(JuliaColoring fractalColoring) {
		this.fractalColoring = fractalColoring;
	}

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

	public void createColorArray() {

		// For naming purposes
		int steps = maxIterations;

		int colorMap[] = new int[steps];

		if (colors.length == 1) {

			Arrays.fill(colorMap, colors[0].getRGB());
			this.colorMap = colorMap;

		}

		double colorDelta = 1.0 / (colors.length - 1);
		for (int i = 0; i < steps; i++) {

			double globalRel = (double) i / (steps - 1);
			int index0 = (int) (globalRel / colorDelta);
			int index1 = Math.min(colors.length - 1, index0 + 1);
			double localRel = (globalRel - index0 * colorDelta) / colorDelta;

			Color c0 = colors[index0];
			int r0 = c0.getRed();
			int g0 = c0.getGreen();
			int b0 = c0.getBlue();
			int a0 = c0.getAlpha();

			Color c1 = colors[index1];
			int r1 = c1.getRed();
			int g1 = c1.getGreen();
			int b1 = c1.getBlue();
			int a1 = c1.getAlpha();

			int dr = r1 - r0;
			int dg = g1 - g0;
			int db = b1 - b0;
			int da = a1 - a0;

			int r = (int) (r0 + localRel * dr);
			int g = (int) (g0 + localRel * dg);
			int b = (int) (b0 + localRel * db);
			int a = (int) (a0 + localRel * da);
			int rgb = (a << 24) | (r << 16) | (g << 8) | (b << 0);
			colorMap[i] = rgb;

		}

		this.colorMap = colorMap;

	}

	public int getColorValue(int i) {

		return colorMap[maxIterations - i - 1];

	}

	public int getRGBValue(int number) {

		return getColorValue(number);

	}

}
