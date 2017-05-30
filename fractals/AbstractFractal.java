package fractals;

import java.awt.image.BufferedImage;

import fractals.colorSchemes.ColorSchemeManager;
import fractals.settings.SettingsManager;
import render.threading.ThreadManager;
import util.math.Point;
import util.math.Scale;

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

	protected SettingsManager settingsManager;

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

	public SettingsManager getSettingsManager() {

		return settingsManager;

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
	@Deprecated
	public void requestImage(ThreadManager threadFactory, int imageWidth, int imageHeight) {

		threadFactory.createThreads(this, imageWidth, imageHeight);

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
	public abstract BufferedImage generateImage(int imagewidth, int imageHeight, Point[][] points);

}
