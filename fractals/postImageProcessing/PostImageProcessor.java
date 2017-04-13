package fractals.postImageProcessing;

import java.awt.image.BufferedImage;

import fractals.AbstractFractal;

public class PostImageProcessor {

	private AbstractFractal fractal;
	
	private boolean drawAxis;
	private Axis axis;

	public PostImageProcessor(AbstractFractal fractal, boolean drawAxis) {
		
		this.fractal = fractal;
		this.drawAxis = drawAxis;
		
		axis = new Axis(fractal.getScale());

	}

	public BufferedImage applyEffects(BufferedImage image) {

		if (drawAxis)
			image = axis.drawAxis(image);

		return image;

	}

}
