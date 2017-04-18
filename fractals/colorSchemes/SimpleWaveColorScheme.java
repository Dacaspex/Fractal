package fractals.colorSchemes;

import java.awt.Color;

public class SimpleWaveColorScheme extends AbstractColorScheme {

	private double frequencyRed;
	private double frequencyGreen;
	private double frequencyBlue;
	private int phaseRed;
	private int phaseGreen;
	private int phaseBlue;
	private int center;
	private int delta;

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

	public int getRGBValue(double continuousIndex) {

		int r = (int) Math.abs((Math.sin(frequencyRed * continuousIndex + phaseRed) * center + delta));
		int g = (int) Math.abs((Math.sin(frequencyGreen * continuousIndex + phaseGreen) * center + delta));
		int b = (int) Math.abs((Math.sin(frequencyBlue * continuousIndex + phaseBlue) * center + delta));

		return new Color(r, g, b).getRGB();

	}

}
