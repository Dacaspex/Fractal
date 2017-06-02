package gui.menuItems;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import fractals.AbstractFractal;
import fractals.FractalManager;
import gui.SettingsPanel;

public class FractalMenuItem extends JMenuItem implements ActionListener {

	private static final long serialVersionUID = 4134586652779444321L;

	private AbstractFractal fractal;
	private FractalManager fractalManager;
	private SettingsPanel settingsPanel;

	// TODO rewrite:
	// Explorer panel -> should house switching between fractals
	public FractalMenuItem(AbstractFractal fractal, FractalManager fractalManager, SettingsPanel settingsPanel) {

		this.fractal = fractal;
		this.fractalManager = fractalManager;
		this.settingsPanel = settingsPanel;

		setText(fractal.getName());
		addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent event) {

		fractalManager.setActiveFractal(fractal.getIdentifier());
		settingsPanel.update();

	}

}
