package fractals;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import fractals.postImageProcessing.PostImageProcessor;
import fractals.threading.ImageGeneratorThread;
import fractals.threading.ImageStitcher;
import fractals.threading.ThreadFactory;
import gui.FractalPanel;
import util.Settings;

public class FractalManager {

	private HashMap<String, AbstractFractal> fractalList;

	private AbstractFractal selectedFractal;

	private FractalPanel fractalPanel;

	private BufferedImage[] imageList;
	private int requestedWidth;
	private int requestedHeight;

	private ThreadFactory threadFactory;
	private int threadsRunning;
	private final int NUMBER_OF_THREADS = 9;

	private boolean isGenerating;

	public FractalManager() {

		fractalList = new HashMap<String, AbstractFractal>();
		imageList = new BufferedImage[NUMBER_OF_THREADS];
		isGenerating = false;

		loadDefaultFractals();
		setDefaultFractal();

		ImageGeneratorThread.fractalManager = this;

	}

	public void requestImage(int width, int height) {

		if (!isGenerating) {

			requestedWidth = width;
			requestedHeight = height;
			isGenerating = true;
			threadsRunning = NUMBER_OF_THREADS;
			threadFactory = new ThreadFactory(NUMBER_OF_THREADS);
			selectedFractal.requestImage(threadFactory, width, height);

		}

	}

	public void updateProgress(BufferedImage intermediateResult, int number) {

		imageList[number] = intermediateResult;
		threadsRunning--;

		// All threads are done	
		if (threadsRunning <= 0) {
			
			// Hard kill threads
			threadFactory.killThreads();

			// Reset counter for cleaner transitions, then stitch images
			stitchImages();
			isGenerating = false;

		}

	}

	public void stitchImages() {
		
		ImageStitcher imageStitcher = new ImageStitcher();
		BufferedImage resultImage = imageStitcher.stitchImages(imageList, requestedWidth, requestedHeight);
		
		PostImageProcessor postImageProcessor = new PostImageProcessor();
		
		fractalPanel.showImage(resultImage);
		
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
