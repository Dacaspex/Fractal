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
			simpleWaveColorScheme.setFrequencyRed(frequencyRed);
			FractalPanel.getFractalPanel().requestUpdate();
		} catch (NumberFormatException exception) {
			return;
		}

	}

	public void setFrequencyGreen(String frequencyGreenString) {

		try {
			double frequencyGreen = Double.parseDouble(frequencyGreenString);
			simpleWaveColorScheme.setFrequencyGreen(frequencyGreen);
			FractalPanel.getFractalPanel().requestUpdate();
		} catch (NumberFormatException exception) {
			return;
		}

	}

	public void setFrequencyBlue(String frequencyBlueString) {

		try {
			double frequencyBlue = Double.parseDouble(frequencyBlueString);
			simpleWaveColorScheme.setFrequencyBlue(frequencyBlue);
			FractalPanel.getFractalPanel().requestUpdate();
		} catch (NumberFormatException exception) {
			return;
		}

	}

	public void setPhaseRed(String phaseRedString) {

		try {
			double phaseRed = Double.parseDouble(phaseRedString);
			simpleWaveColorScheme.setPhaseRed(phaseRed);
			FractalPanel.getFractalPanel().requestUpdate();
		} catch (NumberFormatException exception) {
			return;
		}

	}

	public void setPhaseGreen(String phaseGreenString) {

		try {
			double phaseGreen = Double.parseDouble(phaseGreenString);
			simpleWaveColorScheme.setPhaseGreen(phaseGreen);
			FractalPanel.getFractalPanel().requestUpdate();
		} catch (NumberFormatException exception) {
			return;
		}

	}

	public void setPhaseBlue(String phaseBlueString) {

		try {
			double phaseBlue = Double.parseDouble(phaseBlueString);
			simpleWaveColorScheme.setPhaseBlue(phaseBlue);
			FractalPanel.getFractalPanel().requestUpdate();
		} catch (NumberFormatException exception) {
			return;
		}

	}

	public void setCenter(String centerString) {

		try {
			double center = Double.parseDouble(centerString);
			simpleWaveColorScheme.setCenter(center);
			FractalPanel.getFractalPanel().requestUpdate();
		} catch (NumberFormatException exception) {
			return;
		}

	}

	public void setDelta(String deltaString) {

		try {
			double delta = Double.parseDouble(deltaString);
			simpleWaveColorScheme.setDelta(delta);
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

		// Phase red
		TextFieldComponent phaseRedTextField = new TextFieldComponent(
				Double.toString(simpleWaveColorScheme.getPhaseRed())) {
			private static final long serialVersionUID = -6033309334749328555L;

			@Override
			public void documentUpdate(DocumentEvent event) {
				setPhaseRed(this.getText());
			}
		};
		SettingItemComponent phaseRedSettingItem = new SettingItemComponent("Phase red:", phaseRedTextField);

		// Phase Green
		TextFieldComponent phaseGreenTextField = new TextFieldComponent(
				Double.toString(simpleWaveColorScheme.getPhaseGreen())) {
			private static final long serialVersionUID = -6033309334749328555L;

			@Override
			public void documentUpdate(DocumentEvent event) {
				setPhaseGreen(this.getText());
			}
		};
		SettingItemComponent phaseGreenSettingItem = new SettingItemComponent("Phase green:", phaseGreenTextField);

		// Phase Blue
		TextFieldComponent phaseBlueTextField = new TextFieldComponent(
				Double.toString(simpleWaveColorScheme.getPhaseBlue())) {
			private static final long serialVersionUID = -6033309334749328555L;

			@Override
			public void documentUpdate(DocumentEvent event) {
				setPhaseBlue(this.getText());
			}
		};
		SettingItemComponent phaseBlueSettingItem = new SettingItemComponent("Phase blue:", phaseBlueTextField);

		// Center
		TextFieldComponent centerTextField = new TextFieldComponent(
				Double.toString(simpleWaveColorScheme.getCenter())) {
			private static final long serialVersionUID = -6033309334749328555L;

			@Override
			public void documentUpdate(DocumentEvent event) {
				setCenter(this.getText());
			}
		};
		SettingItemComponent centerSettingItem = new SettingItemComponent("Center:", centerTextField);

		// Delta
		TextFieldComponent deltaTextField = new TextFieldComponent(Double.toString(simpleWaveColorScheme.getDelta())) {
			private static final long serialVersionUID = -6033309334749328555L;

			@Override
			public void documentUpdate(DocumentEvent event) {
				setDelta(this.getText());
			}
		};
		SettingItemComponent deltaSettingItem = new SettingItemComponent("Delta:", deltaTextField);

		return new SettingItemComponent[] { frequencyRedSettingItem, frequencyGreenSettingItem,
				frequencyBlueSettingItem, phaseRedSettingItem, phaseGreenSettingItem, phaseBlueSettingItem,
				centerSettingItem, deltaSettingItem };

	}

}
