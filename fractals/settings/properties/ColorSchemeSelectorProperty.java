package fractals.settings.properties;

import java.util.ArrayList;

import fractals.colorSchemes.AbstractColorScheme;
import fractals.colorSchemes.ColorSchemeManager;
import util.ComboBoxItem;

public class ColorSchemeSelectorProperty extends Property<String> {

	private ArrayList<ComboBoxItem<String>> list;
	
	public ColorSchemeSelectorProperty(ColorSchemeManager manager) {

		list = new ArrayList<ComboBoxItem<String>>();
		
		list.add(new ComboBoxItem<String>(manager.getActiveColorScheme().getName(),
				manager.getActiveColorScheme().getIdentifier()));

		for (AbstractColorScheme scheme : manager.getAvailableColorSchemes()) {
			if (scheme != manager.getActiveColorScheme()) {
				list.add(new ComboBoxItem<String>(scheme.getName(), scheme.getIdentifier()));
			}
		}

		setValue(manager.getActiveColorScheme().getIdentifier());

	}
	
	public ArrayList<ComboBoxItem<String>> getList() {
		return list;
	}

	public ColorSchemeSelectorProperty setName(String name) {
		super.setName(name);
		return this;
	}

	public ColorSchemeSelectorProperty setType(PropertyType type) {
		super.setType(type);
		return this;
	}
}
