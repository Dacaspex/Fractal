package gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import gui.menuItems.ExitMenuItem;
import util.Settings;

public class Display extends JFrame {

	private static final long serialVersionUID = -2354333139035535931L;
	
	private final int DEFAULT_DISPLAY_WIDTH;
	private final int DEFAULT_DISPLAY_HEIGHT;

	FractalPanel fractalPanel;
	
	public Display() {
		
		// Initialize variables
		DEFAULT_DISPLAY_WIDTH = 1000;
		DEFAULT_DISPLAY_HEIGHT = 1000;
		
		// Load settings
		Settings.loadDefaultSettigns();
		
		// Initialize the display
		buildGUI();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void buildGUI() {
		
		setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		setPreferredSize(new Dimension(DEFAULT_DISPLAY_WIDTH, DEFAULT_DISPLAY_HEIGHT));
		setTitle(Settings.getProgramName() + " - Version: " + Settings.getVersion());
		
		fractalPanel = new FractalPanel();
		add(fractalPanel);
		
		JMenuBar menuBar = new JMenuBar();
		
		// File menu
		JMenu fileMenu = new JMenu("File");
		ExitMenuItem exitMenuItem = new ExitMenuItem();
		fileMenu.add(exitMenuItem);
		menuBar.add(fileMenu);
		setJMenuBar(menuBar);
		
		pack();
		setVisible(true);
		
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
