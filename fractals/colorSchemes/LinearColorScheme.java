package fractals.colorSchemes;

import java.awt.Color;
import java.util.Arrays;

import fractals.colorSchemes.settings.LinearColorSchemeSettingsManager;

public class LinearColorScheme extends AbstractColorScheme {

	private Color[] colorArray;
	private int[] gradientMap;
	private int steps;
	private int maxInputSteps;

	public LinearColorScheme(int steps) {

		this.identifier = "LinearColorScheme";
		this.name = "Linear Color Scheme";
		this.colorArray = new Color[0];
		this.steps = steps;
		this.maxInputSteps = steps;
		this.settingsManager = new LinearColorSchemeSettingsManager(this);

	}

	public int getSteps() {

		return steps;

	}

	public void setSteps(int steps) {

		this.steps = steps;

	}

	public void setMaxInputSteps(int maxInputSteps) {

		this.maxInputSteps = maxInputSteps;

	}

	/**
	 * This method generates a gradient color map for the current loaded color
	 * and steps. It will do this linearly from one color to the next in equally
	 * spaced steps. It updates the gradient map which is used to retrieve a
	 * color value
	 */
	public void generateGradientMap() {

		int gradientMap[] = new int[steps];

		if (colorArray.length == 1) {

			Arrays.fill(gradientMap, colorArray[0].getRGB());
			this.gradientMap = gradientMap;

		}

		double colorDelta = 1.0 / (colorArray.length - 1);
		for (int i = 0; i < steps; i++) {

			double globalRel = (double) i / (steps - 1);
			int index0 = (int) (globalRel / colorDelta);
			int index1 = Math.min(colorArray.length - 1, index0 + 1);
			double localRel = (globalRel - index0 * colorDelta) / colorDelta;

			Color c0 = colorArray[index0];
			int r0 = c0.getRed();
			int g0 = c0.getGreen();
			int b0 = c0.getBlue();
			int a0 = c0.getAlpha();

			Color c1 = colorArray[index1];
			int r1 = c1.getRed();
			int g1 = c1.getGreen();
			int b1 = c1.getBlue();
			int a1 = c1.getAlpha();

			int dr = r1 - r0;
			int dg = g1 - g0;
			int db = b1 - b0;
			int da = a1 - a0;

			int r = (int) (r0 + localRel * dr);
			int g = (int) (g0 + localRel * dg);
			int b = (int) (b0 + localRel * db);
			int a = (int) (a0 + localRel * da);
			int rgb = (a << 24) | (r << 16) | (g << 8) | (b << 0);
			gradientMap[i] = rgb;

		}

		this.gradientMap = gradientMap;

	}

	/**
	 * Loads a set of default colors in the color array TODO: Specify which
	 * colors.
	 */
	public void loadDefaultColors() {

		colorArray = new Color[] { new Color(0, 0, 128), new Color(0, 0, 255), new Color(255, 255, 255),
				new Color(255, 200, 0), new Color(0, 0, 0) };

	}

	/**
	 * Sets the color array to a specific new array. When the updateGradientMap
	 * variable is true, it also updates the gradient map immediately
	 * 
	 * @param colorArray
	 *            The new color array that should be loaded in
	 * @param updateGradientMap
	 *            If set to true, it will update the gradient map as well
	 */
	public void setColorArray(Color[] colorArray, boolean updateGradientMap) {

		this.colorArray = colorArray;

		if (updateGradientMap) {

			generateGradientMap();

		}

	}

	/**
	 * Adds a color to the end of the color array
	 * 
	 * @param color
	 *            The color to be added
	 * @param updateGradientMap
	 *            If set to true, it will update the gradient map as well
	 */
	public void addColor(Color color, boolean updateGradientMap) {

		addColors(new Color[] { color }, updateGradientMap);

	}

	/**
	 * Adds an array of colors to the end of the color array
	 * 
	 * @param colors
	 *            The array of colors to be added at the end
	 * @param updateGradientMap
	 *            If set to true, it will update the gradient map as well
	 */
	public void addColors(Color[] colors, boolean updateGradientMap) {

		// Create new array
		Color[] colorArray = new Color[this.colorArray.length + colors.length];

		// Copy old array into new array
		int index = 0;
		for (int i = 0; i < this.colorArray.length; i++) {

			colorArray[index++] = this.colorArray[i];

		}

		// Append new colors
		for (int j = 0; j < colors.length; j++) {

			colorArray[index++] = colors[j];

		}

		this.colorArray = colorArray;

		generateGradientMap();

	}

	/**
	 * Returns the value in the gradient map that corresponds to the
	 * referenceNumber.
	 * 
	 * @param referenceNumber
	 *            The position in the array
	 * @return The color that corresponds to that position in the array
	 */
	@Override
	public int getRGBValue(float referenceNumber) {

		int index = (int) ((referenceNumber / maxInputSteps) * (steps - 1));
		return gradientMap[index];

	}

}
