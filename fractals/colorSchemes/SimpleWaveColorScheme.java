package fractals.colorSchemes;

import java.awt.Color;

import fractals.colorSchemes.settings.SimpleWaveColorSchemeSettingsManager;

public class SimpleWaveColorScheme extends AbstractColorScheme {

	private double frequencyRed;
	private double frequencyGreen;
	private double frequencyBlue;
	private double phaseRed;
	private double phaseGreen;
	private double phaseBlue;
	private double center;
	private double delta;

	private double maximumValue;
	private Color maximumColor;

	private double threshold;

	public SimpleWaveColorScheme() {

		identifier = "SimpleWaveColorScheme1";
		name = "Simple wave colour scheme";

		frequencyRed = 0.016;
		frequencyGreen = 0.013;
		frequencyBlue = 0.01;

		phaseRed = 4;
		phaseGreen = 2;
		phaseBlue = 1;

		center = 230;
		delta = 25;

		maximumValue = Integer.MAX_VALUE;
		maximumColor = Color.black;
		threshold = 0;

		settingsManager = new SimpleWaveColorSchemeSettingsManager(this);

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

	public int getRGBValue(double continuousIndex) {

		// Apply threshold
		if (continuousIndex > maximumValue - threshold) {
			return maximumColor.getRGB();
		}

		// Calculate rgb values
		int r = (int) Math.abs((Math.sin(frequencyRed * continuousIndex + phaseRed) * center + delta));
		int g = (int) Math.abs((Math.sin(frequencyGreen * continuousIndex + phaseGreen) * center + delta));
		int b = (int) Math.abs((Math.sin(frequencyBlue * continuousIndex + phaseBlue) * center + delta));

		// Cap rgb values at 255
		r = Math.min(r, 255);
		g = Math.min(g, 255);
		b = Math.min(b, 255);

		return new Color(r, g, b).getRGB();

	}

}
