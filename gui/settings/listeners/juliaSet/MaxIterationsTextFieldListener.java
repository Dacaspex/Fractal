package gui.settings.listeners.juliaSet;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import fractals.settings.JuliaSettingsManager;
import fractals.settings.SettingsManager;

public class MaxIterationsTextFieldListener implements DocumentListener {

	private JTextField maxIterationsTextField;
	private SettingsManager juliaSettingsManager;
	
	public MaxIterationsTextFieldListener(JTextField maxIterationsTextField, SettingsManager juliaSettingsManager) {

		this.maxIterationsTextField = maxIterationsTextField;
		this.juliaSettingsManager = juliaSettingsManager;
		
	}
	
	public void update() {
		
		((JuliaSettingsManager) juliaSettingsManager).setMaxIterations(maxIterationsTextField.getText());
		
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
