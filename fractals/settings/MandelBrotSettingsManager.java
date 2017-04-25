package fractals.settings;

import fractals.MandelBrotFractal;
import gui.settings.FractalSettingsPanel;
import gui.settings.MandelBrotSettingsPanel;

public class MandelBrotSettingsManager implements SettingsManager {

	private MandelBrotFractal fractal;
	private MandelBrotSettingsPanel settingsPanel;

	public MandelBrotSettingsManager(MandelBrotFractal fractal) {

		this.fractal = fractal;
		this.settingsPanel = new MandelBrotSettingsPanel(fractal);

	}

	@Override
	public FractalSettingsPanel getSettingsPanel() {

		return settingsPanel;

	}

}
