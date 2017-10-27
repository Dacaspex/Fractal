package gui.settings.fractalSettings.editors;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

import fractals.settings.properties.Property;

public class IntegerEditor extends DefaultCellEditor implements TableCellEditor {

	private static final long serialVersionUID = 3464531358342477564L;

	private Property<Integer> property;

	public IntegerEditor(Property<Integer> property) {
		super(new JTextField());

		this.property = property;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

		property.setValue(Integer.parseInt((String) value));

		return super.getTableCellEditorComponent(table, value, isSelected, row, column);
	}

}
