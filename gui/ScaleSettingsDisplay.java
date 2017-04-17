package gui;

import java.awt.Dimension;

import javax.swing.JFrame;

public class ScaleSettingsDisplay extends JFrame {

	private static final long serialVersionUID = 3812176595170262309L;

	private final int DEFAULT_WIDTH;
	private final int DEFAULT_HEIGHT;

	public ScaleSettingsDisplay() {

		DEFAULT_WIDTH = 300;
		DEFAULT_HEIGHT = 200;

		buildGUI();
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	private void buildGUI() {

		setTitle("Change scale settings");
		setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));

		// TODO create GUI

	}

}
