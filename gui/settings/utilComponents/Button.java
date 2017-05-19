package gui.settings.utilComponents;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Button extends JButton implements ActionListener {

	private static final long serialVersionUID = 4938619758521996365L;

	public Button() {

		addActionListener(this);

	}
	
	public Button(String text) {
		
		this();
		setText(text);
		
	}

	@Override
	public Dimension getMaximumSize() {

		Dimension max = super.getMaximumSize();
		max.height = getPreferredSize().height;
		return max;

	}

	@Override
	public void actionPerformed(ActionEvent event) {
	}

}
