package fractals.colorSchemes.settings;

import java.awt.Color;

import fractals.colorSchemes.WaveColorScheme;
import fractals.settings.SettingsManager;
import fractals.settings.properties.Property;
import fractals.settings.properties.Property.PropertyType;
import fractals.settings.properties.valdiators.FloatValidator;

public class WaveColorSchemeSettingsManager implements SettingsManager {

	private WaveColorScheme colorScheme;

	private Property<Float> frequencyRed;
	private Property<Float> frequencyGreen;
	private Property<Float> frequencyBlue;

	private Property<Float> phaseRed;
	private Property<Float> phaseGreen;
	private Property<Float> phaseBlue;

	private Property<Float> centerRed;
	private Property<Float> centerGreen;
	private Property<Float> centerBlue;

	private Property<Float> deltaRed;
	private Property<Float> deltaGreen;
	private Property<Float> deltaBlue;

	private Property<Float> threshold;
	private Property<Color> maximumColor;

	public WaveColorSchemeSettingsManager(WaveColorScheme colorScheme) {

		this.colorScheme = colorScheme;
		
		FloatValidator validator = new FloatValidator();

		frequencyRed = new Property<Float>()
				.setName("Frequency red")
				.setValue(colorScheme.getFrequencyRed())
				.setType(PropertyType.FLOAT)
				.setValidator(validator);

		frequencyGreen = new Property<Float>()
				.setName("Frequency green")
				.setValue(colorScheme.getFrequencyGreen())
				.setType(PropertyType.FLOAT)
				.setValidator(validator);

		frequencyBlue = new Property<Float>()
				.setName("Frequencey blue")
				.setValue(colorScheme.getFrequencyBlue())
				.setType(PropertyType.FLOAT)
				.setValidator(validator);

		phaseRed = new Property<Float>()
				.setName("Phase red")
				.setValue(colorScheme.getPhaseRed())
				.setType(PropertyType.FLOAT)
				.setValidator(validator);

		phaseGreen = new Property<Float>()
				.setName("Phase green")
				.setValue(colorScheme.getPhaseGreen())
				.setType(PropertyType.FLOAT)
				.setValidator(validator);

		phaseBlue = new Property<Float>()
				.setName("Phase blue")
				.setValue(colorScheme.getPhaseBlue())
				.setType(PropertyType.FLOAT)
				.setValidator(validator);

		centerRed = new Property<Float>()
				.setName("Center red")
				.setValue(colorScheme.getCenterRed())
				.setType(PropertyType.FLOAT)
				.setValidator(validator);

		centerGreen = new Property<Float>()
				.setName("Center green")
				.setValue(colorScheme.getCenterGreen())
				.setType(PropertyType.FLOAT)
				.setValidator(validator);

		centerBlue = new Property<Float>()
				.setName("Center blue")
				.setValue(colorScheme.getCenterBlue())
				.setType(PropertyType.FLOAT)
				.setValidator(validator);

		deltaRed = new Property<Float>()
				.setName("Delta red")
				.setValue(colorScheme.getDeltaRed())
				.setType(PropertyType.FLOAT)
				.setValidator(validator);

		deltaGreen = new Property<Float>()
				.setName("Delta green")
				.setValue(colorScheme.getDeltaGreen())
				.setType(PropertyType.FLOAT)
				.setValidator(validator);

		deltaBlue = new Property<Float>()
				.setName("Delta blue")
				.setValue(colorScheme.getDeltaBlue())
				.setType(PropertyType.FLOAT)
				.setValidator(validator);

		threshold = new Property<Float>()
				.setName("Threshold")
				.setValue(colorScheme.getThreshold())
				.setType(PropertyType.FLOAT)
				.setValidator(validator);

		maximumColor = new Property<Color>()
				.setName("Maximum colour")
				.setValue(colorScheme.getMaximumColor())
				.setType(PropertyType.COLOR)
				.setValidator(validator);

	}

	@Override
	public Property<?>[] getProperties() {

		return new Property<?>[] {
				frequencyRed,
				frequencyBlue,
				frequencyGreen,
				phaseRed,
				phaseBlue,
				phaseGreen,
				centerRed,
				centerGreen,
				centerBlue,
				deltaRed,
				deltaGreen,
				deltaBlue,
				threshold,
				maximumColor
		};

	}

	@Override
	public void updateProperties() {

		colorScheme.setFrequencyRed(frequencyRed.getValue());
		colorScheme.setFrequencyBlue(frequencyBlue.getValue());
		colorScheme.setFrequencyGreen(frequencyGreen.getValue());

		colorScheme.setPhaseRed(phaseRed.getValue());
		colorScheme.setPhaseBlue(phaseBlue.getValue());
		colorScheme.setPhaseGreen(phaseGreen.getValue());

		colorScheme.setCenterRed(centerRed.getValue());
		colorScheme.setCenterGreen(centerGreen.getValue());
		colorScheme.setCenterBlue(centerBlue.getValue());

		colorScheme.setDeltaRed(deltaRed.getValue());
		colorScheme.setDeltaGreen(deltaGreen.getValue());
		colorScheme.setDeltaBlue(deltaBlue.getValue());

		colorScheme.setThreshold(threshold.getValue());
		colorScheme.setMaximumColor(maximumColor.getValue());

	}

}
