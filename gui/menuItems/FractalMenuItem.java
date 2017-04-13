package gui.menuItems;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

import fractals.FractalManager;

public class FractalMenuItem extends JMenuItem implements ActionListener {
	
	private static final long serialVersionUID = 4134586652779444321L;
	
	private String identifier;
	private FractalManager fractalManager;

	public FractalMenuItem(String identifier, String name, FractalManager fractalManager) {
		
		this.identifier = identifier;
		this.fractalManager = fractalManager;
		
		setText(name);
		addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		
		fractalManager.setSelectedFractal(identifier);
		
	}

}
