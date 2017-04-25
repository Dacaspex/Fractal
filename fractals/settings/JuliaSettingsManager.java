package fractals.settings;

import fractals.JuliaFractal;
import gui.settings.FractalSettingsPanel;
import gui.settings.JuliaFractalSettingsPanel;

public class JuliaSettingsManager implements SettingsManager {

	private JuliaFractal juliaFractal;
	private JuliaFractalSettingsPanel settingsPanel;
	
	public JuliaSettingsManager(JuliaFractal juliafractal) {
		
		this.juliaFractal = juliafractal;
		this.settingsPanel = new JuliaFractalSettingsPanel(juliaFractal);
		
	}
	
	@Override
	public FractalSettingsPanel getSettingsPanel() {

		return settingsPanel;

	}
	
}
