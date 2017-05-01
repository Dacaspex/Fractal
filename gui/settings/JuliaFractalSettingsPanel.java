package gui.settings;

import javax.swing.JLabel;
import javax.swing.JTextField;

import fractals.AbstractFractal;
import fractals.JuliaFractal;
import fractals.settings.SettingsManager;
import gui.settings.listeners.juliaSet.ConstantTextFieldListener;
import gui.settings.listeners.juliaSet.EscapeValueTextFieldListener;
import gui.settings.listeners.juliaSet.MaxIterationsTextFieldListener;
import gui.settings.utilComponents.ColorSchemeSelectorBox;
import gui.settings.utilComponents.SettingItemPanel;

public class JuliaFractalSettingsPanel extends FractalSettingsPanel {

	private static final long serialVersionUID = 905291527554013054L;

	private ColorSchemeSelectorBox colorSchemeSelectorBox;

	public JuliaFractalSettingsPanel(AbstractFractal fractal, SettingsManager settingsManager) {

		super(fractal, settingsManager);

		this.colorSchemeSelectorBox = new ColorSchemeSelectorBox(fractal.getColorSchemeManager());

		buildGUI();

	}

	@Override
	protected void buildGUI() {

		JuliaFractal fractal = (JuliaFractal) this.fractal;

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

		// Constant
		JLabel constantLabel = new JLabel("Constant: ");
		SettingItemPanel constantLabelPanel = new SettingItemPanel(constantLabel);
		add(constantLabelPanel);

		JTextField constantField = new JTextField();
		String constantString = Double.toString(fractal.getConstant().getReal());
		constantString += (fractal.getConstant().getImaginary() < 0) ? "-" : "+";
		constantString += Double.toString(fractal.getConstant().getImaginary());
		constantString += "i";
		constantField.setText(constantString);
		constantField.getDocument().addDocumentListener(new ConstantTextFieldListener(constantField, settingsManager));
		SettingItemPanel constantPanel = new SettingItemPanel(constantField);
		add(constantPanel);

	}

}
