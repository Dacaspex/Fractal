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

		return new Property<?>[] {};

	}

	@Override
	public void updateProperties() {

	}

}
