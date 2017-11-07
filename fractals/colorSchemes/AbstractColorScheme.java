package fractals.colorSchemes;

import fractals.settings.SettingsManager;

public abstract class AbstractColorScheme {

	/**
	 * Unique identifier of the color scheme to distinguish between different
	 * color schemes.
	 */
	protected String identifier;

	/**
	 * Name to be displayed on the GUI
	 */
	protected String name;

	protected SettingsManager settingsManager;

	public String getIdentifier() {
		return identifier;
	}

	public String getName() {
		return name;
	}

	public SettingsManager getSettingsManager() {
		return settingsManager;
	}

	/**
	 * TODO write documentation
	 */
	public abstract int getRGBValue(float value);

}
