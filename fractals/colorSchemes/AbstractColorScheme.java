package fractals.colorSchemes;

public abstract class AbstractColorScheme {

	/**
	 * Unique name of the color scheme to distinguish between different color
	 * schemes.
	 */
	protected String name;

	public String getName() {

		return name;

	}

	/**
	 * This method should return a integer RGB value for the pixel on the image.
	 * This can be calculated with a number of different parameters, hence the
	 * Object list. Objects should be cast to their corresponding type and can
	 * then be used
	 * 
	 * @param object The list of parameters on which the color depends
	 * @return Returns an integer value of a RGB color. 
	 */
	public abstract int getRGBValue(Object[] object);

}
