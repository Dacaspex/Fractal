package gui.settings.julia;

import javax.swing.event.DocumentEvent;

import fractals.settings.JuliaSettingsManager;
import fractals.settings.SettingsManager;
import gui.settings.FractalSettingsPanel;
import gui.settings.utilComponents.AbstractSettingTextField;

public class ConstantTextField extends AbstractSettingTextField {

	public ConstantTextField(FractalSettingsPanel settingsPanel, SettingsManager settingsManager, String description,
			String defaultText) {

		super(settingsPanel, settingsManager, description, defaultText);

	}

	@Override
	public void documentUpdate(DocumentEvent event) {

		((JuliaSettingsManager) settingsManager).setConstant(textField.getText());

	}

}
