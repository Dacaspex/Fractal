package fractals;

import java.awt.image.BufferedImage;

import org.w3c.dom.Element;

import complex.Complex;
import fractals.colorSchemes.ColorSchemeManager;
import fractals.colorSchemes.ColorSchemeManagerOptions;
import fractals.colorSchemes.LinearColorScheme;
import fractals.colorSchemes.SimpleWaveColorScheme;
import fractals.settings.JuliaSettingsManager;
import util.Settings;
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
		colorSchemeManager.addColorScheme(linearColorScheme);
		colorSchemeManager.addColorScheme(new SimpleWaveColorScheme(), ColorSchemeManagerOptions.SET_AS_ACTIVE);
		settingsManager = new JuliaSettingsManager(this);

	}

	public BufferedImage test(int width, int height, util.math.Scale scale) {

		Point[][] points = scale.getPointsOnScreen(width, height);
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < width; x++) {

			for (int y = 0; y < height; y++) {

				int escapeNumber = getEscapeNumber(new Complex(points[x][y].x, points[x][y].y));
				double continuousIndex = escapeNumber + 1
						- (Math.log10(2) / lastEscapeComplexValue.getModulus()) / Math.log10(2);
				int colorValue = getRGBValue(continuousIndex);
				image.setRGB(x, y, colorValue);

			}

		}

		return image;

	}

	public int getMaxIterations() {

		return maxIterations;

	}

	public void setMaxIterations(int maxIterations) {

		this.maxIterations = maxIterations;

		// Reload the gradient map in the linear color scheme
		((LinearColorScheme) colorSchemeManager.getColorScheme("LinearColorScheme1")).setMaxInputSteps(maxIterations);
		((LinearColorScheme) colorSchemeManager.getColorScheme("LinearColorScheme1")).generateGradientMap();

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

				case "SimpleWaveColorScheme1":

					// Extra calculation for a smooth color transition
					double continuousIndex = escapeNumber + 1
							- (Math.log10(2) / lastEscapeComplexValue.getModulus()) / Math.log10(2);
					colorValue = getRGBValue(continuousIndex);
					break;

				case "LinearColorScheme1":
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

	@Override
	public void loadDefaultSettings() {

		super.loadDefaultSettings();

		try {

			Element defaultSettingsElement = Settings.getFractalSettingsDOM(identifier);
			Element constantNode = (Element) defaultSettingsElement.getElementsByTagName("constant").item(0);
			Element complexNode = (Element) constantNode.getElementsByTagName("complex").item(0);

			Complex constant = Settings.getComplexFromElement(complexNode);
			int maxIterations = Integer
					.parseInt(defaultSettingsElement.getElementsByTagName("maxIterations").item(0).getTextContent());
			double escapeValue = Double
					.parseDouble(defaultSettingsElement.getElementsByTagName("escapeValue").item(0).getTextContent());

			this.constant = constant;
			this.maxIterations = maxIterations;
			this.escapeValue = escapeValue;

		} catch (NullPointerException exception) {

			return;

		}

	}

}
