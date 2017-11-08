package main;

import gui.Display;
import gui.menuItems.FractalMenuItem;
import util.logger.Logger;
import util.logger.Logger.Type;

/**
 * <p>
 * This class is the main entry point of the program. Every class can access
 * elements of the program by requesting it from this class. It serves to
 * connect different modules.
 * </p>
 * </p>
 * Furthermore, when an update is requested by one of the components (e.g.
 * Explorer panel, export image, etc), the {@link #update()} makes sure the
 * correct GUI component is updated accordingly. For example, when a settings
 * manager requests an update of the fractal, the update method is called. Then,
 * depending on the mode of the program, the correct image will be updated.
 * </p>
 * <p>
 * Although, this behavior could be ignored in some cases. Then the correct GUI
 * components should be called and updated correctly. An example is given below
 * which is taken from
 * {@link FractalMenuItem#actionPerformed(java.awt.event.ActionEvent)}.
 * </p>
 * <code>
 * // Code to update the explorer panel <br>
 * Application.getApplication().getDisplay().getExplorerPanel().update();
 * </code>
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
	 * Describes the application mode
	 */
	private ApplicationMode applicationMode;

	/*
	 * The display that holds all GUI components
	 */
	private Display display;

	public Display getDisplay() {
		return display;
	}

	/*
	 * Starts up the program by creating the display.
	 */
	public void start() {

		applicationMode = ApplicationMode.EXPLORER;
		display = new Display();
		display.buildGUI();

		Logger.log(this, "Application started successfully", Type.LOG_SUCCESS);

	}

	/**
	 * Updates the correct GUI components of the application depending on the
	 * mode. The <code>updateGUI</code> flag can be set to true. Then the
	 * program will update any GUI component that has to do with the update.
	 * E.g. setting menu components.
	 * 
	 * @param updateGUI
	 *            If set to true, GUI components will be updated as well.
	 */
	public void update(boolean updateGUI) {

		switch (applicationMode) {
		case EXPLORER:
			display.update();
			break;
		case EXPORT_IMAGE:
			break;
		case RENDER_VIDEO:
			break;
		default:
			break;
		}

	}

	/**
	 * Creates a new application. Should only be used once.
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

	/**
	 * Describes the application mode.
	 */
	public enum ApplicationMode {
		// Explorer mode, main function of the program
		EXPLORER,
		// Export mode of an image
		EXPORT_IMAGE,
		// Export mode of a video
		RENDER_VIDEO,
	}

}
