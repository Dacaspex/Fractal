package fractals.colorSchemes;

import java.awt.Color;
import java.util.Arrays;

import fractals.colorSchemes.settings.LinearColorSchemeSettingsManager;

public class LinearColorScheme extends AbstractColorScheme {

	private Color[] colorArray;
	private int[] gradientMap;
	private int max;
	private int steps;
	private boolean loop;

	public LinearColorScheme(int max) {

		this.identifier = "LinearColorScheme";
		this.name = "Linear Color Scheme";
		this.colorArray = getDefaultColors();
		this.max = max;
		this.steps = 100;
		this.loop = false;
		this.gradientMap = generateGradientMap();
		this.settingsManager = new LinearColorSchemeSettingsManager(this);

	}
	
	public Color[] getColorArray() {
		return colorArray;
	}
	
	public void setColorArray(Color[] colorArray) {
		this.colorArray = colorArray;
	}

	public int getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
		this.gradientMap = generateGradientMap();
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public boolean isLoop() {
		return loop;
	}

	public void setLoop(boolean loop) {
		this.loop = loop;
	}

	public int[] generateGradientMap() {
		
		int[] gradientMap = new int[steps];

		if (colorArray.length <= 0) {
			// TODO error
		}

		if (colorArray.length == 1) {
			Arrays.fill(gradientMap, colorArray[0].getRGB());
			return gradientMap;
		}

		int colorIndex = 0;
		float relativeIndex = 0;
		float slotSpacing = ((float) steps) / ((float) (colorArray.length - 1));

		for (int absoluteIndex = 0; absoluteIndex < steps; absoluteIndex++) {

			if (relativeIndex > slotSpacing) {
				relativeIndex = 0;
				colorIndex++;
			}
			
			Color leftColor = colorArray[colorIndex];
			Color rightColor = colorArray[colorIndex + 1];
			
			float interpolation = relativeIndex / slotSpacing;
			
			float r = leftColor.getRed() * (1 - interpolation) + rightColor.getRed() * interpolation;
			float g = leftColor.getGreen() * (1 - interpolation) + rightColor.getGreen() * interpolation;
			float b = leftColor.getBlue() * (1 - interpolation) + rightColor.getBlue() * interpolation;
			
			r = Math.min(r, 255);
			g = Math.min(g, 255);
			b = Math.min(b, 255);
			
			gradientMap[absoluteIndex] = new Color(r / 255, g / 255, b / 255).getRGB();
			relativeIndex++;
			
		}

		return gradientMap;

	}

	public Color[] getDefaultColors() {
		
		return new Color[] {
				new Color(0, 0, 128),
				new Color(0, 0, 255),
				new Color(255, 255, 255),
				new Color(255, 200, 0),
				new Color(0, 0, 0)
		};

	}

	@Override
	public int getRGBValue(float referenceNumber) {

		int value = (int) referenceNumber;
		int index = 0;
		
		if (max == 0) {
			return gradientMap[0];
		}
		
		if (loop) {
			index = Math.floorMod(value, steps);
		} else {
			value = Math.min(value, max - 1);
			index = (int) ((((float) value) / ((float) max)) * steps);
		}

		return gradientMap[index];

	}

}
