package fractals.colorSchemes.settings;

import java.awt.event.ActionEvent;
import java.util.Random;

import javax.swing.event.DocumentEvent;

import fractals.colorSchemes.WaveColorScheme;
import fractals.settings.SettingsManager;
import fractals.settings.properties.Property;
import gui.settings.utilComponents.ButtonComponent;
import gui.settings.utilComponents.ColorPickerButton;
import gui.settings.utilComponents.SettingItemComponent;
import gui.settings.utilComponents.TextFieldComponent;
import main.Application;

public class WaveColorSchemeSettingsManager implements SettingsManager {

	private WaveColorScheme simpleWaveColorScheme;

	private TextFieldComponent frequencyRedTextField;
	private TextFieldComponent frequencyGreenTextField;
	private TextFieldComponent frequencyBlueTextField;
	private TextFieldComponent phaseRedTextField;
	private TextFieldComponent phaseGreenTextField;
	private TextFieldComponent phaseBlueTextField;
	private TextFieldComponent centerTextField;
	private TextFieldComponent deltaTextField;

	public WaveColorSchemeSettingsManager(WaveColorScheme simpleWaveColorScheme) {

		this.simpleWaveColorScheme = simpleWaveColorScheme;

	}

	public void setFrequencyRed(String frequencyRedString) {

		try {
			double frequencyRed = Double.parseDouble(frequencyRedString);
			simpleWaveColorScheme.setFrequencyRed(frequencyRed);
			Application.getApplication().update(false);
		} catch (NumberFormatException exception) {
			return;
		}

	}

	public void setFrequencyGreen(String frequencyGreenString) {

		try {
			double frequencyGreen = Double.parseDouble(frequencyGreenString);
			simpleWaveColorScheme.setFrequencyGreen(frequencyGreen);
			Application.getApplication().update(false);
		} catch (NumberFormatException exception) {
			return;
		}

	}

	public void setFrequencyBlue(String frequencyBlueString) {

		try {
			double frequencyBlue = Double.parseDouble(frequencyBlueString);
			simpleWaveColorScheme.setFrequencyBlue(frequencyBlue);
			Application.getApplication().update(false);
		} catch (NumberFormatException exception) {
			return;
		}

	}

	public void setPhaseRed(String phaseRedString) {

		try {
			double phaseRed = Double.parseDouble(phaseRedString);
			simpleWaveColorScheme.setPhaseRed(phaseRed);
			Application.getApplication().update(false);
		} catch (NumberFormatException exception) {
			return;
		}

	}

	public void setPhaseGreen(String phaseGreenString) {

		try {
			double phaseGreen = Double.parseDouble(phaseGreenString);
			simpleWaveColorScheme.setPhaseGreen(phaseGreen);
			Application.getApplication().update(false);
		} catch (NumberFormatException exception) {
			return;
		}

	}

	public void setPhaseBlue(String phaseBlueString) {

		try {
			double phaseBlue = Double.parseDouble(phaseBlueString);
			simpleWaveColorScheme.setPhaseBlue(phaseBlue);
			Application.getApplication().update(false);
		} catch (NumberFormatException exception) {
			return;
		}

	}

	public void setCenter(String centerString) {

		try {
			double center = Double.parseDouble(centerString);
			simpleWaveColorScheme.setCenter(center);
			Application.getApplication().update(false);
		} catch (NumberFormatException exception) {
			return;
		}

	}

	public void setDelta(String deltaString) {

		try {
			double delta = Double.parseDouble(deltaString);
			simpleWaveColorScheme.setDelta(delta);
			Application.getApplication().update(false);
		} catch (NumberFormatException exception) {
			return;
		}

	}

	public void setThreshold(String thresholdString) {

		try {
			double threshold = Double.parseDouble(thresholdString);
			if (threshold >= 0) {
				simpleWaveColorScheme.setThreshold(threshold);
				Application.getApplication().update(false);
			}
		} catch (NumberFormatException exception) {
			return;
		}

	}

	public void setMaximumValue(String maximumValueString) {

		if (maximumValueString.trim().equals("")) {
			simpleWaveColorScheme.setMaximumValue(Integer.MAX_VALUE);
			Application.getApplication().update(false);
		}

		try {
			double maximumValue = Double.parseDouble(maximumValueString);
			if (maximumValue >= 0) {
				simpleWaveColorScheme.setMaximumValue(maximumValue);
				Application.getApplication().update(false);
			}
		} catch (NumberFormatException exception) {
			return;
		}

	}

	public void generateRandomSettings() {

		Random random = new Random();

		// Generate random values
		double frequencyRed = random.nextDouble() * 0.1;
		double frequencyGreen = random.nextDouble() * 0.1;
		double frequencyBlue = random.nextDouble() * 0.1;
		double phaseRed = random.nextDouble() * 10;
		double phaseGreen = random.nextDouble() * 10;
		double phaseBlue = random.nextDouble() * 10;
		double center = random.nextDouble() * (300 - 200) + 200;
		double delta = random.nextDouble() * 100;

		// Set values in the color scheme
		simpleWaveColorScheme.setFrequencyRed(frequencyRed);
		simpleWaveColorScheme.setFrequencyGreen(frequencyGreen);
		simpleWaveColorScheme.setFrequencyBlue(frequencyBlue);
		simpleWaveColorScheme.setPhaseRed(phaseRed);
		simpleWaveColorScheme.setPhaseGreen(phaseGreen);
		simpleWaveColorScheme.setPhaseBlue(phaseBlue);
		simpleWaveColorScheme.setCenter(center);
		simpleWaveColorScheme.setDelta(delta);

		// Update input fields
		frequencyRedTextField.setTextNoTrigger(Double.toString(frequencyRed));
		frequencyGreenTextField.setTextNoTrigger(Double.toString(frequencyGreen));
		frequencyBlueTextField.setTextNoTrigger(Double.toString(frequencyBlue));
		phaseRedTextField.setTextNoTrigger(Double.toString(phaseRed));
		phaseGreenTextField.setTextNoTrigger(Double.toString(phaseGreen));
		phaseBlueTextField.setTextNoTrigger(Double.toString(phaseBlue));
		centerTextField.setTextNoTrigger(Double.toString(center));
		deltaTextField.setTextNoTrigger(Double.toString(delta));

		// Update fractal
		Application.getApplication().update(false);

	}

	@Override
	public SettingItemComponent[] getSettingComponents() {

		// Frequency red
		frequencyRedTextField = new TextFieldComponent(Double.toString(simpleWaveColorScheme.getFrequencyRed())) {
			private static final long serialVersionUID = -6033309334749328555L;

			@Override
			public void documentUpdate(DocumentEvent event) {
				setFrequencyRed(this.getText());
			}
		};
		SettingItemComponent frequencyRedSettingItem = new SettingItemComponent("Frequency red:",
				frequencyRedTextField);

		// Frequency green
		frequencyGreenTextField = new TextFieldComponent(Double.toString(simpleWaveColorScheme.getFrequencyGreen())) {
			private static final long serialVersionUID = -6033309334749328555L;

			@Override
			public void documentUpdate(DocumentEvent event) {
				setFrequencyGreen(this.getText());
			}
		};
		SettingItemComponent frequencyGreenSettingItem = new SettingItemComponent("Frequency green:",
				frequencyGreenTextField);

		// Frequency blue
		frequencyBlueTextField = new TextFieldComponent(Double.toString(simpleWaveColorScheme.getFrequencyBlue())) {
			private static final long serialVersionUID = -6033309334749328555L;

			@Override
			public void documentUpdate(DocumentEvent event) {
				setFrequencyBlue(this.getText());
			}
		};
		SettingItemComponent frequencyBlueSettingItem = new SettingItemComponent("Frequency blue:",
				frequencyBlueTextField);

		// Phase red
		phaseRedTextField = new TextFieldComponent(Double.toString(simpleWaveColorScheme.getPhaseRed())) {
			private static final long serialVersionUID = -6033309334749328555L;

			@Override
			public void documentUpdate(DocumentEvent event) {
				setPhaseRed(this.getText());
			}
		};
		SettingItemComponent phaseRedSettingItem = new SettingItemComponent("Phase red:", phaseRedTextField);

		// Phase Green
		phaseGreenTextField = new TextFieldComponent(Double.toString(simpleWaveColorScheme.getPhaseGreen())) {
			private static final long serialVersionUID = -6033309334749328555L;

			@Override
			public void documentUpdate(DocumentEvent event) {
				setPhaseGreen(this.getText());
			}
		};
		SettingItemComponent phaseGreenSettingItem = new SettingItemComponent("Phase green:", phaseGreenTextField);

		// Phase Blue
		phaseBlueTextField = new TextFieldComponent(Double.toString(simpleWaveColorScheme.getPhaseBlue())) {
			private static final long serialVersionUID = -6033309334749328555L;

			@Override
			public void documentUpdate(DocumentEvent event) {
				setPhaseBlue(this.getText());
			}
		};
		SettingItemComponent phaseBlueSettingItem = new SettingItemComponent("Phase blue:", phaseBlueTextField);

		// Center
		centerTextField = new TextFieldComponent(Double.toString(simpleWaveColorScheme.getCenter())) {
			private static final long serialVersionUID = -6033309334749328555L;

			@Override
			public void documentUpdate(DocumentEvent event) {
				setCenter(this.getText());
			}
		};
		SettingItemComponent centerSettingItem = new SettingItemComponent("Center:", centerTextField);

		// Delta
		deltaTextField = new TextFieldComponent(Double.toString(simpleWaveColorScheme.getDelta())) {
			private static final long serialVersionUID = -6033309334749328555L;

			@Override
			public void documentUpdate(DocumentEvent event) {
				setDelta(this.getText());
			}
		};
		SettingItemComponent deltaSettingItem = new SettingItemComponent("Delta:", deltaTextField);

		// Threshold
		TextFieldComponent thresholdTextField = new TextFieldComponent(
				Double.toString(simpleWaveColorScheme.getThreshold())) {
			private static final long serialVersionUID = -6033309334749328555L;

			@Override
			public void documentUpdate(DocumentEvent event) {
				setThreshold(this.getText());
			}
		};
		SettingItemComponent thresholdSettingItem = new SettingItemComponent("Threshold:", thresholdTextField);

		// Maximum value
		double maximumValue = simpleWaveColorScheme.getMaximumValue();
		String maximumValueString;
		if (maximumValue == Integer.MAX_VALUE) {
			maximumValueString = "";
		} else {
			maximumValueString = Double.toString(simpleWaveColorScheme.getMaximumValue());
		}

		TextFieldComponent maximumValueTextField = new TextFieldComponent(maximumValueString) {
			private static final long serialVersionUID = -6033309334749328555L;

			@Override
			public void documentUpdate(DocumentEvent event) {
				setMaximumValue(this.getText());
			}
		};
		SettingItemComponent maximumValueSettingItem = new SettingItemComponent("Maximum value:",
				maximumValueTextField);

		// Color picker button
		ColorPickerButton colorPickerButton = new ColorPickerButton(simpleWaveColorScheme.getMaximumColor()) {

			private static final long serialVersionUID = 8994349209644064825L;

			@Override
			public void actionPerformed(ActionEvent e) {

				update();
				simpleWaveColorScheme.setMaximumColor(color);
				Application.getApplication().update(false);

			}

		};
		SettingItemComponent colorPickerSettingItem = new SettingItemComponent("Color picker:", colorPickerButton);

		// Random button
		ButtonComponent randomButton = new ButtonComponent("Random") {

			private static final long serialVersionUID = 1886939492408972226L;

			@Override
			public void actionPerformed(ActionEvent e) {
				generateRandomSettings();
			}

		};
		SettingItemComponent randomButtonSettingItem = new SettingItemComponent("Randomize:", randomButton);

		return new SettingItemComponent[] { frequencyRedSettingItem, frequencyGreenSettingItem,
				frequencyBlueSettingItem, phaseRedSettingItem, phaseGreenSettingItem, phaseBlueSettingItem,
				centerSettingItem, deltaSettingItem, thresholdSettingItem, maximumValueSettingItem,
				colorPickerSettingItem, randomButtonSettingItem };

	}

	@SuppressWarnings("rawtypes")
	@Override
	public Property[] getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

}
