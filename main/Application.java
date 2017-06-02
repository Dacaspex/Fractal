package main;

import gui.Display;

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

	/**
	 * Creates a new application
	 * 
	 * @return The newly created application
	 */
	public static Application create() {
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
