package fractals.postImageProcessing;

import java.awt.Color;
import java.awt.image.BufferedImage;

import fractals.Scale;

public class Axis {
	
	private Color axisColor;
	private Color textColor;
	
	private int axisWidth;
	
	private boolean showY;
	private boolean showX;
	
	private Scale scale;
	
	public Axis(Scale scale)  {
		
		axisColor = Color.WHITE;
		textColor = Color.WHITE;
		axisWidth = 2;
		showY = true;
		showX = true;
		this.scale = scale;
		
	}
	
	public BufferedImage drawAxis(BufferedImage image) {
		
		if (showX && (scale.getxMin() < 0 && scale.getxMax() > 0)) {
			
			// Draw x axis
			
		}
		
		return image;
		
	}

}
