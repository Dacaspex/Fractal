package gui.explorer;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import fractals.FractalManager;

/**
 * This panel is the main panel of the program. It shows on startup and provides
 * interaction between the user and the software. Via this panel, the user can
 * interact with the fractals in various ways:
 * 
 * <ul>
 * <li>Zoom in</li>
 * <li>Zoom out</li>
 * <li>Translate the center point of the fractal</li>
 * <li>Edit the fractal settings, provided by the fractal</li>
 * <li>Edit the active color scheme settings</li>
 * </ul>
 * 
 * @author Casper
 *
 */
public class ExplorerPanel extends JPanel {

	private static final long serialVersionUID = -6898782977195248388L;

	/*
	 * The fractal manager that is used by the explorer panel. Saves fractals
	 * and their own settings.
	 */
	private FractalManager fractalManager;

	/*
	 * Panel that displays the rendered fractals.
	 */
	private FractalPanel fractalPanel;

	public ExplorerPanel() {

		fractalManager = new FractalManager();
		fractalPanel = new FractalPanel(fractalManager);

		buildGUI();

	}

	public FractalManager getFractalManager() {
		return fractalManager;
	}

	/**
	 * Builds the explorer panel with all it's subcomponents
	 */
	public void buildGUI() {

		setLayout(new BorderLayout());
		add(fractalPanel);

	}

	public void update() {

		fractalPanel.update();

	}

}
