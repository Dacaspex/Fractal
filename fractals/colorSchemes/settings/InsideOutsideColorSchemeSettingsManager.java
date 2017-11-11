package fractals.colorSchemes.settings;

import java.awt.Color;

import fractals.colorSchemes.InsideOutsideColorScheme;
import fractals.settings.SettingsManager;
import fractals.settings.properties.Property;
import fractals.settings.properties.Property.PropertyType;
import fractals.settings.properties.valdiators.FloatValidator;

public class InsideOutsideColorSchemeSettingsManager implements SettingsManager {

	private InsideOutsideColorScheme colorScheme;
	
	private Property<Float> threshold;
	private Property<Color> inColor;
	private Property<Color> outColor;
	
	public InsideOutsideColorSchemeSettingsManager(InsideOutsideColorScheme colorScheme) {
		
		this.colorScheme = colorScheme;
		
		threshold = new Property<Float>()
				.setName("Threshold")
				.setValue(colorScheme.getThreshold())
				.setType(PropertyType.FLOAT)
				.setValidator(new FloatValidator().setLowerBound(1));
		
		inColor = new Property<Color>()
				.setName("Inside set color")
				.setValue(colorScheme.getInColor())
				.setType(PropertyType.COLOR);
		
		outColor = new Property<Color>()
				.setName("Outside set color")
				.setValue(colorScheme.getOutColor())
				.setType(PropertyType.COLOR);
		
	}
	
	@Override
	public Property<?>[] getProperties() {
		
		return new Property<?>[] {
			threshold,
			inColor,
			outColor
		};
		
	}

	@Override
	public void updateProperties() {
		
		colorScheme.setThreshold(threshold.getValue());
		colorScheme.setInColor(inColor.getValue());
		colorScheme.setOutColor(outColor.getValue());
		
	}

}
