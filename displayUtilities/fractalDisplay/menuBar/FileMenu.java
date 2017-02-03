package displayUtilities.fractalDisplay.menuBar;

import javax.swing.JMenu;

import displayUtilities.fractalDisplay.menuBar.fileMenu.menuItems.ExitMenuItem;
import gui.FractalPanel;

public class FileMenu extends JMenu {

	private static final long serialVersionUID = 7419183203732549510L;

	private ExitMenuItem exitMenuItem;

	public FileMenu(FractalPanel fractalDrawManager) {

		setText("File");

		exitMenuItem = new ExitMenuItem();

		add(exitMenuItem);

	}

}
