package gui.settings.fractalSettings.editors;

import java.awt.Component;
import java.util.HashMap;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

public class EditorController extends AbstractCellEditor implements TableCellEditor {

	private static final long serialVersionUID = -2060586099889639333L;

	private HashMap<Integer, DefaultCellEditor> editors;
	private DefaultCellEditor lastSelected;

	public EditorController() {
		editors = new HashMap<Integer, DefaultCellEditor>();
	}

	public void addEditor(int key, DefaultCellEditor editor) {
		editors.put(key, editor);
	}

	@Override
	public Object getCellEditorValue() {
		return lastSelected.getCellEditorValue();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		if (column == 1) {
			lastSelected = editors.get(row);
		}

		return lastSelected.getTableCellEditorComponent(table, value, isSelected, row, column);
	}

}
