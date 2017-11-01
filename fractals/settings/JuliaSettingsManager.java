package fractals.settings;

import complex.Complex;
import fractals.JuliaFractal;
import fractals.settings.properties.ColorSchemeSelectorProperty;
import fractals.settings.properties.Property;
import fractals.settings.properties.Property.PropertyType;
import fractals.settings.properties.valdiators.IntegerValidator;
import gui.settings.utilComponents.ColorSchemeSelectorBox;
import gui.settings.utilComponents.SettingItemComponent;

public class JuliaSettingsManager implements SettingsManager {

	private JuliaFractal juliaFractal;

	public JuliaSettingsManager(JuliaFractal juliaFractal) {

		this.juliaFractal = juliaFractal;

	}

	@Override
	public SettingItemComponent[] getSettingComponents() {

		// Color scheme
		ColorSchemeSelectorBox colorSchemeBox = new ColorSchemeSelectorBox(juliaFractal.getColorSchemeManager());
		SettingItemComponent colorSchemeBoxSettingItem = new SettingItemComponent("Color Scheme:", colorSchemeBox);

		return new SettingItemComponent[] { colorSchemeBoxSettingItem };
	}

	Property<Integer> maxIterations;
	Property<Float> escapeValue;
	Property<Complex> constant;
	ColorSchemeSelectorProperty colorSchemes;

	@Override
	public Property<?>[] getProperties() {

		maxIterations = new Property<Integer>()
				.setName("Max iterations")
				.setType(PropertyType.INTEGER)
				.setValue(juliaFractal.getMaxIterations())
				.setValidator(new IntegerValidator().setLowerBound(0));

		escapeValue = new Property<Float>()
				.setName("Escape value")
				.setType(PropertyType.FLOAT)
				.setValue(juliaFractal.getEscapeValue());

		constant = new Property<Complex>()
				.setName("Constant")
				.setType(PropertyType.COMPLEX)
				.setValue(juliaFractal.getConstant());
		
		colorSchemes = new ColorSchemeSelectorProperty(juliaFractal.getColorSchemeManager())
				.setName("Colorscheme")
				.setType(PropertyType.SELECTION);

		return new Property[] { maxIterations, escapeValue, constant, colorSchemes };

	}

	@Override
	public void updateProperties() {

		juliaFractal.setMaxIterations(maxIterations.getValue());
		juliaFractal.setEscapeValue(escapeValue.getValue());
		juliaFractal.setConstant(constant.getValue());

	}

}
