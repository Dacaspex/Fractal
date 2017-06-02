package fractals;

import java.awt.image.BufferedImage;

import fractals.colorSchemes.ColorSchemeManager;
import fractals.settings.SettingsManager;
import render.Renderer;
import util.math.Point;
import util.math.Scale;

public abstract class AbstractFractal {

	/**
	 * The identifier of the fractal. This should be unique.
	 */
	protected FractalIdentifier identifier;

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

	public FractalIdentifier getIdentifier() {
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
	 * Method to be implemented that should generate an image of the fractal
	 * using the given dimensions and point list. Note that this point list may
	 * not contain every point of the image, since the work is sub divided over
	 * threads when multi threading is enabled.
	 * 
	 * @see Renderer
	 * 
	 * @param width
	 *            The width of the image
	 * @param height
	 *            The height of the image
	 * @param points
	 *            The set of points the fractal needs to compute
	 * @return An image with the fractal rendered on top of it
	 */
	public abstract BufferedImage generateImage(int width, int height, Point[][] points);

	public enum FractalIdentifier {
		JULIA_SET, MANDELBROT_SET
	}

}
