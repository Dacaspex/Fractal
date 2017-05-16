package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import fractals.FractalManager;
import test.SettingItemComponent;

public class SettingsPanel extends JPanel {

	private static final long serialVersionUID = -8928464335165056558L;

	private JScrollPane scrollPane;

	private int padding;

	private FractalManager fractalManager;

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

		JPanel wrapper = new JPanel();
		wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.Y_AXIS));

		for (SettingItemComponent component : fractalManager.getSelectedFractal().getSettingsManager()
				.getSettingComponents()) {
			wrapper.add(component.getLabel());
			wrapper.add(component.getComponent());
		}

		scrollPane.setViewportView(wrapper);

		revalidate();
		repaint();

	}

}
