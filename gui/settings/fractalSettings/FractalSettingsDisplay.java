package gui.settings.fractalSettings;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import fractals.FractalManager;
import main.Application;

public class FractalSettingsDisplay extends JFrame {

	private static final long serialVersionUID = -5491899016615981173L;

	private final int PREFERRED_WIDTH = 500;
	private final int PREFERRED_HEIGHT = 600;

	private PropertyTable propertyTable;
	private FractalManager fractalManager;

	public FractalSettingsDisplay() {

		fractalManager = Application.getApplication().getDisplay().getExplorerPanel().getFractalManager();

		buildGUI();
		setLocationRelativeTo(Application.getApplication().getDisplay());

	}

	public void buildGUI() {

		setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		buildPropertyTable();

		JScrollPane scrollPane = new JScrollPane(propertyTable);
		getContentPane().add(scrollPane);

		pack();
		setVisible(true);

	}

	public void buildPropertyTable() {

		propertyTable = new PropertyTable(fractalManager.getActiveFractal().getSettingsManager().getProperties());

	}

}
