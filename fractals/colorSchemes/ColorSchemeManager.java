package fractals.colorSchemes;

import java.util.HashMap;
import java.util.Map;

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

	public String[][] getAvailableColorSchemes() {

		String[][] list = new String[availableColorSchemes.size()][2];
		int index = 0;

		for (Map.Entry<String, AbstractColorScheme> entry : availableColorSchemes.entrySet()) {

			list[index][0] = entry.getValue().getIdentifier();
			list[index++][1] = entry.getValue().getName();

		}

		return list;

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
