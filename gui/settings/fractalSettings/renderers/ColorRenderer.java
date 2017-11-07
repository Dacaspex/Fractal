package gui.settings.fractalSettings.renderers;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class ColorRenderer extends JPanel implements TableCellRenderer {

	private static final long serialVersionUID = 1L;

	public ColorRenderer() {
		setOpaque(true);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		String hex = (String) value;

		Color color = new Color(
				Integer.valueOf(hex.substring(1, 3), 16),
				Integer.valueOf(hex.substring(3, 5), 16),
				Integer.valueOf(hex.substring(5, 7), 16));

		this.setBackground(color);

		return this;

	}

}