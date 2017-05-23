package gui.settings.utilComponents;

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JColorChooser;
import javax.swing.JPanel;

public class ColorPickerButton extends ButtonComponent {

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
	public void actionPerformed(ActionEvent e) {

	}

}
