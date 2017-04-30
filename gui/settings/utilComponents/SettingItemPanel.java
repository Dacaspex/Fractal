package gui.settings.utilComponents;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class SettingItemPanel extends JPanel {

	private static final long serialVersionUID = 1761594042300552175L;

	private JComponent component;
	private int extraHeight;

	public SettingItemPanel(JComponent component) {

		this.component = component;
		this.extraHeight = 10;

		buildGUI();

	}

	public void buildGUI() {

		setLayout(new BorderLayout());
		add(component);

	}

	@Override
	public Dimension getMaximumSize() {

		Dimension max = super.getMaximumSize();
		max.height = getPreferredSize().height + extraHeight;
		return max;

	}

}
