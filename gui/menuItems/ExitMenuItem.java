package gui.menuItems;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;

public class ExitMenuItem extends JMenuItem implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3584786912157820751L;
	
	public ExitMenuItem() {
		
		setText("Exit");
		addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		System.exit(0);
		
	}

}
