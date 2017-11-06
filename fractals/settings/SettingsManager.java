package fractals.settings;

import fractals.settings.properties.Property;

public interface SettingsManager {

	public Property<?>[] getProperties();
	
	public void updateProperties();
	
}
