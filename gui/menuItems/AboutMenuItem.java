package gui.menuItems;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;

import javax.swing.JMenuItem;

public class AboutMenuItem extends JMenuItem implements ActionListener {
	
	private static final long serialVersionUID = -4588441455796244750L;
	
	private URI uri;

	public AboutMenuItem() {
		
		String url = "https://github.com/Dacaspex/Fractal#fractal";
		uri = URI.create(url);
		
		setText("About");
		addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		try {
			
			Desktop.getDesktop().browse(uri);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
	}

}
