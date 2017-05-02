package gui.settings.mandelBrot;

import javax.swing.event.DocumentEvent;

import fractals.settings.MandelBrotSettingsManager;
import fractals.settings.SettingsManager;
import gui.settings.FractalSettingsPanel;
import gui.settings.utilComponents.AbstractSettingTextField;

public class MaxIterationsTextField extends AbstractSettingTextField {

	public MaxIterationsTextField(FractalSettingsPanel settingsPanel, SettingsManager settingsManager,
			String description, String defaultText) {

		super(settingsPanel, settingsManager, description, defaultText);

	}

	@Override
	public void documentUpdate(DocumentEvent event) {

		((MandelBrotSettingsManager) settingsManager).setMaxIterations(textField.getText());

	}

}
