package fractals.colorSchemes;

import java.awt.Color;

import fractals.colorSchemes.settings.InsideOutsideColorSchemeSettingsManager;

public class InsideOutsideColorScheme extends AbstractColorScheme {

	private Color inColor;
	private Color outColor;
	private float threshold;
	
	public InsideOutsideColorScheme() {
		
		this.identifier = "InsideOutsideColorScheme";
		this.name = "Inside Outside Color Scheme";
		this.inColor = Color.BLACK;
		this.outColor = Color.WHITE;
		this.threshold = 100;
		
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
	
	public float getThreshold() {
		return threshold;
	}

	public void setThreshold(float threshold) {
		this.threshold = threshold;
	}

	@Override
	public int getRGBValue(float value) {
		
		if (value < threshold) {
			return outColor.getRGB();
		} else {
			return inColor.getRGB();
		}
		
	}

}
