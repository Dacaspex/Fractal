package gui.settings.listeners.juliaSet;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import fractals.settings.JuliaSettingsManager;
import fractals.settings.SettingsManager;

public class EscapeValueTextFieldListener implements DocumentListener {

	private JTextField escapeValueTextField;
	private SettingsManager settingsManager;

	public EscapeValueTextFieldListener(JTextField escapeValueTextField, SettingsManager settingsManager) {

		this.escapeValueTextField = escapeValueTextField;
		this.settingsManager = settingsManager;

	}

	public void update() {

		((JuliaSettingsManager) settingsManager).setEscapeValue(escapeValueTextField.getText());

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
