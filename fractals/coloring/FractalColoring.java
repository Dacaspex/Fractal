package fractals.coloring;

import java.awt.Color;
import java.util.Arrays;

public class FractalColoring {

	private Color[] colorArray;
	private int[] gradientMap;
	private int steps;
	
	public FractalColoring(boolean loadDefaultColors, int steps) {
		
		this.steps = steps;
		
		if (loadDefaultColors) {
			
			loadDefaultColors();
			generateGradientMap();
			
		}

	}

	public FractalColoring(boolean loadDefaultColors) {

		if (loadDefaultColors) {
			
			loadDefaultColors();
			steps = colorArray.length;
			generateGradientMap();
			
		}

	}
	
	public FractalColoring(int steps) {
		
		this(true, steps);
		
	}
	
	public FractalColoring() {
		
		this(true);
		
	}

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

	public void loadDefaultColors() {

		colorArray = new Color[] { new Color(0, 0, 0), new Color(255, 200, 0), new Color(255, 255, 255),
				new Color(0, 0, 255), new Color(0, 0, 128) };

	}
	
	public void setColorArray(Color[] colorArray) {
		
		this.colorArray = colorArray;
		
	}
	
	public void addColor(Color color) {
		
		addColors(new Color[] {color});
		
	}
	
	public void addColors(Color[] colors) {
		
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
		
	}

	public int getRGBValue(int referenceNumber) {

		return gradientMap[referenceNumber];

	}

}
