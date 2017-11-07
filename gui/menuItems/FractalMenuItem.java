package gui.menuItems;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import fractals.AbstractFractal;
import fractals.FractalManager;
import main.Application;

public class FractalMenuItem extends JMenuItem implements ActionListener {

	private static final long serialVersionUID = 4134586652779444321L;

	private AbstractFractal fractal;
	private FractalManager fractalManager;

	public FractalMenuItem(AbstractFractal fractal, FractalManager fractalManager) {

		this.fractal = fractal;
		this.fractalManager = fractalManager;

		setText(fractal.getName());
		addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent event) {

		fractalManager.setActiveFractal(fractal.getIdentifier());
		
		JPopupMenu popupMenu = (JPopupMenu) ((JMenuItem) event.getSource()).getParent();
		JMenu menu = (JMenu) popupMenu.getInvoker();
		((OpenFractalSettingsMenuItem) menu.getMenuComponent(0)).updateText();
		
		Application.getApplication().getDisplay().getExplorerPanel().update(true);

	}

}
