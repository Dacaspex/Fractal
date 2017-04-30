package gui.settings.listeners.mandelBrotSet;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import fractals.settings.MandelBrotSettingsManager;
import fractals.settings.SettingsManager;

public class MaxIterationsTextFieldListener implements DocumentListener {

	private JTextField maxIterationsTextField;
	private SettingsManager settingsManager;

	public MaxIterationsTextFieldListener(JTextField maxIterationsTextField, SettingsManager settingsManager) {

		this.maxIterationsTextField = maxIterationsTextField;
		this.settingsManager = settingsManager;

	}

	public void update() {

		((MandelBrotSettingsManager) settingsManager).setMaxIterations(maxIterationsTextField.getText());

	}

	@Override
	public void changedUpdate(DocumentEvent event) {

	}

	@Override
	public void insertUpdate(DocumentEvent event) {

		update();

	}

	@Override
	public void removeUpdate(DocumentEvent event) {

		update();

	}

}
