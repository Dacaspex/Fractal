package fractals;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import fractals.threading.ImageGenerator;
import gui.FractalPanel;
import util.Settings;

public class FractalManager {

	private HashMap<String, AbstractFractal> fractalList;

	private AbstractFractal selectedFractal;

	private FractalPanel fractalPanel;

	private BufferedImage[] imageList;
	private int threadCount;
	private int requestedWidth;
	private int requestedHeight;

	private boolean isGenerating;

	public FractalManager() {

		fractalList = new HashMap<String, AbstractFractal>();
		imageList = new BufferedImage[4];
		// TODO Create threads dynamicly according to settings
		threadCount = 4; // Number of threads
		requestedWidth = 0;
		requestedHeight = 0;
		isGenerating = false;

		loadDefaultFractals();
		setDefaultFractal();

		ImageGenerator.fractalManager = this;

	}

	public void requestImage(int width, int height) {

		if (!isGenerating) {

			requestedWidth = width;
			requestedHeight = height;
			isGenerating = true;
			selectedFractal.requestImage(width, height);

		}

	}

	public void updateProgress(BufferedImage intermediateResult, int number) {

		imageList[number] = intermediateResult;
		threadCount--;

		if (threadCount <= 0) {

			// Reset counter for cleaner transitions, then stitch images
			threadCount = 4;
			stitchImages();
			isGenerating = false;

		}

	}

	public void stitchImages() {

		BufferedImage image = new BufferedImage(requestedWidth, requestedHeight, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) image.getGraphics();

		g.drawImage(imageList[0], 1, 1, null);
		g.drawImage(imageList[1], requestedWidth / 2, 1, null);
		g.drawImage(imageList[2], 1, requestedHeight / 2, null);
		g.drawImage(imageList[3], requestedWidth / 2, requestedHeight / 2, null);

		g.dispose();

		fractalPanel.showImage(image);

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
