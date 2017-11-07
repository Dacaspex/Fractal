package fractals.colorSchemes;

import java.awt.Color;

import complex.Complex;
import fractals.colorSchemes.settings.WaveColorSchemeSettingsManager;

public class WaveColorScheme extends AbstractColorScheme {

	private float frequencyRed;
	private float frequencyGreen;
	private float frequencyBlue;
	private float phaseRed;
	private float phaseGreen;
	private float phaseBlue;
	private float centerRed;
	private float centerGreen;
	private float centerBlue;
	private float deltaRed;
	private float deltaGreen;
	private float deltaBlue;

	private boolean useContinuousIndex;

	private Color maximumColor;
	private float threshold;

	public WaveColorScheme() {

		identifier = "WaveColorScheme";
		name = "Wave colour scheme";

		frequencyRed = 0.016f;
		frequencyGreen = 0.013f;
		frequencyBlue = 0.01f;

		phaseRed = 4;
		phaseGreen = 2;
		phaseBlue = 1;

		centerRed = 230;
		centerGreen = 230;
		centerBlue = 230;
		
		deltaRed = 25;
		deltaGreen = 25;
		deltaBlue = 25;

		useContinuousIndex = true;

		maximumColor = Color.black;
		threshold = 0;

		settingsManager = new WaveColorSchemeSettingsManager(this);

	}

	public float getFrequencyRed() {
		return frequencyRed;
	}

	public void setFrequencyRed(float frequencyRed) {
		this.frequencyRed = frequencyRed;
	}

	public float getFrequencyGreen() {
		return frequencyGreen;
	}

	public void setFrequencyGreen(float frequencyGreen) {
		this.frequencyGreen = frequencyGreen;
	}

	public float getFrequencyBlue() {
		return frequencyBlue;
	}

	public void setFrequencyBlue(float frequencyBlue) {
		this.frequencyBlue = frequencyBlue;
	}

	public float getPhaseRed() {
		return phaseRed;
	}

	public void setPhaseRed(float phaseRed) {
		this.phaseRed = phaseRed;
	}

	public float getPhaseGreen() {
		return phaseGreen;
	}

	public void setPhaseGreen(float phaseGreen) {
		this.phaseGreen = phaseGreen;
	}

	public float getPhaseBlue() {
		return phaseBlue;
	}

	public void setPhaseBlue(float phaseBlue) {
		this.phaseBlue = phaseBlue;
	}

	public float getCenterRed() {
		return centerRed;
	}

	public void setCenterRed(float centerRed) {
		this.centerRed = centerRed;
	}

	public float getCenterGreen() {
		return centerGreen;
	}

	public void setCenterGreen(float centerGreen) {
		this.centerGreen = centerGreen;
	}

	public float getCenterBlue() {
		return centerBlue;
	}

	public void setCenterBlue(float centerBlue) {
		this.centerBlue = centerBlue;
	}

	public float getDeltaRed() {
		return deltaRed;
	}

	public void setDeltaRed(float deltaRed) {
		this.deltaRed = deltaRed;
	}

	public float getDeltaGreen() {
		return deltaGreen;
	}

	public void setDeltaGreen(float deltaGreen) {
		this.deltaGreen = deltaGreen;
	}

	public float getDeltaBlue() {
		return deltaBlue;
	}

	public void setDeltaBlue(float deltaBlue) {
		this.deltaBlue = deltaBlue;
	}

	public float getThreshold() {
		return threshold;
	}

	public void setThreshold(float threshold) {
		this.threshold = threshold;
	}

	public Color getMaximumColor() {
		return maximumColor;
	}

	public void setMaximumColor(Color maximumColor) {
		this.maximumColor = maximumColor;
	}

	public int getRGBValue(float value) {
		return getRGBValue(value, null);
	}

	public int getRGBValue(float escapeNumber, Complex lastEscapeComplexValue) {

		float value = escapeNumber;
		if (useContinuousIndex && lastEscapeComplexValue != null) {
			// Extra calculation for a smooth color transition
			value = (float) (escapeNumber + 1 - (Math.log10(2) / lastEscapeComplexValue.getModulus()) / Math.log10(2));
		}

		// Apply threshold
		if (threshold > 0 && value > threshold) {
			return maximumColor.getRGB();
		}

		// Calculate rgb values
		int r = (int) Math.abs((Math.sin(frequencyRed * value + phaseRed) * centerRed + deltaRed));
		int g = (int) Math.abs((Math.sin(frequencyGreen * value + phaseGreen) * centerGreen + deltaGreen));
		int b = (int) Math.abs((Math.sin(frequencyBlue * value + phaseBlue) * centerBlue + deltaBlue));

		// Cap rgb values at 255
		r = Math.min(r, 255);
		g = Math.min(g, 255);
		b = Math.min(b, 255);

		return new Color(r, g, b).getRGB();

	}

}
