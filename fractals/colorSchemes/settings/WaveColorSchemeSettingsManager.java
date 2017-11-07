package fractals.colorSchemes.settings;

import java.awt.Color;

import fractals.colorSchemes.WaveColorScheme;
import fractals.settings.SettingsManager;
import fractals.settings.properties.Property;
import fractals.settings.properties.Property.PropertyType;

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

		frequencyRed = new Property<Float>()
				.setName("Frequency red")
				.setValue(colorScheme.getFrequencyRed())
				.setType(PropertyType.FLOAT);

		frequencyGreen = new Property<Float>()
				.setName("Frequency green")
				.setValue(colorScheme.getFrequencyGreen())
				.setType(PropertyType.FLOAT);

		frequencyBlue = new Property<Float>()
				.setName("Frequencey blue")
				.setValue(colorScheme.getFrequencyBlue())
				.setType(PropertyType.FLOAT);

		phaseRed = new Property<Float>()
				.setName("Phase red")
				.setValue(colorScheme.getPhaseRed())
				.setType(PropertyType.FLOAT);

		phaseGreen = new Property<Float>()
				.setName("Phase green")
				.setValue(colorScheme.getPhaseGreen())
				.setType(PropertyType.FLOAT);

		phaseBlue = new Property<Float>()
				.setName("Phase blue")
				.setValue(colorScheme.getPhaseBlue())
				.setType(PropertyType.FLOAT);

		centerRed = new Property<Float>()
				.setName("Center red")
				.setValue(colorScheme.getCenterRed())
				.setType(PropertyType.FLOAT);

		centerGreen = new Property<Float>()
				.setName("Center green")
				.setValue(colorScheme.getCenterGreen())
				.setType(PropertyType.FLOAT);

		centerBlue = new Property<Float>()
				.setName("Center blue")
				.setValue(colorScheme.getCenterBlue())
				.setType(PropertyType.FLOAT);

		deltaRed = new Property<Float>()
				.setName("Delta red")
				.setValue(colorScheme.getDeltaRed())
				.setType(PropertyType.FLOAT);

		deltaGreen = new Property<Float>()
				.setName("Delta green")
				.setValue(colorScheme.getDeltaGreen())
				.setType(PropertyType.FLOAT);

		deltaBlue = new Property<Float>()
				.setName("Delta blue")
				.setValue(colorScheme.getDeltaBlue())
				.setType(PropertyType.FLOAT);

		threshold = new Property<Float>()
				.setName("Threshold")
				.setValue(colorScheme.getThreshold())
				.setType(PropertyType.FLOAT);

		maximumColor = new Property<Color>()
				.setName("Maximum colour")
				.setValue(colorScheme.getMaximumColor())
				.setType(PropertyType.COLOR);

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
