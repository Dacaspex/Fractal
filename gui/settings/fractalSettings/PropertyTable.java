package gui.settings.fractalSettings;

import java.util.HashMap;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import complex.Complex;
import fractals.settings.SettingsManager;
import fractals.settings.properties.ColorSchemeSelectorProperty;
import fractals.settings.properties.Property;
import gui.settings.fractalSettings.editors.ComplexEditor;
import gui.settings.fractalSettings.editors.EditorController;
import gui.settings.fractalSettings.editors.FloatEditor;
import gui.settings.fractalSettings.editors.IntegerEditor;
import gui.settings.fractalSettings.editors.SelectionEditor;
import main.Application;

public class PropertyTable extends JTable {

	private static final long serialVersionUID = 6697511685217086092L;

	private SettingsManager fractalSettingsManager;
	private SettingsManager colorSchemeSettingsManager;
	private Property<?>[] properties;

	private int rowCounter;
	private PropertyTableModel model;
	private HashMap<Integer, TableCellRenderer> renderers;
	private EditorController editorController;

	public PropertyTable(Property<?>[] properties, SettingsManager fractalSettingsManager,
			SettingsManager colorSchemeSettingsManager) {

		this.fractalSettingsManager = fractalSettingsManager;
		this.colorSchemeSettingsManager = colorSchemeSettingsManager;
		this.properties = properties;

		String[] headers = { "Property", "Value" };

		rowCounter = 0;
		model = new PropertyTableModel(headers, null);
		renderers = new HashMap<Integer, TableCellRenderer>();
		editorController = new EditorController();

		setModel(model);
		getColumnModel().getColumn(1).setCellEditor(editorController);

		addProperties();

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
				model.addRow(new Object[] { property.getName(), ((Complex) property.getValue()).toString() }); 
				editorController.addEditor(rowCounter, new ComplexEditor((Property<Complex>) property, this));
				break;
			case FLOAT:
				model.addRow(new Object[] { property.getName(), property.getValue().toString() });
				editorController.addEditor(rowCounter, new FloatEditor((Property<Float>) property, this));
				break;
			case INTEGER:
				model.addRow(new Object[] { property.getName(), property.getValue().toString() });
				editorController.addEditor(rowCounter, new IntegerEditor((Property<Integer>) property, this));
				break;
			case SELECTION:
				String value = ((ColorSchemeSelectorProperty) property).getValue();
				model.addRow(new Object[] { property.getName(), value });
				editorController.addEditor(rowCounter, new SelectionEditor((ColorSchemeSelectorProperty) property, this));
				break;
			default:
				break;

			}

			rowCounter++;
		}

		model.addTableModelListener(new PropertyTableModelListener(this));
	}

	@Override
	public TableCellRenderer getCellRenderer(int row, int column) {

		if (column == 1) {
			return renderers.getOrDefault(row, super.getCellRenderer(row, column));
		}

		return super.getCellRenderer(row, column);

	}

	public void requestUpdate(boolean updateGUI) {

		fractalSettingsManager.updateProperties();
		Application.getApplication().update(updateGUI);

	}

}
