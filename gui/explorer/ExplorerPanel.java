package gui.explorer;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JMenu;
import javax.swing.JPanel;

import fractals.AbstractFractal;
import fractals.FractalManager;
import gui.FractalPanel;
import gui.SettingsPanel;
import gui.menuItems.FractalMenuItem;

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

	/*
	 * Side bar menu that houses all settings; both fractal and color scheme
	 * settings.
	 */
	private SettingsPanel settingsPanel;

	public ExplorerPanel() {

		fractalManager = new FractalManager();
		fractalPanel = new FractalPanel(fractalManager);
		settingsPanel = new SettingsPanel(fractalManager);

		buildGUI();

	}

	/**
	 * Builds the explorer panel with all it's subcomponents
	 */
	public void buildGUI() {

		setLayout(new BorderLayout());
		add(settingsPanel, BorderLayout.LINE_START);
		add(fractalPanel);

	}

	/**
	 * Shows the settings panel by adding it back to the explorer panel
	 */
	public void showSettingsPanel() {
		add(settingsPanel, BorderLayout.LINE_START);
	}

	/**
	 * Removes the settings panel by removing it from the explorer panel
	 */
	public void hideSettingsPanel() {
		remove(settingsPanel);
	}

	/**
	 * Utility method to supply the screen with a menu to switch between the
	 * fractals.
	 * 
	 * @see Display
	 * 
	 * @return A menu containing menu items that switch between the fractals
	 */
	public JMenu getFractalMenu() {

		JMenu fractalMenu = new JMenu("Fractals");
		ArrayList<AbstractFractal> fractals = fractalManager.getFractals();

		for (AbstractFractal fractal : fractals) {
			// TODO simplify this constructor
			fractalMenu.add(new FractalMenuItem(fractal, fractalManager, settingsPanel));
		}

		return fractalMenu;

	}

}
