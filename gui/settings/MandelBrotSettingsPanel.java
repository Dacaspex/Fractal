package gui.settings;

import javax.swing.JLabel;
import javax.swing.JTextField;

import fractals.AbstractFractal;
import fractals.MandelBrotFractal;
import fractals.settings.SettingsManager;
import gui.settings.listeners.mandelBrotSet.EscapeValueTextFieldListener;
import gui.settings.listeners.mandelBrotSet.MaxIterationsTextFieldListener;
import gui.settings.utilComponents.ColorSchemeSelectorBox;
import gui.settings.utilComponents.SettingItemPanel;

public class MandelBrotSettingsPanel extends FractalSettingsPanel {

	private static final long serialVersionUID = 794795730103012414L;

	private ColorSchemeSelectorBox colorSchemeSelectorBox;

	public MandelBrotSettingsPanel(AbstractFractal fractal, SettingsManager settingsManager) {

		super(fractal, settingsManager);

		this.colorSchemeSelectorBox = new ColorSchemeSelectorBox(fractal.getColorSchemeManager());

		buildGUI();

	}

	@Override
	protected void buildGUI() {

		MandelBrotFractal fractal = (MandelBrotFractal) this.fractal;

		super.buildGUI();

		SettingItemPanel colorSchemeSelectorBoxPanel = new SettingItemPanel(colorSchemeSelectorBox);
		add(colorSchemeSelectorBoxPanel);

		// Maximum number of iterations
		JLabel maxIterationsLabel = new JLabel("Maximum number of iterations:");
		SettingItemPanel maxIterationsLabelPanel = new SettingItemPanel(maxIterationsLabel);
		add(maxIterationsLabelPanel);

		JTextField maxIterationsField = new JTextField();
		maxIterationsField.setText(Integer.toString(fractal.getMaxIterations()));
		maxIterationsField.getDocument()
				.addDocumentListener(new MaxIterationsTextFieldListener(maxIterationsField, settingsManager));
		SettingItemPanel maxIterationsPanel = new SettingItemPanel(maxIterationsField);
		add(maxIterationsPanel);

		// Escape value
		JLabel escapeValueLabel = new JLabel("Escape value:");
		SettingItemPanel escapeValueLabelPanel = new SettingItemPanel(escapeValueLabel);
		add(escapeValueLabelPanel);

		JTextField escapeValueField = new JTextField();
		escapeValueField.setText(Double.toString(fractal.getEscapeValue()));
		escapeValueField.getDocument()
				.addDocumentListener(new EscapeValueTextFieldListener(escapeValueField, settingsManager));
		SettingItemPanel escapeValuePanel = new SettingItemPanel(escapeValueField);
		add(escapeValuePanel);

	}

}
