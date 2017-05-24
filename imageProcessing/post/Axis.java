package imageProcessing.post;

import java.awt.Color;
import java.awt.image.BufferedImage;

import util.math.Scale;

public class Axis {

	private Color axisColor;
	private Color textColor;

	private int axisWidth;

	private boolean showY;
	private boolean showX;

	private Scale scale;

	public Axis(Scale scale) {

		axisColor = new Color(25, 25, 50, 100);
		textColor = Color.WHITE;
		axisWidth = 2;
		showY = true;
		showX = true;
		this.scale = scale;

	}

	public BufferedImage drawAxis(BufferedImage image) {

		return image;

	}

}
