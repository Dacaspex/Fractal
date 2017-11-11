package gui.settings.fractalSettings;

import java.awt.Color;
import java.util.HashMap;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import complex.Complex;
import fractals.settings.SettingsManager;
import fractals.settings.properties.Property;
import fractals.settings.properties.SelectionProperty;
import gui.settings.fractalSettings.editors.BooleanEditor;
import gui.settings.fractalSettings.editors.ColorEditor;
import gui.settings.fractalSettings.editors.ComplexEditor;
import gui.settings.fractalSettings.editors.EditorController;
import gui.settings.fractalSettings.editors.FloatEditor;
import gui.settings.fractalSettings.editors.IntegerEditor;
import gui.settings.fractalSettings.editors.SelectionEditor;
import gui.settings.fractalSettings.renderers.BooleanRenderer;
import gui.settings.fractalSettings.renderers.ColorRenderer;
import main.Application;

public class PropertyTable extends JTable {

	private static final long serialVersionUID = 6697511685217086092L;

	public static Color invalidColor = new Color(255, 114, 114);
	
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
		setRowHeight(30);
	}

	@SuppressWarnings("unchecked")
	private void addProperties() {

		for (Property<?> property : properties) {
			
			switch (property.getType()) {
			case BOOLEAN:
				model.addRow(new String[] { property.getName(), property.getValue().toString() });
				editorController.addEditor(rowCounter, new BooleanEditor((Property<Boolean>) property, this));
				renderers.put(rowCounter, new BooleanRenderer());
				break;
				
			case COLOR:
				String color = String.format("#%06x", ((Property<Color>) property).getValue().getRGB() & 0x00FFFFFF);
				model.addRow(new String[] { property.getName(), color });
				editorController.addEditor(rowCounter, new ColorEditor((Property<Color>) property, this));
				renderers.put(rowCounter, new ColorRenderer());
				break;
				
			case COMPLEX:
				model.addRow(new String[] { property.getName(), ((Complex) property.getValue()).toString() }); 
				editorController.addEditor(rowCounter, new ComplexEditor((Property<Complex>) property, this));
				break;
				
			case FLOAT:
				model.addRow(new String[] { property.getName(), property.getValue().toString() });
				editorController.addEditor(rowCounter, new FloatEditor((Property<Float>) property, this));
				break;
				
			case INTEGER:
				model.addRow(new String[] { property.getName(), property.getValue().toString() });
				editorController.addEditor(rowCounter, new IntegerEditor((Property<Integer>) property, this));
				break;
				
			case SELECTION:
				String value = ((SelectionProperty) property).getValue();
				model.addRow(new String[] { property.getName(), value });
				editorController.addEditor(rowCounter, new SelectionEditor((SelectionProperty) property, this));
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
		colorSchemeSettingsManager.updateProperties();
		Application.getApplication().update(updateGUI);

	}

}
