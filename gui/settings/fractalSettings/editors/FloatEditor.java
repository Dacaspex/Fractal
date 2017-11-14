package gui.settings.fractalSettings.editors;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

import fractals.settings.properties.Property;
import gui.settings.fractalSettings.PropertyTable;

public class FloatEditor extends DefaultCellEditor implements TableCellEditor, KeyListener {

	private static final long serialVersionUID = 3464531358342477564L;

	private Property<Float> property;
	private PropertyTable table;

	public FloatEditor(Property<Float> property, PropertyTable table) {
		super(new JTextField());

		this.property = property;
		this.table = table;

		super.getComponent().addKeyListener(this);
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		return super.getTableCellEditorComponent(table, value, isSelected, row, column);
	}

	@Override
	public void keyPressed(KeyEvent event) {
		return;
	}

	@Override
	public void keyReleased(KeyEvent event) {

		JTextField textField = (JTextField) super.getComponent();
		String value = (String) textField.getText();
		
		if (property.getValidator().validate(value)) {
			property.setValue(Float.parseFloat(value));
			textField.setBackground(Color.WHITE);
		} else {
			textField.setBackground(PropertyTable.invalidColor);
		}

		// Check if the property wants a request update, if so, pass it on
		table.requestUpdate(false);

	}

	@Override
	public void keyTyped(KeyEvent event) {
		return;
	}

}
