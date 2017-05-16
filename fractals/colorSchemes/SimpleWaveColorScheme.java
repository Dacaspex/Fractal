package fractals.colorSchemes;

import java.awt.Color;

import fractals.colorSchemes.settings.SimpleWaveColorSchemeSettingsManager;

public class SimpleWaveColorScheme extends AbstractColorScheme {

	private double frequencyRed;
	private double frequencyGreen;
	private double frequencyBlue;
	private int phaseRed;
	private int phaseGreen;
	private int phaseBlue;
	private int center;
	private int delta;

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

	public int getPhaseRed() {

		return phaseRed;

	}

	public void setPhaseRed(int phaseRed) {

		this.phaseRed = phaseRed;

	}

	public int getPhaseGreen() {

		return phaseGreen;

	}

	public void setPhaseGreen(int phaseGreen) {

		this.phaseGreen = phaseGreen;

	}

	public int getPhaseBlue() {

		return phaseBlue;

	}

	public void setPhaseBlue(int phaseBlue) {

		this.phaseBlue = phaseBlue;

	}

	public int getCenter() {

		return center;

	}

	public void setCenter(int center) {

		this.center = center;

	}

	public int getDelta() {

		return delta;

	}

	public void setDelta(int delta) {

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

		if (continuousIndex > maximumValue - threshold) {

			return maximumColor.getRGB();

		}

		int r = (int) Math.abs((Math.sin(frequencyRed * continuousIndex + phaseRed) * center + delta));
		int g = (int) Math.abs((Math.sin(frequencyGreen * continuousIndex + phaseGreen) * center + delta));
		int b = (int) Math.abs((Math.sin(frequencyBlue * continuousIndex + phaseBlue) * center + delta));

		return new Color(r, g, b).getRGB();

	}

}
