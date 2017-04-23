package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import fractals.FractalManager;
import gui.settings.FractalSettingsPanel;
import gui.settings.InformationPanel;

public class SettingsPanel extends JPanel {

	private static final long serialVersionUID = -8928464335165056558L;

	private int padding;

	private FractalManager fractalManager;
	private InformationPanel informationPanel;
	private FractalSettingsPanel fractalSettingsPanel;

	public SettingsPanel(FractalManager fractalManager) {

		padding = 10;
		this.fractalManager = fractalManager;
		informationPanel = new InformationPanel(fractalManager.getSelectedFractal());
		fractalSettingsPanel = new FractalSettingsPanel(fractalManager.getSelectedFractal());

		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(250, 0));

		buildGUI();

	}

	public InformationPanel getInformationPanel() {

		return informationPanel;

	}
	
	public FractalSettingsPanel getFractalSettingsPanel() {
		
		return fractalSettingsPanel;
		
	}

	private void buildGUI() {

		setBorder(new EmptyBorder(padding, padding, padding, padding));

		add(informationPanel, BorderLayout.NORTH);
		add(fractalSettingsPanel, BorderLayout.CENTER);

	}

}
