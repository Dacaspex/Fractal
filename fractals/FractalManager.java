package fractals;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import fractals.threads.Worker;
import gui.FractalPanel;
import util.Settings;

public class FractalManager {

	private HashMap<String, AbstractFractal> fractalList;

	private AbstractFractal selectedFractal;

	private FractalPanel fractalPanel;
	
	private BufferedImage[] imageList;
	private int count;
	private int requestedWidth;
	private int requestedHeight;

	public FractalManager() {

		fractalList = new HashMap<String, AbstractFractal>();
		imageList = new BufferedImage[4];
		count = 4; // Number of workers
		requestedWidth = 0;
		requestedHeight = 0;

		loadDefaultFractals();
		setDefaultFractal();
		
		Worker.fractalManager = this;

	}

	public void requestImage(int width, int height) {
		
		requestedWidth = width;
		requestedHeight = height;
		selectedFractal.requestImage(width, height);

	}
	
	public void updateProgress(BufferedImage intermediateResult, int number) {
		
		imageList[number] = intermediateResult;
		count--;
		
		if (count <= 0) {
			
			generateImage();
			
		}
		
	}
	
	public void generateImage() {
		
		BufferedImage image = new BufferedImage(requestedWidth, requestedHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) image.getGraphics();
		
		g.drawImage(imageList[0], 1, 1, null);
		g.drawImage(imageList[1], requestedWidth / 2, 1, null);
		g.drawImage(imageList[2], 1, requestedHeight / 2, null);
		g.drawImage(imageList[3], requestedWidth / 2, requestedHeight / 2, null);
		
		fractalPanel.showImage(image);
		
	}
	
	public void drawImage() {
		
		
		
	}

	/**
	 * Loads the default fractals into the list
	 */
	public void loadDefaultFractals() {

		fractalList.put("Julia Set", new JuliaFractal());
		fractalList.put("Mandelbrot Set", new MandelBrotFractal());

	}

	/**
	 * Sets the default fractal to be displayed at startup, set in the
	 * settings.xml
	 */
	public void setDefaultFractal() {

		selectedFractal = Settings.getDefaultFractal(this);

	}

	/**
	 * Add a fractal to the list of available fractals by it's name and the
	 * fractal itself
	 * 
	 * @param name
	 *            The name of the fractal. Should be a unique name
	 * @param fractal
	 *            An instance of the fractal
	 */
	public void addFractal(String name, AbstractFractal fractal) {

		fractalList.put(name, fractal);

	}

	/**
	 * @return The currently selected fractal
	 */
	public AbstractFractal getSelectedFractal() {

		return selectedFractal;

	}

	/**
	 * Sets the selected fractal to be drawn. Also updates the screen to draw
	 * the new fractal
	 * 
	 * @param name
	 *            The name of the fractal to be selected
	 */
	public void setSelectedFractal(String name) {

		selectedFractal = fractalList.get(name);
		updateFractalPanel();

	}

	/**
	 * @return A list of names of the loaded fractals
	 */
	public String[] getLoadedFractals() {

		String[] fractalNames = new String[fractalList.size()];
		return fractalList.keySet().toArray(fractalNames);

	}

	/**
	 * Function to specify which FractalPanel draws the fractal
	 * 
	 * @param fractalPanel
	 *            The fractalPanel which draws the fractal
	 */
	public void setFractalPanel(FractalPanel fractalPanel) {

		this.fractalPanel = fractalPanel;

	}

	/**
	 * Forces the FractalPanel to draw a fractal
	 */
	public void updateFractalPanel() {

		fractalPanel.draw();

	}
	
	public AbstractFractal getFractalByName(String name) {
		
		return fractalList.get(name);
		
	}

}
