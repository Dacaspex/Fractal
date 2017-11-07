package fractals.settings;

import java.util.ArrayList;

import fractals.MandelBrotFractal;
import fractals.colorSchemes.AbstractColorScheme;
import fractals.settings.properties.SelectionProperty;
import fractals.settings.properties.Property;
import fractals.settings.properties.Property.PropertyType;
import util.ComboBoxItem;

public class MandelBrotSettingsManager implements SettingsManager {

	private MandelBrotFractal mandelBrotFractal;

	private Property<Integer> maxIterations;
	private Property<Float> escapeValue;
	private SelectionProperty colorSchemes;

	public MandelBrotSettingsManager(MandelBrotFractal mandelBrotFractal) {

		this.mandelBrotFractal = mandelBrotFractal;

		maxIterations = new Property<Integer>()
				.setName("Max iterations")
				.setValue(mandelBrotFractal.getMaxIterations())
				.setType(PropertyType.INTEGER);

		escapeValue = new Property<Float>()
				.setName("Escape value")
				.setValue(mandelBrotFractal.getEscapeValue())
				.setType(PropertyType.FLOAT);

		ArrayList<ComboBoxItem<String>> list = new ArrayList<ComboBoxItem<String>>();

		list.add(new ComboBoxItem<String>(mandelBrotFractal.getColorSchemeManager().getActiveColorScheme().getName(),
				mandelBrotFractal.getColorSchemeManager().getActiveColorScheme().getIdentifier()));

		for (AbstractColorScheme scheme : mandelBrotFractal.getColorSchemeManager().getAvailableColorSchemes()) {
			if (scheme != mandelBrotFractal.getColorSchemeManager().getActiveColorScheme()) {
				list.add(new ComboBoxItem<String>(scheme.getName(), scheme.getIdentifier()));
			}
		}

		colorSchemes = new SelectionProperty(list)
				.setName("Color schemes");

	}

	@Override
	public Property<?>[] getProperties() {

		return new Property<?>[] { maxIterations, escapeValue, colorSchemes };

	}

	@Override
	public void updateProperties() {

		mandelBrotFractal.setMaxIterations(maxIterations.getValue());
		mandelBrotFractal.setEscapeValue(escapeValue.getValue());
		mandelBrotFractal.getColorSchemeManager().setActiveColorScheme(colorSchemes.getValue());

	}

}
