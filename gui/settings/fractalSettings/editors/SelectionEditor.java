package gui.settings.fractalSettings.editors;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.table.TableCellEditor;

import fractals.settings.properties.ColorSchemeSelectorProperty;
import gui.settings.fractalSettings.PropertyTable;
import util.ComboBoxItem;

public class SelectionEditor extends DefaultCellEditor implements TableCellEditor, ItemListener {

	private static final long serialVersionUID = 3464531358342477564L;

	private ColorSchemeSelectorProperty property;
	private PropertyTable table;
	private JComboBox<String> comboBox;

	@SuppressWarnings("unchecked")
	public SelectionEditor(ColorSchemeSelectorProperty property, PropertyTable table) {
		super(new JComboBox<ComboBoxItem<String>>(generateList(property)));

		this.property = property;
		this.table = table;
		this.comboBox = (JComboBox<String>) super.getComponent();

		comboBox.addItemListener(this);

	}

	private static ComboBoxItem<String>[] generateList(ColorSchemeSelectorProperty property) {

		@SuppressWarnings("unchecked")
		ComboBoxItem<String>[] list = new ComboBoxItem[property.getList().size()];

		int index = 0;
		for (ComboBoxItem<String> item : property.getList()) {
			list[index++] = item;
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void itemStateChanged(ItemEvent event) {
		
		if (event.getStateChange() == 1) {
			property.setValue(((ComboBoxItem<String>) comboBox.getSelectedItem()).getValue());
		}

		table.requestUpdate(true);

	}

}
