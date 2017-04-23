package fractals.colorSchemes;

public abstract class AbstractColorScheme {

	/**
	 * Unique identifier of the color scheme to distinguish between different color
	 * schemes.
	 */
	protected String identifier;
	
	/**
	 * Name to be displayed on the GUI
	 */
	protected String name;

	public String getIdentifier() {
		
		return identifier;
		
	}
	
	public String getName() {

		return name;

	}

	/**
	 * TODO write documentation
	 */
	public abstract int getRGBValue(double value);

}
