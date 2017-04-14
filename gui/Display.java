package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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

	public Display() {

		// Initialize variables
		DEFAULT_DISPLAY_WIDTH = 1000;
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

		setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		setPreferredSize(new Dimension(DEFAULT_DISPLAY_WIDTH, DEFAULT_DISPLAY_HEIGHT));
		setTitle(Settings.getProgramName() + " - Version: " + Settings.getVersion());
		
		buildTopMenu();
		buildMainPanels();
		
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

		String[][] fractalIdentificationList = fractalManager.getLoadedFractals();
		for (String[] fractalIdentification : fractalIdentificationList) {

			fractalMenu.add(new FractalMenuItem(fractalIdentification[0], fractalIdentification[1], fractalManager));

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
		
		JPanel explorerPanel = new JPanel(new BorderLayout());
		JPanel settingsPanel = new SettingsPanel(fractalManager);
		FractalPanel fractalPanel = new FractalPanel(fractalManager);
		
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

		}

	}

}
