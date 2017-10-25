package gui.settings.fractalSettings;

import javax.swing.JTable;

import fractals.settings.properties.Property;

public class PropertyTable extends JTable {

	private static final long serialVersionUID = 6697511685217086092L;

	private Property<?>[] properties;

	public PropertyTable(Property<?>[] properties, String leftHeader, String rightHeader) {

		this.properties = properties;

		String[] headers = { leftHeader, rightHeader };
		
		setModel(new PropertyTableModel(headers, null));
		
		addProperties();
		
	}

	public PropertyTable(Property<?>[] properties) {
		this(properties, "Property", "Value");
	}

	private void addProperties() {

		for (Property<?> property : properties) {

			switch (property.getType()) {
			case BOOLEAN:
				break;
			case COLOR:
				break;
			case COMPLEX:
				break;
			case FLOAT:
				break;
			case INTEGER:
				break;
			default:
				break;

			}
		}
	}

}
