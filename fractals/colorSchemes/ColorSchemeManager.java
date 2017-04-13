package fractals.colorSchemes;

import java.util.HashMap;

public class ColorSchemeManager {

	protected HashMap<String, AbstractColorScheme> availableColorSchemes;

	protected AbstractColorScheme activeColorScheme;

	public ColorSchemeManager() {

		availableColorSchemes = new HashMap<String, AbstractColorScheme>();

	}

	public AbstractColorScheme getActiveColorScheme() {

		return activeColorScheme;

	}
	
	public AbstractColorScheme getColorScheme(String identifier) {
		
		return availableColorSchemes.get(identifier);
		
	}

	public void setActiveColorScheme(String identifier) {

		activeColorScheme = availableColorSchemes.get(identifier);

	}

	public String[] getAvailableColorSchemes() {

		String[] availableColorSchemesNames = new String[availableColorSchemes.size()];
		return availableColorSchemes.keySet().toArray(availableColorSchemesNames);

	}

	public void addColorScheme(AbstractColorScheme colorScheme) {

		availableColorSchemes.put(colorScheme.getIdentifier(), colorScheme);

	}

	public void addColorScheme(AbstractColorScheme colorScheme, ColorSchemeManagerOptions setting) {

		availableColorSchemes.put(colorScheme.getIdentifier(), colorScheme);
		
		if (setting == ColorSchemeManagerOptions.SET_AS_ACTIVE) {
			
			setActiveColorScheme(colorScheme.getIdentifier());
			
		}

	}

}
