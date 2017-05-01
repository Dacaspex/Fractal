package gui.settings.listeners.juliaSet;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import fractals.settings.JuliaSettingsManager;
import fractals.settings.SettingsManager;

public class ConstantTextFieldListener implements DocumentListener {

	private JTextField constantTextField;
	private SettingsManager settingsManager;

	public ConstantTextFieldListener(JTextField constantTextField, SettingsManager settingsManager) {

		this.constantTextField = constantTextField;
		this.settingsManager = settingsManager;

	}

	public void update() {

		((JuliaSettingsManager) settingsManager).setConstant(constantTextField.getText());

	}

	@Override
	public void changedUpdate(DocumentEvent e) {

		update();

	}

	@Override
	public void insertUpdate(DocumentEvent e) {

		update();

	}

	@Override
	public void removeUpdate(DocumentEvent e) {

		update();

	}

}
