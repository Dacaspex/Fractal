package fractals;

import java.util.ArrayList;

import fractals.AbstractFractal.FractalIdentifier;
import gui.FractalPanel;

public class FractalManager {

	/*
	 * List that stores all instances of fractals that are loaded in the fractal
	 * manager. This allows the option to save all settings of the fractal in
	 * the current display mode (e.g. explorer menu, export menu)
	 */
	private ArrayList<AbstractFractal> fractals;

	/*
	 * The active/selected fractal for the current fractal manager
	 */
	private AbstractFractal activeFractal;

	public FractalManager() {

		fractals = new ArrayList<AbstractFractal>();

		// Load and set default fractal
		loadDefaultFractals();
		setDefaultFractal();

	}

	/**
	 * Loads all fractals into the list
	 */
	public void loadDefaultFractals() {

		fractals.add(new JuliaFractal());
		fractals.add(new MandelBrotFractal());

	}

	/**
	 * Sets the default fractal to be displayed at startup, set in the
	 * settings.xml
	 */
	public void setDefaultFractal() {
		// TODO read from settings.xml
		activeFractal = fractals.get(0);
	}

	/**
	 * Add a fractal to the list of available fractals by passing an instance of
	 * that fractal
	 * 
	 * @param fractal
	 *            An instance of the fractal
	 */
	public void addFractal(AbstractFractal fractal) {
		fractals.add(fractal);
	}

	/**
	 * @return The fractal that has been marked as active
	 */
	public AbstractFractal getActiveFractal() {
		return activeFractal;
	}

	/**
	 * Sets the selected fractal according to the identifier.
	 * 
	 * @param identifier
	 *            The identifier of the fractal to be selected
	 */
	public void setActiveFractal(FractalIdentifier identifier) {

		for (AbstractFractal fractal : fractals) {
			if (fractal.getIdentifier() == identifier) {
				activeFractal = fractal;
				return;
			}
		}

		// TODO remove
		FractalPanel.getFractalPanel().requestUpdate();

	}

	/**
	 * @return A list of all loaded fractals in the fractal manager
	 */
	public ArrayList<AbstractFractal> getFractals() {
		return fractals;
	}

}
