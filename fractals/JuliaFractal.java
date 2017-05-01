package fractals;

import java.awt.image.BufferedImage;

import org.w3c.dom.Element;

import complex.Complex;
import fractals.colorSchemes.ColorSchemeManager;
import fractals.colorSchemes.ColorSchemeManagerOptions;
import fractals.colorSchemes.JuliaColorScheme;
import fractals.colorSchemes.LinearColorScheme;
import fractals.settings.JuliaSettingsManager;
import util.Settings;

public class JuliaFractal extends AbstractFractal {

	private Complex constant;
	private int maxIterations;
	private double escapeValue;

	private Complex lastEscapeComplexValue;

	public JuliaFractal() {

		identifier = "JuliaSet1";
		name = "Julia Set";

		loadDefaultSettings();

		lastEscapeComplexValue = new Complex();
		colorSchemeManager = new ColorSchemeManager();
		colorSchemeManager.addColorScheme(new LinearColorScheme(true, maxIterations));
		colorSchemeManager.addColorScheme(new JuliaColorScheme(), ColorSchemeManagerOptions.SET_AS_ACTIVE);
		settingsManager = new JuliaSettingsManager(this);

	}

	public int getMaxIterations() {

		return maxIterations;

	}

	public void setMaxIterations(int maxIterations) {

		this.maxIterations = maxIterations;
		((LinearColorScheme) colorSchemeManager.getColorScheme("LinearColorScheme1")).setSteps(maxIterations, true);

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
	public BufferedImage generateImage(int imageWidth, int imageHeight, Scale scale) {

		BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);

		double xTransformFactor = ((scale.getxDifference()) / (double) (imageWidth - 1));
		double yTransformFactor = ((scale.getyDifference()) / (double) (imageHeight - 1));

		for (double i = 0; i < imageHeight - 1; i++) {

			for (double j = 0; j < imageWidth - 1; j++) {

				double x = scale.getxMin() + j * xTransformFactor;
				double y = scale.getyMin() + i * yTransformFactor;

				int escapeNumber = getEscapeNumber(new Complex(x, y));
				int colorValue = 0;

				switch (colorSchemeManager.getActiveColorScheme().getIdentifier()) {

				case "JuliaColorScheme1":
					double continuousIndex = escapeNumber + 1
							- (Math.log10(2) / lastEscapeComplexValue.getModulus()) / Math.log10(2);
					colorValue = getRGBValue(continuousIndex);
					break;

				case "LinearColorScheme1":
					colorValue = getRGBValue(escapeNumber);
					break;

				}

				image.setRGB((int) j, (int) i, colorValue);

			}

		}

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

		return colorSchemeManager.getActiveColorScheme().getRGBValue(continuousIndex);

	}

	@Override
	public void loadDefaultSettings() {

		super.loadDefaultSettings();

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

	}

}
