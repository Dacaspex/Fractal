package fractals;

import java.awt.image.BufferedImage;

import org.w3c.dom.Element;

import complex.Complex;
import fractals.colorSchemes.ColorSchemeManager;
import fractals.colorSchemes.ColorSchemeManagerOptions;
import fractals.colorSchemes.LinearColorScheme;
import fractals.colorSchemes.SimpleWaveColorScheme;
import fractals.settings.MandelBrotSettingsManager;
import util.Settings;
import util.math.Point;
import util.math.Scale;

public class MandelBrotFractal extends AbstractFractal {

	private int maxIterations;
	private double escapeValue;

	public MandelBrotFractal() {

		// Set identification
		identifier = "MandelBrotSet1";

		// Set default values
		name = "Mandelbrot Set";
		maxIterations = 512;
		escapeValue = 2.0;
		scale = new Scale(new Point(-0.5, 0));

		// Create color scheme manager
		colorSchemeManager = new ColorSchemeManager();
		
		SimpleWaveColorScheme simpleWaveColorScheme = new SimpleWaveColorScheme();
		simpleWaveColorScheme.setMaximumValue(maxIterations);
		simpleWaveColorScheme.setThreshold(2.0);
		
		LinearColorScheme linearColorScheme = new LinearColorScheme(maxIterations);
		linearColorScheme.loadDefaultColors();
		linearColorScheme.generateGradientMap();
		
		colorSchemeManager.addColorScheme(simpleWaveColorScheme, ColorSchemeManagerOptions.SET_AS_ACTIVE);
		colorSchemeManager.addColorScheme(linearColorScheme);
		
		settingsManager = new MandelBrotSettingsManager(this);

	}

	public int getMaxIterations() {

		return maxIterations;

	}

	public void setMaxIterations(int maxIterations) {

		this.maxIterations = maxIterations;
		((SimpleWaveColorScheme) colorSchemeManager.getColorScheme("SimpleWaveColorScheme1"))
				.setMaximumValue(maxIterations);
		((LinearColorScheme) colorSchemeManager.getColorScheme("LinearColorScheme1")).setSteps(maxIterations);
		((LinearColorScheme) colorSchemeManager.getColorScheme("LinearColorScheme1")).generateGradientMap();

	}

	public double getEscapeValue() {

		return escapeValue;

	}

	public void setEscapeValue(double escapeValue) {

		this.escapeValue = escapeValue;

	}

	@Override
	public BufferedImage generateImage(int width, int height, Point[][] points) {

		// Create empty image
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		for (int x = 0; x < width; x++) {

			for (int y = 0; y < height; y++) {

				Complex constant = new Complex(points[x][y].x, points[x][y].y);

				// Get the escape number
				int escapeNumber = getEscapeValue(constant);

				// Set color
				int colorValue = colorSchemeManager.getActiveColorScheme().getRGBValue(escapeNumber);
				image.setRGB(x, y, colorValue);

			}

		}

		return image;

	}

	public int getEscapeValue(Complex constant) {

		Complex value = new Complex(0, 0);
		int i;

		// Perform calculation
		for (i = 0; i < maxIterations && value.getModulus() < escapeValue; i++) {

			value.power(2).add(constant);

		}

		return i;

	}

	@Override
	public void loadDefaultSettings() {

		super.loadDefaultSettings();

		try {

			Element defaultSettingsElement = Settings.getFractalSettingsDOM(identifier);

			int maxIterations = Integer
					.parseInt(defaultSettingsElement.getElementsByTagName("maxIterations").item(0).getTextContent());
			double escapeValue = Double
					.parseDouble(defaultSettingsElement.getElementsByTagName("escapeValue").item(0).getTextContent());

			this.maxIterations = maxIterations;
			this.escapeValue = escapeValue;

		} catch (NullPointerException exception) {

			return;

		}

	}

}
