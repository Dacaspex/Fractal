package fractals.settings;

import gui.settings.FractalSettingsPanel;
import gui.settings.utilComponents.SettingItemPanel;

public interface SettingsManager {
	
	@Deprecated
	public FractalSettingsPanel getSettingsPanel();
	
	public SettingItemPanel[] getSettingComponents();

}
