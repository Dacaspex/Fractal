package gui.settings.utilComponents;

import java.awt.Dimension;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

import fractals.colorSchemes.AbstractColorScheme;
import fractals.colorSchemes.ColorSchemeManager;
import main.Application;

public class ColorSchemeSelectorBox extends JComboBox<ColorSchemeSelectorBoxItem> implements ItemListener {

	private static final long serialVersionUID = 1717249993673919452L;

	private ColorSchemeManager colorSchemeManager;

	public ColorSchemeSelectorBox(ColorSchemeManager colorSchemeManager) {

		super();
		this.colorSchemeManager = colorSchemeManager;
		buildContents();
		addItemListener(this);

	}

	@Override
	public Dimension getMaximumSize() {

		Dimension max = super.getMaximumSize();
		max.height = getPreferredSize().height;
		return max;

	}

	public void buildContents() {

		for (AbstractColorScheme colorScheme : colorSchemeManager.getAvailableColorSchemes()) {

			ColorSchemeSelectorBoxItem item = new ColorSchemeSelectorBoxItem(colorScheme);
			addItem(item);

			if (colorSchemeManager.getActiveColorScheme().getIdentifier().equals(colorScheme.getIdentifier())) {

				setSelectedItem(item);

			}

		}

	}

	@Override
	public void itemStateChanged(ItemEvent event) {

		if (event.getStateChange() == ItemEvent.SELECTED) {

			ColorSchemeSelectorBoxItem item = (ColorSchemeSelectorBoxItem) getSelectedItem();
			colorSchemeManager.setActiveColorScheme(item.getValue());
			Application.getApplication().update(true);

		}

	}

}
