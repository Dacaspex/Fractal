package fractals.colorSchemes;

import java.awt.Color;

import complex.Complex;
import fractals.colorSchemes.settings.WaveColorSchemeSettingsManager;

public class WaveColorScheme extends AbstractColorScheme {

	private double frequencyRed;
	private double frequencyGreen;
	private double frequencyBlue;
	private double phaseRed;
	private double phaseGreen;
	private double phaseBlue;
	private double center;
	private double delta;

	private boolean useContinuousIndex;

	private double maximumValue;
	private Color maximumColor;
	private double threshold;

	public WaveColorScheme() {

		identifier = "WaveColorScheme";
		name = "Wave colour scheme";

		frequencyRed = 0.016;
		frequencyGreen = 0.013;
		frequencyBlue = 0.01;

		phaseRed = 4;
		phaseGreen = 2;
		phaseBlue = 1;

		center = 230;
		delta = 25;

		useContinuousIndex = true;

		maximumValue = Integer.MAX_VALUE;
		maximumColor = Color.black;
		threshold = 0;

		settingsManager = new WaveColorSchemeSettingsManager(this);

	}

	public double getFrequencyRed() {

		return frequencyRed;

	}

	public void setFrequencyRed(double frequencyRed) {

		this.frequencyRed = frequencyRed;

	}

	public double getFrequencyGreen() {

		return frequencyGreen;

	}

	public void setFrequencyGreen(double frequencyGreen) {

		this.frequencyGreen = frequencyGreen;

	}

	public double getFrequencyBlue() {

		return frequencyBlue;

	}

	public void setFrequencyBlue(double frequencyBlue) {

		this.frequencyBlue = frequencyBlue;

	}

	public double getPhaseRed() {

		return phaseRed;

	}

	public void setPhaseRed(double phaseRed) {

		this.phaseRed = phaseRed;

	}

	public double getPhaseGreen() {

		return phaseGreen;

	}

	public void setPhaseGreen(double phaseGreen) {

		this.phaseGreen = phaseGreen;

	}

	public double getPhaseBlue() {

		return phaseBlue;

	}

	public void setPhaseBlue(double phaseBlue) {

		this.phaseBlue = phaseBlue;

	}

	public double getCenter() {

		return center;

	}

	public void setCenter(double center) {

		this.center = center;

	}

	public double getDelta() {

		return delta;

	}

	public void setDelta(double delta) {

		this.delta = delta;

	}

	public double getThreshold() {

		return threshold;

	}

	public double getMaximumValue() {

		return maximumValue;

	}

	public void setMaximumValue(double maximumValue) {

		this.maximumValue = maximumValue;

	}

	public void setThreshold(double threshold) {

		this.threshold = threshold;

	}

	public Color getMaximumColor() {

		return maximumColor;

	}

	public void setMaximumColor(Color maximumColor) {

		this.maximumColor = maximumColor;

	}

	public int getRGBValue(double value) {
		return getRGBValue(value, null);
	}

	public int getRGBValue(double escapeNumber, Complex lastEscapeComplexValue) {

		double value = escapeNumber;
		if (useContinuousIndex && lastEscapeComplexValue != null) {
			// Extra calculation for a smooth color transition
			value = escapeNumber + 1 - (Math.log10(2) / lastEscapeComplexValue.getModulus()) / Math.log10(2);
		}

		// Apply threshold
		if (value > maximumValue - threshold) {
			return maximumColor.getRGB();
		}

		// Calculate rgb values
		int r = (int) Math.abs((Math.sin(frequencyRed * value + phaseRed) * center + delta));
		int g = (int) Math.abs((Math.sin(frequencyGreen * value + phaseGreen) * center + delta));
		int b = (int) Math.abs((Math.sin(frequencyBlue * value + phaseBlue) * center + delta));

		// Cap rgb values at 255
		r = Math.min(r, 255);
		g = Math.min(g, 255);
		b = Math.min(b, 255);

		return new Color(r, g, b).getRGB();

	}

}
