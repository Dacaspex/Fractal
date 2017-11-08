package fractals.colorSchemes.settings;

import fractals.colorSchemes.InsideOutsideColorScheme;
import fractals.settings.SettingsManager;
import fractals.settings.properties.Property;

public class InsideOutsideColorSchemeSettingsManager implements SettingsManager {

	private InsideOutsideColorScheme colorScheme;
	
	public InsideOutsideColorSchemeSettingsManager(InsideOutsideColorScheme colorScheme) {
		
		this.colorScheme = colorScheme;
		
	}
	
	@Override
	public Property<?>[] getProperties() {
		
		return new Property<?>[] {};
		
	}

	@Override
	public void updateProperties() {
		// TODO Auto-generated method stub
		
	}

}
