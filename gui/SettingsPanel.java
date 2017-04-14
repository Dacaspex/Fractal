package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.HashMap;

import javax.swing.JPanel;

import fractals.FractalManager;
import gui.settings.InformationPanel;

public class SettingsPanel extends JPanel {

	private static final long serialVersionUID = -8928464335165056558L;

	private FractalManager fractalManager;
	
	public SettingsPanel(FractalManager fractalManager) {

		this.fractalManager = fractalManager;
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		setPreferredSize(new Dimension(200, 0));

		buildGUI();

	}

	private void buildGUI() {

		// Add information panel
		InformationPanel informationPanel = new InformationPanel(fractalManager.getSelectedFractal());
		
		// Add 'change settings' panel
		
		
		add(informationPanel);
	}

}
