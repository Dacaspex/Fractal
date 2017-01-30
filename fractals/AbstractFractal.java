package fractals;

import java.awt.image.BufferedImage;

public abstract class AbstractFractal {

	/**
	 * The name of the fractal. This name is used in all the context menus. It
	 * should be a unique and descriptive short name.
	 */
	private String name;

	/**
	 * Scaling used to determine where to draw the fractal.
	 */
	private Scale scale;
	
	public String getName() {
		
		return name;
		
	}

	public Scale getScale() {

		return scale;

	}

	public void setScale(Scale scale) {

		this.scale = scale;

	}

	/**
	 * This method returns the image that should be rendered based of the
	 * scaling and screen width and height. The implementation differs per
	 * fractal.
	 * 
	 * @param scaling
	 *            The mathematical scale the fractal should be drawn in
	 * @param imageWidth
	 *            The width of the output image
	 * @param imageHeight
	 *            The height of the output image
	 * @return Returns an image with the current scale.
	 */
	public abstract BufferedImage getImage(Scale scale, int imageWidth, int imageHeight);

}
