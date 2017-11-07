package gui.settings.fractalSettings.editors;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.table.TableCellEditor;

import fractals.settings.properties.Property;
import gui.settings.fractalSettings.PropertyTable;

public class BooleanEditor extends DefaultCellEditor implements TableCellEditor, ActionListener {

	private static final long serialVersionUID = 3464531358342477564L;

	private JCheckBox checkBox;
	private Property<Boolean> property;
	private PropertyTable table;

	public BooleanEditor(Property<Boolean> property, PropertyTable table) {
		super(new JCheckBox());

		this.checkBox = (JCheckBox) super.getComponent();
		this.property = property;
		this.table = table;

		checkBox.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		property.setValue(checkBox.isSelected());
		table.requestUpdate(false);
		
	}

}
