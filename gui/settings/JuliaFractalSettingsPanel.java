package gui.settings;

import fractals.AbstractFractal;
import fractals.JuliaFractal;
import fractals.settings.SettingsManager;
import gui.settings.julia.ConstantTextField;
import gui.settings.julia.EscapeValueTextField;
import gui.settings.julia.MaxIterationsTextField;
import gui.settings.utilComponents.ColorSchemeSelectorBox;
import gui.settings.utilComponents.SettingItemPanel;

public class JuliaFractalSettingsPanel extends FractalSettingsPanel {

	private static final long serialVersionUID = 905291527554013054L;

	private ColorSchemeSelectorBox colorSchemeSelectorBox;

	public JuliaFractalSettingsPanel(AbstractFractal fractal, SettingsManager settingsManager) {

		super(fractal, settingsManager);

		this.colorSchemeSelectorBox = new ColorSchemeSelectorBox(fractal.getColorSchemeManager());

		buildGUI();

	}

	@Override
	protected void buildGUI() {

		JuliaFractal fractal = (JuliaFractal) this.fractal;

		super.buildGUI();

		SettingItemPanel colorSchemeSelectorBoxPanel = new SettingItemPanel(colorSchemeSelectorBox);
		add(colorSchemeSelectorBoxPanel);

		// Maximum number of iterations settings field
		MaxIterationsTextField maxIterationsTextField = new MaxIterationsTextField(this, settingsManager,
				"Max iterations", Integer.toString(fractal.getMaxIterations()));
		maxIterationsTextField.buildGUI();

		// Escape value settings field
		EscapeValueTextField escapeValueTextField = new EscapeValueTextField(this, settingsManager, "Escape value:",
				Double.toString(fractal.getEscapeValue()));
		escapeValueTextField.buildGUI();

		// Constant settings field
		String constantString = Double.toString(fractal.getConstant().getReal());
		constantString += (fractal.getConstant().getImaginary() < 0) ? "-" : "+";
		constantString += Double.toString(fractal.getConstant().getImaginary());
		constantString += "i";
		ConstantTextField constantTextField = new ConstantTextField(this, settingsManager, "Constant: ",
				constantString);
		constantTextField.buildGUI();

	}

}
