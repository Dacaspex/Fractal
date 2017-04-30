package gui.settings;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import fractals.AbstractFractal;
import fractals.settings.SettingsManager;

public abstract class FractalSettingsPanel extends JPanel {

	private static final long serialVersionUID = 7273863811094892240L;

	protected AbstractFractal fractal;
	protected SettingsManager settingsManager;

	public FractalSettingsPanel(AbstractFractal fractal, SettingsManager settingsManager) {

		this.fractal = fractal;
		this.settingsManager = settingsManager;

	}

	protected void buildGUI() {

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		TitledBorder titleBorder = BorderFactory.createTitledBorder("Settings");
		setBorder(titleBorder);

	}

}
