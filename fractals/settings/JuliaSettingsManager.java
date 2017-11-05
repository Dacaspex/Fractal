package fractals.settings;

import complex.Complex;
import fractals.JuliaFractal;
import fractals.settings.properties.ColorSchemeSelectorProperty;
import fractals.settings.properties.Property;
import fractals.settings.properties.Property.PropertyType;
import fractals.settings.properties.valdiators.IntegerValidator;
import gui.settings.utilComponents.SettingItemComponent;

public class JuliaSettingsManager implements SettingsManager {

	private JuliaFractal juliaFractal;
	
	private Property<Integer> maxIterations;
	private Property<Float> escapeValue;
	private Property<Complex> constant;
	private ColorSchemeSelectorProperty colorSchemes;

	public JuliaSettingsManager(JuliaFractal juliaFractal) {

		this.juliaFractal = juliaFractal;

	}
	
	@Override
	public SettingItemComponent[] getSettingComponents() {
		return null;
	}

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
				.setName("Colorscheme");

		return new Property[] { maxIterations, escapeValue, constant, colorSchemes };

	}

	@Override
	public void updateProperties() {

		juliaFractal.setMaxIterations(maxIterations.getValue());
		juliaFractal.setEscapeValue(escapeValue.getValue());
		juliaFractal.setConstant(constant.getValue());
		juliaFractal.getColorSchemeManager().setActiveColorScheme(colorSchemes.getValue());
		
	}

}
