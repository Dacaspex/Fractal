package gui.settings.utilComponents;

import java.awt.Dimension;

import javax.swing.JComboBox;

public class ColorSchemeComboBox extends JComboBox<ColorSchemeComboBoxItem> {

	private static final long serialVersionUID = 1717249993673919452L;

	public ColorSchemeComboBox() {

		super();

	}

	@Override
	public Dimension getMaximumSize() {

		Dimension max = super.getMaximumSize();
		max.height = getPreferredSize().height;
		return max;

	}

	public void addItem(ColorSchemeComboBoxItem item) {

		super.addItem(item);

	}

}
