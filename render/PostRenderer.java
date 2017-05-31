package render;

import java.awt.image.BufferedImage;

import fractals.AbstractFractal;
import imageProcessing.post.Axis;

public class PostRenderer {

	private AbstractFractal fractal;

	private boolean drawAxis;
	private Axis axis;
	
	public PostRenderer() {
		
		drawAxis = false;
		
	}

	public BufferedImage render(BufferedImage image, AbstractFractal fractal) {

		return image;

	}

}
