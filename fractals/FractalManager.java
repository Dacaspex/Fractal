package fractals;

import java.util.Map;
import java.util.TreeMap;

import gui.FractalPanel;
import util.Settings;

public class FractalManager {

	private TreeMap<String, AbstractFractal> fractalList;
	private AbstractFractal activeFractal;

	public FractalManager() {

		// Initialize variables
		fractalList = new TreeMap<String, AbstractFractal>();

		// Load and set default fractal
		loadDefaultFractals();
		setDefaultFractal();

	}

	/**
	 * Loads the default fractals into the list
	 */
	public void loadDefaultFractals() {

		fractalList.put("JuliaSet1", new JuliaFractal());
		fractalList.put("MandelBrotSet1", new MandelBrotFractal());

	}

	/**
	 * Sets the default fractal to be displayed at startup, set in the
	 * settings.xml
	 */
	public void setDefaultFractal() {

		activeFractal = Settings.getDefaultFractal(this);

	}

	/**
	 * Add a fractal to the list of available fractals by it's identifier and
	 * the fractal itself
	 * 
	 * @param identifier
	 *            The identifier of the fractal. Should be a unique identifier
	 * @param fractal
	 *            An instance of the fractal
	 */
	public void addFractal(String identifier, AbstractFractal fractal) {

		fractalList.put(identifier, fractal);

	}

	/**
	 * @return The currently selected fractal
	 */
	public AbstractFractal getActiveFractal() {

		return activeFractal;

	}

	/**
	 * Sets the selected fractal to be drawn. Also updates the screen to draw
	 * the new fractal
	 * 
	 * @param identifier
	 *            The identifier of the fractal to be selected
	 */
	public void setActiveFractal(String identifier) {

		activeFractal = fractalList.get(identifier);
		FractalPanel.getFractalPanel().requestUpdate();

	}

	/**
	 * @return A list of names of the loaded fractals
	 */
	public String[][] getLoadedFractals() {

		String[][] list = new String[fractalList.size()][2];
		int index = 0;

		for (Map.Entry<String, AbstractFractal> entry : fractalList.entrySet()) {

			list[index][0] = entry.getValue().getIdentifier();
			list[index++][1] = entry.getValue().getName();

		}

		return list;

	}

	public AbstractFractal getFractalByIdentifier(String identifier) {

		if (!identifier.equals("")) {

			return fractalList.get(identifier);

		} else {

			return fractalList.firstEntry().getValue();

		}

	}

	public enum FractalGeneratingState {
		IDLE, GENERATING_IMAGE, STITCHING_IMAGE
	}

}
