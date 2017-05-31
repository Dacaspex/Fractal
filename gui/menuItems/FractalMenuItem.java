package gui.menuItems;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import fractals.FractalManager;
import gui.SettingsPanel;

public class FractalMenuItem extends JMenuItem implements ActionListener {

	private static final long serialVersionUID = 4134586652779444321L;

	private String identifier;
	private FractalManager fractalManager;
	private SettingsPanel settingsPanel;

	public FractalMenuItem(String identifier, String name, FractalManager fractalManager, SettingsPanel settingsPanel) {

		this.identifier = identifier;
		this.fractalManager = fractalManager;
		this.settingsPanel = settingsPanel;

		setText(name);
		addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent event) {

		fractalManager.setActiveFractal(identifier);
		settingsPanel.update();

	}

}
