package fractals.settings;

import javax.swing.event.DocumentEvent;

import fractals.MandelBrotFractal;
import fractals.settings.properties.Property;
import gui.settings.utilComponents.ColorSchemeSelectorBox;
import gui.settings.utilComponents.SettingItemComponent;
import gui.settings.utilComponents.TextFieldComponent;
import main.Application;

public class MandelBrotSettingsManager implements SettingsManager {

	private MandelBrotFractal mandelBrotFractal;

	public MandelBrotSettingsManager(MandelBrotFractal mandelBrotFractal) {

		this.mandelBrotFractal = mandelBrotFractal;

	}

	public void setMaxIterations(String iterationsString) {

		try {

			int iterations = Integer.parseInt(iterationsString);

			if (iterations <= 0) {

				return;

			}

			mandelBrotFractal.setMaxIterations(iterations);
			Application.getApplication().update(false);

		} catch (NumberFormatException exception) {

			return;

		}

	}

	public void setEscapeValue(String escapeValueString) {

		try {

			double escapeValue = Double.parseDouble(escapeValueString);

			if (escapeValue <= 0) {

				return;

			}

			mandelBrotFractal.setEscapeValue(escapeValue);
			Application.getApplication().update(false);

		} catch (NumberFormatException exception) {

			return;

		}

	}

	@Override
	public SettingItemComponent[] getSettingComponents() {

		// Color scheme
		ColorSchemeSelectorBox colorSchemeBox = new ColorSchemeSelectorBox(mandelBrotFractal.getColorSchemeManager());
		SettingItemComponent colorSchemeBoxSettingItem = new SettingItemComponent("Color Scheme:", colorSchemeBox);

		// Max iterations
		TextFieldComponent maxIterationsTextField = new TextFieldComponent(
				Integer.toString(mandelBrotFractal.getMaxIterations())) {
			private static final long serialVersionUID = -6033309334749328555L;

			@Override
			public void documentUpdate(DocumentEvent event) {
				setMaxIterations(this.getText());
			}
		};
		SettingItemComponent maxIterationsSettingItem = new SettingItemComponent("Max iterations:",
				maxIterationsTextField);

		// Escape value
		TextFieldComponent escapeValueTextField = new TextFieldComponent(
				Double.toString(mandelBrotFractal.getEscapeValue())) {
			private static final long serialVersionUID = -6033309334749328555L;

			@Override
			public void documentUpdate(DocumentEvent event) {
				setEscapeValue(this.getText());
			}
		};
		SettingItemComponent escapeValueSettingsItem = new SettingItemComponent("Escape value:", escapeValueTextField);

		return new SettingItemComponent[] { colorSchemeBoxSettingItem, maxIterationsSettingItem,
				escapeValueSettingsItem };
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Property[] getProperties() {
		return null;
	}

	@Override
	public void updateProperties() {
		// TODO Auto-generated method stub
		
	}

}
