package fractals.colorSchemes.settings;

import fractals.colorSchemes.LinearColorScheme;
import fractals.settings.SettingsManager;
import fractals.settings.properties.Property;
import fractals.settings.properties.Property.PropertyType;
import fractals.settings.properties.valdiators.FloatValidator;

public class LinearColorSchemeSettingsManager implements SettingsManager {

	private LinearColorScheme linearColorScheme;

	Property<Integer> steps;
	Property<Boolean> loop;

	public LinearColorSchemeSettingsManager(LinearColorScheme linearColorScheme) {

		this.linearColorScheme = linearColorScheme;

		steps = new Property<Integer>()
				.setName("Number of steps")
				.setValue(linearColorScheme.getSteps())
				.setType(PropertyType.INTEGER)
				.setValidator(new FloatValidator().setLowerBound(0));

		loop = new Property<Boolean>()
				.setName("Loop gradient")
				.setValue(linearColorScheme.isLoop())
				.setType(PropertyType.BOOLEAN);

	}

	@Override
	public Property<?>[] getProperties() {

		return new Property<?>[] {
				steps,
				loop
		};

	}

	@Override
	public void updateProperties() {

		linearColorScheme.setSteps(steps.getValue());
		linearColorScheme.setLoop(loop.getValue());

	}

}
