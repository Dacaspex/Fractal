package fractals.colorSchemes;

import java.awt.Color;

import fractals.colorSchemes.settings.InsideOutsideColorSchemeSettingsManager;

public class InsideOutsideColorScheme extends AbstractColorScheme {

	private Color inColor;
	private Color outColor;
	private double maxIterations;
	private double threshold;
	
	public InsideOutsideColorScheme() {
		
		this.identifier = "InsideOutsideColorScheme";
		this.name = "Inside Outside Color Scheme";
		this.inColor = Color.BLACK;
		this.outColor = Color.WHITE;
		this.maxIterations = 0;
		this.threshold = 0;
		
		this.settingsManager = new InsideOutsideColorSchemeSettingsManager(this);
		
	}
	
	public Color getInColor() {
		return inColor;
	}

	public void setInColor(Color inColor) {
		this.inColor = inColor;
	}

	public Color getOutColor() {
		return outColor;
	}

	public void setOutColor(Color outColor) {
		this.outColor = outColor;
	}

	public double getMaxIterations() {
		return maxIterations;
	}

	public void setMaxIterations(double maxIterations) {
		this.maxIterations = maxIterations;
	}

	public double getThreshold() {
		return threshold;
	}

	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}

	@Override
	public int getRGBValue(float value) {
		
		if (value < maxIterations - threshold) {
			return outColor.getRGB();
		} else {
			return inColor.getRGB();
		}
		
	}

}
