package gui.settings;

import javax.swing.JTextField;

import fractals.AbstractFractal;
import gui.settings.utilComponents.ColorSchemeSelectorBox;
import gui.settings.utilComponents.SettingItemPanel;

public class JuliaFractalSettingsPanel extends FractalSettingsPanel {

	private static final long serialVersionUID = 905291527554013054L;

	private ColorSchemeSelectorBox colorSchemeSelectorBox;

	public JuliaFractalSettingsPanel(AbstractFractal fractal) {

		super(fractal);

		this.colorSchemeSelectorBox = new ColorSchemeSelectorBox(fractal.getColorSchemeManager());

		buildGUI();

	}

	@Override
	protected void buildGUI() {

		super.buildGUI();

		SettingItemPanel colorSchemeSelectorBoxPanel = new SettingItemPanel(colorSchemeSelectorBox);
		add(colorSchemeSelectorBoxPanel);

	}

}
