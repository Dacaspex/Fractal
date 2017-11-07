package gui.settings.fractalSettings;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class PropertyTableModelListener implements TableModelListener {

	private PropertyTable table;
	
	public PropertyTableModelListener(PropertyTable table) {
		this.table = table;
	}
	
	@Override
	public void tableChanged(TableModelEvent event) {

		table.requestUpdate(false);
		
	}

}
