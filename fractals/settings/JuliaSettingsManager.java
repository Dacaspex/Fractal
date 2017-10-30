package fractals.settings;

import javax.swing.event.DocumentEvent;

import complex.Complex;
import fractals.JuliaFractal;
import fractals.settings.properties.Property;
import fractals.settings.properties.Property.PropertyType;
import gui.settings.utilComponents.ColorSchemeSelectorBox;
import gui.settings.utilComponents.SettingItemComponent;
import gui.settings.utilComponents.TextFieldComponent;
import main.Application;
import util.ComplexValueParser;

public class JuliaSettingsManager implements SettingsManager {

	private JuliaFractal juliaFractal;

	public JuliaSettingsManager(JuliaFractal juliaFractal) {

		this.juliaFractal = juliaFractal;

	}

	public void setMaxIterations(String iterationsString) {

		try {
			int iterations = Integer.parseInt(iterationsString);
			if (iterations <= 0) {
				return;
			}
			juliaFractal.setMaxIterations(iterations);
			Application.getApplication()
					.update(false);
		} catch (NumberFormatException exception) {
			return;
		}

	}

	public void setEscapeValue(String escapeValueString) {

		try {
			float escapeValue = Float.parseFloat(escapeValueString);
			if (escapeValue <= 0) {
				return;
			}
			juliaFractal.setEscapeValue(escapeValue);
			Application.getApplication()
					.update(false);
		} catch (NumberFormatException exception) {
			return;
		}

	}

	public void setConstant(String value) {

		Complex constant = ComplexValueParser.parseFromString(value);
		if (constant != null) {
			juliaFractal.setConstant(constant);
			Application.getApplication()
					.update(false);
		}

	}

	@Override
	public SettingItemComponent[] getSettingComponents() {

		// Color scheme
		ColorSchemeSelectorBox colorSchemeBox = new ColorSchemeSelectorBox(juliaFractal.getColorSchemeManager());
		SettingItemComponent colorSchemeBoxSettingItem = new SettingItemComponent("Color Scheme:", colorSchemeBox);

		// Max iterations
		TextFieldComponent maxIterationsTextField = new TextFieldComponent(
				Integer.toString(juliaFractal.getMaxIterations())) {
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
				Double.toString(juliaFractal.getEscapeValue())) {
			private static final long serialVersionUID = -6033309334749328555L;

			@Override
			public void documentUpdate(DocumentEvent event) {
				setEscapeValue(this.getText());
			}
		};
		SettingItemComponent escapeValueSettingsItem = new SettingItemComponent("Escape value:", escapeValueTextField);

		// Constant
		String constantString = Double.toString(juliaFractal.getConstant()
				.getReal());
		constantString += (juliaFractal.getConstant()
				.getImaginary() < 0) ? "-" : "+";
		constantString += Double.toString(juliaFractal.getConstant()
				.getImaginary());
		constantString += "i";
		TextFieldComponent constantTextField = new TextFieldComponent(constantString) {
			private static final long serialVersionUID = -6033309334749328555L;

			@Override
			public void documentUpdate(DocumentEvent event) {
				setConstant(this.getText());
			}
		};
		SettingItemComponent constantSettingItem = new SettingItemComponent("Constant:", constantTextField);

		return new SettingItemComponent[] { colorSchemeBoxSettingItem, maxIterationsSettingItem,
				escapeValueSettingsItem, constantSettingItem };
	}
	
	Property<Integer> maxIterations;
	Property<Float> escapeValue;

	@Override
	public Property<?>[] getProperties() {

		maxIterations = new Property<Integer>()
				.setName("Max iterations")
				.setType(PropertyType.INTEGER)
				.setValue(juliaFractal.getMaxIterations());

		escapeValue = new Property<Float>()
				.setName("Escape value")
				.setType(PropertyType.FLOAT)
				.setValue(juliaFractal.getEscapeValue());

		return new Property[] { maxIterations, escapeValue };

	}
	
	@Override
	public void updateProperties() {
		
		juliaFractal.setMaxIterations(maxIterations.getValue());
		juliaFractal.setEscapeValue(escapeValue.getValue());
		
	}

}
