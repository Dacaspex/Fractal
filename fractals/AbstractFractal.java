package fractals;

import java.awt.image.BufferedImage;
import java.util.HashMap;

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

	public abstract void requestImage(int imageWidth, int imageHeight);

	/**
	 * Loads the default settings from the settings xml file. By default it only
	 * resets the scale variable. This method can be overriden to reset more
	 * settings
	 */
	public void loadDefaultSettings() {

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
	
	public HashMap<String, String> getInformation() {
		
		HashMap<String, String> informationMap = new HashMap<String, String>();
		
		informationMap.put("Name", name);
		informationMap.put("X-min", Double.toString(scale.getxMin()));
		informationMap.put("X-max", Double.toString(scale.getxMax()));
		informationMap.put("Y-min", Double.toString(scale.getyMin()));
		informationMap.put("Y-max", Double.toString(scale.getyMax()));
		
		return informationMap;
		
	}

}
