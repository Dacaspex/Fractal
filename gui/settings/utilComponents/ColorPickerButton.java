package gui.settings.utilComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;

public class ColorPickerButton extends JButton implements ActionListener {

	private static final long serialVersionUID = -683697919564031688L;

	protected Color color;
	private JPanel wrapper;

	public ColorPickerButton(Color color) {

		this.color = color;
		this.wrapper = new JPanel();
		this.wrapper.setBackground(color);
		add(wrapper);
		setText("test");
		addActionListener(this);

	}

	public void update() {

		Color newColor = JColorChooser.showDialog(null, "Test", color);
		if (newColor != null) {
			color = newColor;
			wrapper.setBackground(color);
		}

	}

	@Override
	public Dimension getMaximumSize() {

		Dimension max = super.getMaximumSize();
		max.height = getPreferredSize().height;
		return max;

	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
