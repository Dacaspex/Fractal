package gui.settings.fractalSettings.renderers;

import java.awt.Component;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class BooleanRenderer extends JCheckBox implements TableCellRenderer {

	private static final long serialVersionUID = 1L;

	public BooleanRenderer() {
		setOpaque(true);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		if (value.toString().equals("true")) {
			this.setSelected(true);
		} else if (value.toString().equals("false")) {
			this.setSelected(false);
		} else {
			// Error
		}

		return this;

	}

}