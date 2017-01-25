package displayUtilities.fractalDisplay;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import displayUtilities.fractalDisplay.menuBar.FileMenu;

public class FractalDisplay extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2354333139035535931L;
	
	private final int DEFAULT_DISPLAY_WIDTH;
	private final int DEFAULT_DISPLAY_HEIGHT;

	FractalPanel fractalPanel;
	
	public FractalDisplay() {
		
		// Initialize variables
		DEFAULT_DISPLAY_WIDTH = 1000;
		DEFAULT_DISPLAY_HEIGHT = 1000;
		
		// Initialize the display
		setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		setName("Fractal Drawer");
		setPreferredSize(new Dimension(DEFAULT_DISPLAY_WIDTH, DEFAULT_DISPLAY_HEIGHT));
		
		// Setup fractal functionality
		
		fractalPanel = new FractalPanel();
		
		// Build display
		JMenuBar menuBar = new JMenuBar();
		FileMenu fileMenu = new FileMenu(fractalPanel);
		
		menuBar.add(fileMenu);
		
		add(fractalPanel);
		setJMenuBar(menuBar);
		
		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	public void setLookAndFeel(String className) {

		try {
			
			UIManager.setLookAndFeel(className);
			
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			
			// TODO print a nice error message
			e.printStackTrace();
			
		}

	}

}
