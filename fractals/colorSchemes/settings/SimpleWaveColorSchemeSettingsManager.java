package fractals.colorSchemes.settings;

import javax.swing.event.DocumentEvent;

import fractals.colorSchemes.SimpleWaveColorScheme;
import fractals.settings.SettingsManager;
import gui.FractalPanel;
import gui.settings.utilComponents.SettingItemComponent;
import gui.settings.utilComponents.TextFieldComponent;

public class SimpleWaveColorSchemeSettingsManager implements SettingsManager {

	public SimpleWaveColorScheme simpleWaveColorScheme;

	public SimpleWaveColorSchemeSettingsManager(SimpleWaveColorScheme simpleWaveColorScheme) {

		this.simpleWaveColorScheme = simpleWaveColorScheme;

	}

	public void setFrequencyRed(String frequencyRedString) {

		try {
			double frequencyRed = Double.parseDouble(frequencyRedString);
			if (frequencyRed < 0) {
				return;
			}
			simpleWaveColorScheme.setFrequencyRed(frequencyRed);
			FractalPanel.getFractalPanel().requestUpdate();
		} catch (NumberFormatException exception) {
			return;
		}

	}
	
	public void setFrequencyGreen(String frequencyGreenString) {

		try {
			double frequencyRed = Double.parseDouble(frequencyGreenString);
			if (frequencyRed < 0) {
				return;
			}
			simpleWaveColorScheme.setFrequencyGreen(frequencyRed);
			FractalPanel.getFractalPanel().requestUpdate();
		} catch (NumberFormatException exception) {
			return;
		}

	}
	
	public void setFrequencyBlue(String frequencyBlueString) {

		try {
			double frequencyRed = Double.parseDouble(frequencyBlueString);
			if (frequencyRed < 0) {
				return;
			}
			simpleWaveColorScheme.setFrequencyBlue(frequencyRed);
			FractalPanel.getFractalPanel().requestUpdate();
		} catch (NumberFormatException exception) {
			return;
		}

	}

	@Override
	public SettingItemComponent[] getSettingComponents() {

		// Frequency red
		TextFieldComponent frequencyRedTextField = new TextFieldComponent(
				Double.toString(simpleWaveColorScheme.getFrequencyRed())) {
			private static final long serialVersionUID = -6033309334749328555L;

			@Override
			public void documentUpdate(DocumentEvent event) {
				setFrequencyRed(this.getText());
			}
		};
		SettingItemComponent frequencyRedSettingItem = new SettingItemComponent("Frequency red:",
				frequencyRedTextField);

		// Frequency green
		TextFieldComponent frequencyGreenTextField = new TextFieldComponent(
				Double.toString(simpleWaveColorScheme.getFrequencyGreen())) {
			private static final long serialVersionUID = -6033309334749328555L;

			@Override
			public void documentUpdate(DocumentEvent event) {
				setFrequencyGreen(this.getText());
			}
		};
		SettingItemComponent frequencyGreenSettingItem = new SettingItemComponent("Frequency green:",
				frequencyGreenTextField);

		// Frequency blue
		TextFieldComponent frequencyBlueTextField = new TextFieldComponent(
				Double.toString(simpleWaveColorScheme.getFrequencyBlue())) {
			private static final long serialVersionUID = -6033309334749328555L;

			@Override
			public void documentUpdate(DocumentEvent event) {
				setFrequencyBlue(this.getText());
			}
		};
		SettingItemComponent frequencyBlueSettingItem = new SettingItemComponent("Frequency blue:",
				frequencyBlueTextField);

		return new SettingItemComponent[] { frequencyRedSettingItem, frequencyGreenSettingItem, frequencyBlueSettingItem };

	}

}
