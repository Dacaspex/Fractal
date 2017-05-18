package gui.settings.utilComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ColorPickerButton extends JButton implements ActionListener {

	private static final long serialVersionUID = -683697919564031688L;
	
	private Color color;

	public ColorPickerButton(Color color) {

		this.color = color;
		setText("test");
		addActionListener(this);
		update();

	}
	
	public void update() {
		
		setBackground(color);
		
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
