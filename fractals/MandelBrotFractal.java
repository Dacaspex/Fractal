package fractals;

import java.awt.image.BufferedImage;

import org.w3c.dom.Element;

import complex.Complex;
import fractals.colorSchemes.ColorSchemeManager;
import fractals.colorSchemes.ColorSchemeManagerOptions;
import fractals.colorSchemes.MandelbrotColorScheme;
import fractals.settings.MandelBrotSettingsManager;
import util.Settings;

public class MandelBrotFractal extends AbstractFractal {

	private int maxIterations;
	private double escapeValue;

	public MandelBrotFractal() {

		// Set needed variable for identification
		identifier = "MandelBrotSet1";
		name = "Mandelbrot Set";

		// Create color scheme manager
		colorSchemeManager = new ColorSchemeManager();

		// Load default settings
		loadDefaultSettings();

		colorSchemeManager.addColorScheme(new MandelbrotColorScheme(), ColorSchemeManagerOptions.SET_AS_ACTIVE);
		settingsManager = new MandelBrotSettingsManager(this);

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
	public BufferedImage generateImage(int imageWidth, int imageHeight, Scale scale) {

		// Create empty image
		BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);

		// Calculate x and y step
		double xTransformFactor = ((scale.getxDifference()) / (double) (imageWidth - 1));
		double yTransformFactor = ((scale.getyDifference()) / (double) (imageHeight - 1));

		// Loop through all pixels of the image
		for (double i = 0; i < imageHeight; i++) {

			for (double j = 0; j < imageWidth; j++) {

				// Calculate the coordinates in the scale system
				double x = scale.getxMin() + j * xTransformFactor;
				double y = scale.getyMin() + i * yTransformFactor;

				Complex constant = new Complex(x, y);

				// Get the escape number
				int escapeNumber = getEscapeValue(constant);

				// Set color
				int colorValue = colorSchemeManager.getActiveColorScheme().getRGBValue(escapeNumber);
				image.setRGB((int) j, (int) i, colorValue);

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
