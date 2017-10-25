package gui.menuItems;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import main.Application;

public class OpenFractalSettingsMenuItem extends JMenuItem implements ActionListener {

	private static final long serialVersionUID = 1801459055270646617L;

	public OpenFractalSettingsMenuItem() {

		addActionListener(this);
		updateText();

	}
	
	public void updateText() {
		
		String fractalName = Application.getApplication().getDisplay().getExplorerPanel().getFractalManager()
				.getActiveFractal().getName();
		setText("Fractal settings ( " + fractalName + " )");
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {

		Application.getApplication().getDisplay().openFractalSettings();

	}

}
