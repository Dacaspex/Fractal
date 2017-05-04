package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import fractals.FractalManager;
import gui.settings.FractalSettingsPanel;

public class SettingsPanel extends JPanel {

	private static final long serialVersionUID = -8928464335165056558L;

	private JScrollPane scrollPane;

	private int padding;

	private FractalManager fractalManager;
	private FractalSettingsPanel fractalSettingsPanel;

	public SettingsPanel(FractalManager fractalManager) {

		this.padding = 10;
		this.fractalManager = fractalManager;
		this.fractalSettingsPanel = fractalManager.getSelectedFractal().getSettingsManager().getSettingsPanel();

		buildGUI();

	}

	private void buildGUI() {

		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(250, 0));
		setBorder(new EmptyBorder(padding, padding, padding, padding));
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(fractalSettingsPanel);
		scrollPane.createHorizontalScrollBar();
		scrollPane.setBorder(null);
		add(scrollPane, BorderLayout.CENTER);

	}

	public void update() {

		remove(fractalSettingsPanel);
		fractalSettingsPanel = fractalManager.getSelectedFractal().getSettingsManager().getSettingsPanel();
		add(fractalSettingsPanel, BorderLayout.CENTER);
		revalidate();
		repaint();

	}

}
