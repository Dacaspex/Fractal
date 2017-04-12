package fractals.colorSchemes;

import java.util.HashMap;

public class ColorSchemeManager {
	
	public enum ColorSchemeSettings {
		
		SET_AS_CURRENT
		
	}

	protected HashMap<String, AbstractColorScheme> availableColorSchemes;

	protected AbstractColorScheme colorScheme;

	public ColorSchemeManager() {

		availableColorSchemes = new HashMap<String, AbstractColorScheme>();

	}

	public AbstractColorScheme getColorScheme() {

		return colorScheme;

	}

	public void setColorScheme(String identifier) {

		colorScheme = availableColorSchemes.get(identifier);

	}

	public String[] getAvailableColorSchemes() {

		String[] availableColorSchemesNames = new String[availableColorSchemes.size()];
		return availableColorSchemes.keySet().toArray(availableColorSchemesNames);

	}

	public void addColorScheme(AbstractColorScheme colorScheme) {

		availableColorSchemes.put(colorScheme.getIdentifier(), colorScheme);

	}

	public void addColorScheme(AbstractColorScheme colorScheme, ColorSchemeSettings setting) {

		availableColorSchemes.put(colorScheme.getIdentifier(), colorScheme);
		
		if (setting == ColorSchemeSettings.SET_AS_CURRENT) {
			
			setColorScheme(colorScheme.getIdentifier());
			
		}

	}
	
	

}
