package fractals.postImageProcessing;

import java.awt.image.BufferedImage;

public class PostImageProcessor {

	private boolean useAxis;
	private Axis axis;

	public PostImageProcessor() {

	}

	public BufferedImage applyEffects(BufferedImage image) {

		if (useAxis)
			image = axis.drawAxis(image);

		return image;

	}

}
