package fractals.settings.properties;

import java.util.ArrayList;

import util.ComboBoxItem;

public class SelectionProperty extends Property<String> {

	private ArrayList<ComboBoxItem<String>> list;

	public SelectionProperty(ArrayList<ComboBoxItem<String>> list) {

		this.type = PropertyType.SELECTION;
		this.list = list;

		setValue(list.get(0).getValue());

	}

	public ArrayList<ComboBoxItem<String>> getList() {
		return list;
	}

	public SelectionProperty setName(String name) {
		super.setName(name);
		return this;
	}

	public SelectionProperty setType(PropertyType type) {
		super.setType(type);
		return this;
	}
}
