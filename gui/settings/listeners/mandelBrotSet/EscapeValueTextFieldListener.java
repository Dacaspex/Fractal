package gui.settings.listeners.mandelBrotSet;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import fractals.MandelBrotFractal;
import fractals.settings.JuliaSettingsManager;
import fractals.settings.MandelBrotSettingsManager;
import fractals.settings.SettingsManager;

public class EscapeValueTextFieldListener implements DocumentListener {

	private JTextField escapeValueTextField;
	private SettingsManager settingsManager;

	public EscapeValueTextFieldListener(JTextField escapeValueTextField, SettingsManager settingsManager) {

		this.escapeValueTextField = escapeValueTextField;
		this.settingsManager = settingsManager;

	}

	public void update() {

		((MandelBrotSettingsManager) settingsManager).setEscapeValue(escapeValueTextField.getText());

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
