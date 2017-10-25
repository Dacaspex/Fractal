package fractals.settings;

import fractals.settings.properties.Property;
import gui.settings.utilComponents.SettingItemComponent;

public interface SettingsManager {

	@Deprecated
	public SettingItemComponent[] getSettingComponents();

	public Property<?>[] getProperties();
	
}
