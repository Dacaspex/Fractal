package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import fractals.FractalManager;
import gui.settings.utilComponents.SettingItemComponent;
import gui.settings.utilComponents.SettingItemComponent.SettingItemPanelType;

public class SettingsPanel extends JPanel {

	private static final long serialVersionUID = -8928464335165056558L;

	private JScrollPane scrollPane;
	private FractalManager fractalManager;
	private int padding;

	public SettingsPanel(FractalManager fractalManager) {

		this.padding = 10;
		this.fractalManager = fractalManager;

		buildGUI();

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

		// Add all fractal components to the wrapper; first label then component
		for (SettingItemComponent component : fractalManager.getSelectedFractal().getSettingsManager()
				.getSettingComponents()) {
			if (component.getType() == SettingItemPanelType.SETTING) {
				wrapper.add(component.getLabel());
				wrapper.add(component.getComponent());
			} else if (component.getType() == SettingItemPanelType.SECTION_TITLE) {
				wrapper.add(component.getLabel());
			}
		}

		for (SettingItemComponent component : fractalManager.getSelectedFractal().getColorSchemeManager()
				.getActiveColorScheme().getSettingsManager().getSettingComponents()) {
			if (component.getType() == SettingItemPanelType.SETTING) {
				wrapper.add(component.getLabel());
				wrapper.add(component.getComponent());
			} else if (component.getType() == SettingItemPanelType.SECTION_TITLE) {
				wrapper.add(component.getLabel());
			}
		}

		// Set scrollPane viewport and update GUI
		scrollPane.setViewportView(wrapper);
		revalidate();
		repaint();

	}

}
