package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import fractals.AbstractFractal;
import fractals.FractalManager;
import gui.menuItems.AboutMenuItem;
import gui.menuItems.ExitMenuItem;
import gui.menuItems.FractalMenuItem;
import util.Settings;

public class Display extends JFrame {

	private static final long serialVersionUID = -2354333139035535931L;

	private final int DEFAULT_DISPLAY_WIDTH;
	private final int DEFAULT_DISPLAY_HEIGHT;

	private FractalManager fractalManager;
	private SettingsPanel settingsPanel;

	public Display() {

		// Initialize variables
		DEFAULT_DISPLAY_WIDTH = 1150;
		DEFAULT_DISPLAY_HEIGHT = 1000;

		// Load settings
		Settings.loadDefaultSettignsFile();

		// Initialize fractal manager
		fractalManager = new FractalManager();

		// Initialize the display
		buildGUI();
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	private void buildGUI() {

		// Set look and feel, size and title
		setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		setPreferredSize(new Dimension(DEFAULT_DISPLAY_WIDTH, DEFAULT_DISPLAY_HEIGHT));
		setTitle(Settings.getProgramName() + " - Version: " + Settings.getVersion());

		// Build main GUI
		buildMainPanels();
		buildTopMenu();

		// Finish the GUI
		pack();
		setVisible(true);

	}

	private void buildTopMenu() {

		JMenuBar menuBar = new JMenuBar();

		// File menu
		JMenu fileMenu = new JMenu("File");
		ExitMenuItem exitMenuItem = new ExitMenuItem();
		fileMenu.add(exitMenuItem);
		menuBar.add(fileMenu);

		// Fractal menu
		JMenu fractalMenu = new JMenu("Fractals");

		ArrayList<AbstractFractal> fractals = fractalManager.getFractals();
		for (AbstractFractal fractal : fractals) {

			fractalMenu.add(new FractalMenuItem(fractal, fractalManager,
					settingsPanel));

		}

		menuBar.add(fractalMenu);

		// About menu
		JMenu aboutMenu = new JMenu("About");
		AboutMenuItem aboutMenuItem = new AboutMenuItem();
		aboutMenu.add(aboutMenuItem);
		menuBar.add(aboutMenu);

		setJMenuBar(menuBar);

	}

	private void buildMainPanels() {

		// The explorer panel holds the settings panel and fractal panel
		JPanel explorerPanel = new JPanel(new BorderLayout());
		settingsPanel = new SettingsPanel(fractalManager);
		FractalPanel fractalPanel = new FractalPanel(fractalManager, settingsPanel);

		explorerPanel.add(settingsPanel, BorderLayout.LINE_START);
		explorerPanel.add(fractalPanel);
		add(explorerPanel);

	}

	public void setLookAndFeel(String className) {

		try {

			UIManager.setLookAndFeel(className);

		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {

			// TODO print error
			e.printStackTrace();
			System.exit(0);

		}

	}

}
