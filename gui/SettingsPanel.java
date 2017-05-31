package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import fractals.FractalManager;
import gui.settings.utilComponents.SettingItemComponent;

public class SettingsPanel extends JPanel {

	private static final long serialVersionUID = -8928464335165056558L;

	private static SettingsPanel instance;

	private JScrollPane scrollPane;
	private FractalManager fractalManager;
	private int padding;

	public SettingsPanel(FractalManager fractalManager) {

		SettingsPanel.instance = this;
		this.padding = 10;
		this.fractalManager = fractalManager;

		buildGUI();

	}

	public static SettingsPanel getSettingsPanel() {

		return instance;

	}

	private void buildGUI() {

		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(250, 0));
		setBorder(new EmptyBorder(padding, padding, padding, padding));
		scrollPane = new JScrollPane();
		scrollPane.createHorizontalScrollBar();
		scrollPane.setBorder(null);
		update();
		add(scrollPane, BorderLayout.CENTER);

	}

	public void update() {

		// Create a wrapper panel to wrap all components with
		JPanel wrapper = new JPanel();
		wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));

		// Section title
		SettingItemComponent titleComponentFractalSettings = new SettingItemComponent("Fractal Settings");
		wrapper.add(titleComponentFractalSettings.getLabel());

		// Add all fractal components to the wrapper; first label then component
		for (SettingItemComponent component : fractalManager.getActiveFractal().getSettingsManager()
				.getSettingComponents()) {
			wrapper.add(component.getLabel());
			wrapper.add(component.getComponent());
		}

		// Section title
		SettingItemComponent titleComponentColorSchemeSettings = new SettingItemComponent("Color scheme Settings");
		wrapper.add(titleComponentColorSchemeSettings.getLabel());

		// Add all settings components in the same way
		for (SettingItemComponent component : fractalManager.getActiveFractal().getColorSchemeManager()
				.getActiveColorScheme().getSettingsManager().getSettingComponents()) {
			wrapper.add(component.getLabel());
			wrapper.add(component.getComponent());
		}

		// Set scrollPane viewport and update GUI
		scrollPane.setViewportView(wrapper);

		revalidate();

		repaint();

	}

}
