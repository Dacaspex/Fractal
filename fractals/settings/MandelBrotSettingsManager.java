package fractals.settings;

import fractals.MandelBrotFractal;
import fractals.settings.properties.ColorSchemeSelectorProperty;
import fractals.settings.properties.Property;
import fractals.settings.properties.Property.PropertyType;
import gui.settings.utilComponents.SettingItemComponent;

public class MandelBrotSettingsManager implements SettingsManager {

	private MandelBrotFractal mandelBrotFractal;

	private Property<Integer> maxIterations;
	private Property<Float> escapeValue;
	private ColorSchemeSelectorProperty colorSchemes;

	public MandelBrotSettingsManager(MandelBrotFractal mandelBrotFractal) {

		this.mandelBrotFractal = mandelBrotFractal;

	}

	@Override
	public Property<?>[] getProperties() {

		maxIterations = new Property<Integer>()
				.setName("Max iterations")
				.setValue(mandelBrotFractal.getMaxIterations())
				.setType(PropertyType.INTEGER);

		escapeValue = new Property<Float>()
				.setName("Escape value")
				.setValue(mandelBrotFractal.getEscapeValue())
				.setType(PropertyType.FLOAT);

		colorSchemes = new ColorSchemeSelectorProperty(mandelBrotFractal.getColorSchemeManager())
				.setName("Color scheme");

		return new Property<?>[] { maxIterations, escapeValue, colorSchemes };

	}

	@Override
	public void updateProperties() {

		mandelBrotFractal.setMaxIterations(maxIterations.getValue());
		mandelBrotFractal.setEscapeValue(escapeValue.getValue());
		mandelBrotFractal.getColorSchemeManager().setActiveColorScheme(colorSchemes.getValue());
		
	}

	@Override
	public SettingItemComponent[] getSettingComponents() {
		return null;
	}

}
