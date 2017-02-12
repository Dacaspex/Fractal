package fractals;

import java.awt.image.BufferedImage;

import org.w3c.dom.Element;

import util.Settings;

public abstract class AbstractFractal {

	/**
	 * The name of the fractal. This name is used in all the context menus. It
	 * should be a unique and descriptive short name.
	 */
	protected String name;

	/**
	 * Scaling used to determine where to draw the fractal.
	 */
	protected Scale scale;

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
	 * @param imageWidth
	 *            The width of the output image
	 * @param imageHeight
	 *            The height of the output image
	 * @return Returns an image with the current scale.
	 */
	public abstract BufferedImage getImage(int imageWidth, int imageHeight);

	/**
	 * Loads the default settings from the settings xml file. By default it only
	 * resets the scale variable. This method can be overriden to reset more
	 * settings
	 */
	public void loadDefaultSettigns() {

		Element defaultSettingsElement = Settings.getFractalSettingsDOM(name);

		if (defaultSettingsElement == null) {

			return;

		}

		Element scaleNode = (Element) defaultSettingsElement.getElementsByTagName("scale").item(0);

		double xMin = Double.parseDouble(scaleNode.getElementsByTagName("xMin").item(0).getTextContent());
		double xMax = Double.parseDouble(scaleNode.getElementsByTagName("xMax").item(0).getTextContent());
		double yMin = Double.parseDouble(scaleNode.getElementsByTagName("yMin").item(0).getTextContent());
		double yMax = Double.parseDouble(scaleNode.getElementsByTagName("yMax").item(0).getTextContent());

		scale = new Scale(xMin, xMax, yMin, yMax);

	}

}
