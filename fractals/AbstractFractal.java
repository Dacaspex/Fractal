package fractals;

import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;

import org.w3c.dom.Element;

import fractals.colorSchemes.ColorSchemeManager;
import fractals.threading.ThreadFactory;
import util.Settings;

public abstract class AbstractFractal {

	/**
	 * The identifier of the fractal. This should be unique.
	 */
	protected String identifier;

	/**
	 * The name of the fractal. This name is used in all the context menus. It
	 * is good practice to keep this name fairly descriptive.
	 */
	protected String name;

	/**
	 * Scaling used to determine where to draw the fractal.
	 */
	protected Scale scale;

	protected ColorSchemeManager colorSchemeManager;

	public String getIdentifier() {

		return identifier;

	}

	public String getName() {

		return name;

	}

	public Scale getScale() {

		return scale;

	}

	public void setScale(Scale scale) {

		this.scale = scale;

	}

	public ColorSchemeManager getColorSchemeManager() {

		return colorSchemeManager;

	}

	/**
	 * Handles the request for an image. It separates the task of generating
	 * into smaller tasks using threads for better performance. This only starts
	 * the threads and does not yet yield any image.
	 * 
	 * @param threadFactory
	 *            Specify which thread factory to use in order to render the
	 *            image
	 * @param imageWidth
	 *            The requested image width
	 * @param imageHeight
	 *            The requested image height
	 */
	public void requestImage(ThreadFactory threadFactory, int imageWidth, int imageHeight) {

		threadFactory.createThreads(scale, imageWidth, imageHeight, this);

	}

	/**
	 * Method to be implemented that should generate an image of the fractal
	 * using the given dimensions and scale. Not this scale is smaller or equal
	 * to the normal scale since this method is being called from a method that
	 * only has partial work.
	 * 
	 * @param imagewidth
	 *            The width of the image
	 * @param imageHeight
	 *            The height of the image
	 * @param scale
	 *            The scale to use
	 * @return An image with the fractal rendered on top of it
	 */
	public abstract BufferedImage generateImage(int imagewidth, int imageHeight, Scale scale);

	/**
	 * Loads the default settings from the settings xml file. By default it only
	 * resets the scale variable. This method can be overriden to reset more
	 * settings
	 */
	public void loadDefaultSettings() {

		Element defaultSettingsElement = Settings.getFractalSettingsDOM(identifier);

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

	/**
	 * @return A HashMap with information about the fractal
	 */
	public LinkedHashMap<String, String> getInformation() {

		LinkedHashMap<String, String> informationMap = new LinkedHashMap<String, String>();

		informationMap.put("Name", name);
		informationMap.put("X-min", Double.toString(scale.getxMin()));
		informationMap.put("X-max", Double.toString(scale.getxMax()));
		informationMap.put("Y-min", Double.toString(scale.getyMin()));
		informationMap.put("Y-max", Double.toString(scale.getyMax()));
		informationMap.put("Colour Scheme", colorSchemeManager.getActiveColorScheme().getName());

		return informationMap;

	}

}
