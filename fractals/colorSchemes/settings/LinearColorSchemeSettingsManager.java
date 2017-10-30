package fractals.colorSchemes.settings;

import javax.swing.event.DocumentEvent;

import fractals.colorSchemes.LinearColorScheme;
import fractals.settings.SettingsManager;
import fractals.settings.properties.Property;
import gui.settings.utilComponents.SettingItemComponent;
import gui.settings.utilComponents.TextFieldComponent;
import main.Application;

public class LinearColorSchemeSettingsManager implements SettingsManager {

	private LinearColorScheme linearColorScheme;

	private TextFieldComponent stepsTextField;

	public LinearColorSchemeSettingsManager(LinearColorScheme linearColorScheme) {

		this.linearColorScheme = linearColorScheme;

	}

	public void setSteps(String stepsString) {

		try {
			int steps = Integer.parseInt(stepsString);
			if (steps > 0) {
				linearColorScheme.setSteps(steps);
				linearColorScheme.generateGradientMap();
				Application.getApplication().update(false);
			}
		} catch (NumberFormatException exception) {
			return;
		}

	}

	@Override
	public SettingItemComponent[] getSettingComponents() {

		stepsTextField = new TextFieldComponent(Integer.toString(linearColorScheme.getSteps())) {

			private static final long serialVersionUID = 1L;

			@Override
			public void documentUpdate(DocumentEvent event) {
				setSteps(this.getText());
			}

		};
		SettingItemComponent stepsSettingComponent = new SettingItemComponent("Steps:", stepsTextField);

		// Add/delete colors from the color array
		// TODO create color picker panel

		return new SettingItemComponent[] { stepsSettingComponent };

	}

	@SuppressWarnings("rawtypes")
	@Override
	public Property[] getProperties() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateProperties() {
		// TODO Auto-generated method stub
		
	}

}
