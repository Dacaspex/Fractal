package gui.settings.fractalSettings.editors;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

import fractals.settings.properties.Property;
import gui.settings.fractalSettings.PropertyTable;

public class ColorEditor extends DefaultCellEditor implements TableCellEditor {

	private static final long serialVersionUID = 7229147340165887264L;

	private JButton delegate;

	private Property<Color> property;
	private PropertyTable table;

	public ColorEditor(Property<Color> property, PropertyTable table) {
		super(new JTextField());

		this.property = property;
		this.table = table;
		this.delegate = new JButton() {
			private static final long serialVersionUID = -4366203882491791946L;

			@Override
		    public void paintComponent(Graphics g) {
		        g.setColor(ColorEditor.this.property.getValue());
		        g.fillRect(0, 0, this.getWidth(), this.getHeight());
		    }
		};

		ActionListener actionListener = new ActionListener() {

			public void actionPerformed(ActionEvent actionEvent) {
				Color color = JColorChooser.showDialog(delegate, "Color Chooser", property.getValue());
				ColorEditor.this.changeColor(color);
				ColorEditor.this.table.requestUpdate(false);
			}

		};

		delegate.addActionListener(actionListener);
	}

	public Object getCellEditorValue() {
		return String.format("#%06x", property.getValue().getRGB() & 0x00FFFFFF);
	}

	private void changeColor(Color color) {
		if (color != null) {
			property.setValue(color);
			delegate.setBackground(color);
		}
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

		String hex = (String) value;
		Color color = new Color(Integer.valueOf(hex.substring(1, 3), 16), Integer.valueOf(hex.substring(3, 5), 16),
				Integer.valueOf(hex.substring(5, 7), 16));

		changeColor(color);
		return delegate;
	}
}
