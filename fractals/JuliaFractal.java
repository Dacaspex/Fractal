package fractals;

import java.awt.image.BufferedImage;

import complex.Complex;
import fractals.colorSchemes.ColorSchemeManager;
import fractals.colorSchemes.ColorSchemeManager.ColorSchemeManagerOptions;
import fractals.colorSchemes.InsideOutsideColorScheme;
import fractals.colorSchemes.LinearColorScheme;
import fractals.colorSchemes.WaveColorScheme;
import fractals.settings.JuliaSettingsManager;
import util.math.Point;
import util.math.Scale;

public class JuliaFractal extends AbstractFractal {

	private Complex constant;
	private Complex lastEscapeComplexValue;
	private int maxIterations;
	private double escapeValue;

	public JuliaFractal() {

		// Set identifier
		identifier = "JuliaSet1";

		// Set default values
		name = "Julia Set";
		constant = new Complex(0.285, 0.01);
		maxIterations = 1024;
		escapeValue = 2.0;
		scale = new Scale(new Point(0, 0));

		// Initialize managers, color schemes etc..
		lastEscapeComplexValue = new Complex();

		colorSchemeManager = new ColorSchemeManager();

		LinearColorScheme linearColorScheme = new LinearColorScheme(maxIterations);
		linearColorScheme.loadDefaultColors();
		linearColorScheme.generateGradientMap();

		InsideOutsideColorScheme insideOutsideColorScheme = new InsideOutsideColorScheme();
		insideOutsideColorScheme.setMaxIterations(maxIterations);
		insideOutsideColorScheme.setThreshold(2);

		colorSchemeManager.addColorScheme(insideOutsideColorScheme);
		colorSchemeManager.addColorScheme(linearColorScheme);
		colorSchemeManager.addColorScheme(new WaveColorScheme(), ColorSchemeManagerOptions.SET_AS_ACTIVE);
		settingsManager = new JuliaSettingsManager(this);

	}

	public int getMaxIterations() {

		return maxIterations;

	}

	public void setMaxIterations(int maxIterations) {

		this.maxIterations = maxIterations;

		// Update linear and inside-outside color schemes
		((LinearColorScheme) colorSchemeManager.getColorScheme("LinearColorScheme")).setMaxInputSteps(maxIterations);
		((LinearColorScheme) colorSchemeManager.getColorScheme("LinearColorScheme")).generateGradientMap();
		((InsideOutsideColorScheme) colorSchemeManager.getColorScheme("InsideOutsideColorScheme")).setMaxIterations(maxIterations);

	}

	public double getEscapeValue() {

		return escapeValue;

	}

	public void setEscapeValue(double escapeValue) {

		this.escapeValue = escapeValue;

	}

	public Complex getConstant() {

		return constant;

	}

	public void setConstant(Complex constant) {

		this.constant = constant;

	}

	@Override
	public BufferedImage generateImage(int width, int height, Point[][] points) {

		// Create empty image
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < width; x++) {

			for (int y = 0; y < height; y++) {

				// Get the escape number
				int escapeNumber = getEscapeNumber(new Complex(points[x][y].x, points[x][y].y));
				int colorValue = 0;

				// Determine color
				switch (colorSchemeManager.getActiveColorScheme().getIdentifier()) {

				case "WaveColorScheme":

					// Extra calculation for a smooth color transition
					double continuousIndex = escapeNumber + 1
							- (Math.log10(2) / lastEscapeComplexValue.getModulus()) / Math.log10(2);
					colorValue = getRGBValue(continuousIndex);
					break;

				case "LinearColorScheme":
					colorValue = getRGBValue(escapeNumber);
					break;

				case "InsideOutsideColorScheme":
					colorValue = getRGBValue(escapeNumber);
					break;

				}

				// Set color
				image.setRGB(x, y, colorValue);

			}

		}

		return image;

	}

	public int getEscapeNumber(Complex complex) {

		Complex currentValue = complex;
		int i;

		// Perform calculation for the Julia Set
		for (i = 0; i < maxIterations - 1 && currentValue.getModulus() < escapeValue; i++) {

			currentValue = currentValue.power(2).add(constant);

		}

		lastEscapeComplexValue = currentValue;
		return i;

	}

	public int getRGBValue(double continuousIndex) {

		return colorSchemeManager.getActiveColorScheme().getRGBValue(continuousIndex);

	}
}
