package gui.settings;

import fractals.AbstractFractal;
import fractals.MandelBrotFractal;
import fractals.settings.SettingsManager;
import gui.settings.mandelBrot.EscapeValueTextField;
import gui.settings.mandelBrot.MaxIterationsTextField;
import gui.settings.utilComponents.ColorSchemeSelectorBox;
import gui.settings.utilComponents.SettingItemPanel;

public class MandelBrotSettingsPanel extends FractalSettingsPanel {

	private static final long serialVersionUID = 794795730103012414L;

	private ColorSchemeSelectorBox colorSchemeSelectorBox;

	public MandelBrotSettingsPanel(AbstractFractal fractal, SettingsManager settingsManager) {

		super(fractal, settingsManager);

		this.colorSchemeSelectorBox = new ColorSchemeSelectorBox(fractal.getColorSchemeManager());

		buildGUI();

	}

	@Override
	protected void buildGUI() {

		MandelBrotFractal fractal = (MandelBrotFractal) this.fractal;

		super.buildGUI();

		SettingItemPanel colorSchemeSelectorBoxPanel = new SettingItemPanel(colorSchemeSelectorBox);
		add(colorSchemeSelectorBoxPanel);

		// Maximum number of iterations
		MaxIterationsTextField maxIterationsField = new MaxIterationsTextField(this, settingsManager,
				"Maximum number of iterations:", Integer.toString(fractal.getMaxIterations()));
		maxIterationsField.buildGUI();

		// Escape value
		EscapeValueTextField escapeValueTextField = new EscapeValueTextField(this, settingsManager, "Escape value:",
				Double.toString(fractal.getEscapeValue()));
		escapeValueTextField.buildGUI();

	}

}
