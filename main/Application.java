package main;

import gui.Display;

/**
 * This class is the main entry point of the program. Every class can access
 * elements of the program by requesting it from this class. It serves to
 * connect different modules.
 * 
 * @author Casper
 *
 */
public class Application {

	/*
	 * The current application
	 */
	private static Application instance;

	/*
	 * The display that holds all GUI components
	 */
	public Display display;

	private Application() {
	}

	public void start() {

		// Create GUI
		display = new Display();

	}
	
	public Display getDisplay() {
		return display;
	}

	/**
	 * Creates a new application
	 * 
	 * @return The newly created application
	 */
	protected static Application create() {
		instance = new Application();
		return instance;
	}

	/**
	 * This method accesses the current application
	 * 
	 * @return The application
	 */
	public static Application getApplication() {
		return instance;
	}

}
