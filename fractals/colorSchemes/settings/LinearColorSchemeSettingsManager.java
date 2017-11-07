package fractals.colorSchemes.settings;

import fractals.colorSchemes.LinearColorScheme;
import fractals.settings.SettingsManager;
import fractals.settings.properties.Property;

public class LinearColorSchemeSettingsManager implements SettingsManager {

	private LinearColorScheme linearColorScheme;

	public LinearColorSchemeSettingsManager(LinearColorScheme linearColorScheme) {

		this.linearColorScheme = linearColorScheme;

	}

	@Override
	public Property<?>[] getProperties() {

		return null;

	}

	@Override
	public void updateProperties() {

	}

}
