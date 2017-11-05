package fractals.colorSchemes.settings;

import fractals.colorSchemes.WaveColorScheme;
import fractals.settings.SettingsManager;
import fractals.settings.properties.Property;
import fractals.settings.properties.Property.PropertyType;
import gui.settings.utilComponents.SettingItemComponent;

public class WaveColorSchemeSettingsManager implements SettingsManager {

	private WaveColorScheme simpleWaveColorScheme;

	private Property<Float> frequencyRed;
	private Property<Float> frequencyGreen;
	private Property<Float> frequencyBlue;
	private Property<Float> phaseRed;
	private Property<Float> phaseGreen;
	private Property<Float> phaseBlue;
	private Property<Float> center;
	private Property<Float> delta;

	public WaveColorSchemeSettingsManager(WaveColorScheme simpleWaveColorScheme) {

		this.simpleWaveColorScheme = simpleWaveColorScheme;

		frequencyRed = new Property<Float>().setName("Frequency red").setValue(0.016f).setType(PropertyType.FLOAT);

		frequencyGreen = new Property<Float>().setName("Frequency green").setValue(0.013f).setType(PropertyType.FLOAT);

		frequencyBlue = new Property<Float>().setName("Frequencey blue").setValue(0.01f).setType(PropertyType.FLOAT);

		phaseRed = new Property<Float>().setName("Phase red").setValue(4f).setType(PropertyType.FLOAT);

		phaseGreen = new Property<Float>().setName("Phase green").setValue(2f).setType(PropertyType.FLOAT);

		phaseBlue = new Property<Float>().setName("Phase blue").setValue(1f).setType(PropertyType.FLOAT);

		center = new Property<Float>().setName("Center").setValue(230f).setType(PropertyType.FLOAT);

		delta = new Property<Float>().setName("Delta").setValue(25f).setType(PropertyType.FLOAT);

	}

	@Override
	public SettingItemComponent[] getSettingComponents() {
		return null;
	}

	@Override
	public Property<?>[] getProperties() {

		return new Property<?>[] { frequencyRed, frequencyBlue, frequencyGreen, phaseRed, phaseBlue, phaseGreen, center,
				delta };

	}

	@Override
	public void updateProperties() {
		
		simpleWaveColorScheme.setFrequencyRed(frequencyRed.getValue());
		simpleWaveColorScheme.setFrequencyBlue(frequencyBlue.getValue());
		simpleWaveColorScheme.setFrequencyGreen(frequencyGreen.getValue());
		simpleWaveColorScheme.setPhaseRed(phaseRed.getValue());
		simpleWaveColorScheme.setPhaseBlue(phaseBlue.getValue());
		simpleWaveColorScheme.setPhaseGreen(phaseGreen.getValue());
		simpleWaveColorScheme.setCenter(center.getValue());
		simpleWaveColorScheme.setDelta(delta.getValue());

	}

}
