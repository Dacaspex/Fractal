package gui.settings.fractalSettings;

import java.util.HashMap;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import fractals.settings.properties.Property;
import gui.settings.fractalSettings.editors.EditorController;
import gui.settings.fractalSettings.editors.IntegerEditor;

public class PropertyTable extends JTable {

	private static final long serialVersionUID = 6697511685217086092L;

	private Property<?>[] properties;

	private int rowCounter;
	private PropertyTableModel model;
	private HashMap<Integer, TableCellRenderer> renderers;
	private EditorController editorController;

	public PropertyTable(Property<?>[] properties, String leftHeader, String rightHeader) {

		this.properties = properties;

		String[] headers = { leftHeader, rightHeader };

		rowCounter = 0;
		model = new PropertyTableModel(headers, null);
		renderers = new HashMap<Integer, TableCellRenderer>();
		editorController = new EditorController();

		setModel(model);
		getColumnModel().getColumn(1).setCellEditor(editorController);

		addProperties();

	}

	public PropertyTable(Property<?>[] properties) {
		this(properties, "Property", "Value");
	}

	@SuppressWarnings("unchecked")
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
				model.addRow(new Object[] { property.getName(), property.getValue().toString() });
				break;
			case INTEGER:
				model.addRow(new Object[] { property.getName(), property.getValue().toString() });
				editorController.addEditor(rowCounter, new IntegerEditor((Property<Integer>) property));
				break;
			default:
				break;

			}

			rowCounter++;
		}
	}

	@Override
	public TableCellRenderer getCellRenderer(int row, int column) {

		if (column == 1) {
			return renderers.getOrDefault(row, super.getCellRenderer(row, column));
		}

		return super.getCellRenderer(row, column);

	}

}
