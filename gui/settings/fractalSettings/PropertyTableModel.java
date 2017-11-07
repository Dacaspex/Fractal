package gui.settings.fractalSettings;

import javax.swing.table.DefaultTableModel;

public class PropertyTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 117071011432651443L;

	public PropertyTableModel(String[] headers, String[][] data) {
		super(data, headers);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return (column == 1);
	}

}
