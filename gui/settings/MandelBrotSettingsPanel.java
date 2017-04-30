package gui.settings;

import javax.swing.JLabel;

import fractals.AbstractFractal;
import fractals.settings.SettingsManager;

public class MandelBrotSettingsPanel extends FractalSettingsPanel {

	private static final long serialVersionUID = 794795730103012414L;

	public MandelBrotSettingsPanel(AbstractFractal fractal, SettingsManager settingsManager) {

		super(fractal, settingsManager);

	}
	
	@Override
	protected void buildGUI() {
		
		super.buildGUI();
		
		add(new JLabel("Mandelbrot Settings"));
		
	}

}
