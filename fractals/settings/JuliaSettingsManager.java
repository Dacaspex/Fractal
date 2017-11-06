package fractals.settings;

import java.util.ArrayList;

import complex.Complex;
import fractals.JuliaFractal;
import fractals.colorSchemes.AbstractColorScheme;
import fractals.settings.properties.Property;
import fractals.settings.properties.Property.PropertyType;
import fractals.settings.properties.SelectionProperty;
import fractals.settings.properties.valdiators.IntegerValidator;
import util.ComboBoxItem;

public class JuliaSettingsManager implements SettingsManager {

	private JuliaFractal juliaFractal;

	private Property<Integer> maxIterations;
	private Property<Float> escapeValue;
	private Property<Complex> constant;
	private SelectionProperty colorSchemes;

	public JuliaSettingsManager(JuliaFractal juliaFractal) {

		this.juliaFractal = juliaFractal;

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

		ArrayList<ComboBoxItem<String>> list = new ArrayList<ComboBoxItem<String>>();

		list.add(new ComboBoxItem<String>(juliaFractal.getColorSchemeManager().getActiveColorScheme().getName(),
				juliaFractal.getColorSchemeManager().getActiveColorScheme().getIdentifier()));

		for (AbstractColorScheme scheme : juliaFractal.getColorSchemeManager().getAvailableColorSchemes()) {
			if (scheme != juliaFractal.getColorSchemeManager().getActiveColorScheme()) {
				list.add(new ComboBoxItem<String>(scheme.getName(), scheme.getIdentifier()));
			}
		}

		colorSchemes = new SelectionProperty(list)
				.setName("Color schemes");
		
	}

	@Override
	public Property<?>[] getProperties() {

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
