package gui.settings.fractalSettings;

import java.util.HashMap;

import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import fractals.settings.properties.Property;
import gui.settings.fractalSettings.editors.IntegerEditor;

public class PropertyTable extends JTable {

	private static final long serialVersionUID = 6697511685217086092L;

	private Property<?>[] properties;
	
	private int rowCounter;
	private PropertyTableModel model;
	private HashMap<Integer, TableCellRenderer> renderers;
	private HashMap<Integer, TableCellEditor> editors;

	public PropertyTable(Property<?>[] properties, String leftHeader, String rightHeader) {

		this.properties = properties;

		String[] headers = { leftHeader, rightHeader };
		
		rowCounter = 0;
		model = new PropertyTableModel(headers, null);
		renderers = new HashMap<Integer, TableCellRenderer>();
		editors = new HashMap<Integer, TableCellEditor>();
		
		setModel(model);
		
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
				model.addRow(new Object[] { property.getName(), property.getValue() });
				break;
			case INTEGER:
				model.addRow(new Object[] { property.getName(), property.getValue() });
				editors.put(rowCounter, new IntegerEditor((Property<Integer>) property));
				break;
			default:
				break;

			}
			
			rowCounter++;
		}
	}

}
