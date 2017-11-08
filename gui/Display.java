package gui;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import fractals.AbstractFractal;
import gui.explorer.ExplorerPanel;
import gui.menuItems.AboutMenuItem;
import gui.menuItems.ExitMenuItem;
import gui.menuItems.FractalMenuItem;
import gui.menuItems.OpenFractalSettingsMenuItem;
import gui.settings.fractalSettings.FractalSettingsDisplay;
import util.Settings;

/**
 * This class provides the main functionalities of the GUI. It builds the main
 * panel; the explorer panel and sets the look-and-feel of the program.
 * Furthermore, it builds the menu bar and fills in all information needed for
 * this.
 * 
 * Moreover, it will provide functionality to call the export and video render
 * frames.
 * 
 * @author Casper
 *
 */
public class Display extends JFrame {

	private static final long serialVersionUID = -2354333139035535931L;

	/*
	 * Default display (window) width
	 */
	private final int DEFAULT_DISPLAY_WIDTH;

	/*
	 * Default display (window) height
	 */
	private final int DEFAULT_DISPLAY_HEIGHT;

	/*
	 * Main window of the program, the explorer panel. This panel allows
	 * interaction between the user and different fractals, as well as settings
	 * can be edited.
	 */
	private ExplorerPanel explorerPanel;

	private FractalSettingsDisplay fractalSettingsDisplay;

	private JMenuBar menuBar;

	public Display() {

		// Load settings
		Settings.loadSettignsFile();

		// Initialize variables and components
		DEFAULT_DISPLAY_WIDTH = 1150;
		DEFAULT_DISPLAY_HEIGHT = 1000;
		explorerPanel = new ExplorerPanel();
		menuBar = new JMenuBar();

	}

	public ExplorerPanel getExplorerPanel() {
		return explorerPanel;
	}

	public void openFractalSettings() {
		fractalSettingsDisplay.open();
	}

	public void closeFractalSettings() {
		fractalSettingsDisplay.close();
	}

	public void update(boolean updateGUI) {

		explorerPanel.update();

		if (updateGUI) {
			fractalSettingsDisplay.update();
		}

	}

	public void buildGUI() {

		// Set look and feel, size and title
		setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		setPreferredSize(new Dimension(DEFAULT_DISPLAY_WIDTH, DEFAULT_DISPLAY_HEIGHT));
		setTitle(Settings.getProgramName() + " - Version: " + Settings.getVersion());
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Build main GUI
		add(explorerPanel);
		buildTopMenu();

		// Finish the GUI
		pack();
		setVisible(true);

		fractalSettingsDisplay = new FractalSettingsDisplay();

	}

	/**
	 * Creates the top menu bar and adds it to the display.
	 */
	private void buildTopMenu() {

		// File menu
		JMenu fileMenu = new JMenu("File");
		ExitMenuItem exitMenuItem = new ExitMenuItem();
		fileMenu.add(exitMenuItem);
		menuBar.add(fileMenu);

		// Fractal menu
		createFractalMenuItem();

		// About menu
		JMenu aboutMenu = new JMenu("About");
		AboutMenuItem aboutMenuItem = new AboutMenuItem();
		aboutMenu.add(aboutMenuItem);
		menuBar.add(aboutMenu);

		setJMenuBar(menuBar);

	}

	private void createFractalMenuItem() {

		JMenu fractalMenu = new JMenu("Fractals");
		ArrayList<AbstractFractal> fractals = explorerPanel.getFractalManager().getFractals();

		fractalMenu.add(new OpenFractalSettingsMenuItem());
		fractalMenu.addSeparator();

		for (AbstractFractal fractal : fractals) {
			fractalMenu.add(new FractalMenuItem(fractal, explorerPanel.getFractalManager()));
		}

		menuBar.add(fractalMenu);

	}

	/**
	 * Sets the look and feel of the program
	 * 
	 * @param className
	 *            The name of the look and feel class
	 */
	private void setLookAndFeel(String className) {

		try {
			UIManager.setLookAndFeel(className);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			JOptionPane.showMessageDialog(this, "Unsupported look and feel detected. Could not boot program.", "Error",
					JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}

	}

}
