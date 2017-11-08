package gui.settings.fractalSettings;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import fractals.FractalManager;
import fractals.settings.properties.Property;
import main.Application;

public class FractalSettingsDisplay extends JFrame {

	private static final long serialVersionUID = -5491899016615981173L;

	private final int PREFERRED_WIDTH = 500;
	private final int PREFERRED_HEIGHT = 600;

	private PropertyTable propertyTable;
	private FractalManager fractalManager;
	private JScrollPane scrollPane;

	public FractalSettingsDisplay() {

		fractalManager = Application.getApplication().getDisplay().getExplorerPanel().getFractalManager();

		buildGUI();

	}
	
	public void open() {
		setVisible(true);	
	}
	
	public void close() {
		setVisible(false);
	}
	
	public void update() {
		
		buildPropertyTable();
		
		getContentPane().removeAll();
		scrollPane = new JScrollPane(propertyTable);
		getContentPane().add(scrollPane);
		
		revalidate();
		repaint();
		
	}

	public void buildGUI() {

		setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		buildPropertyTable();

		scrollPane = new JScrollPane(propertyTable);
		getContentPane().add(scrollPane);

		pack();
		setLocationRelativeTo(Application.getApplication().getDisplay());

	}

	public void buildPropertyTable() {

		Property<?>[] fractalProperties = fractalManager.getActiveFractal().getSettingsManager().getProperties();
		Property<?>[] colorSchemeProperties = fractalManager.getActiveFractal().getColorSchemeManager()
				.getActiveColorScheme().getSettingsManager().getProperties();

		Property<?>[] properties = new Property<?>[fractalProperties.length + colorSchemeProperties.length];

		int i = 0;
		for (; i < fractalProperties.length; i++) {
			properties[i] = fractalProperties[i];
		}
		for (int j = 0; j < colorSchemeProperties.length; j++) {
			properties[i++] = colorSchemeProperties[j];
		}

		propertyTable = new PropertyTable(properties, fractalManager.getActiveFractal().getSettingsManager(),
				fractalManager.getActiveFractal().getColorSchemeManager().getActiveColorScheme().getSettingsManager());

	}

}
