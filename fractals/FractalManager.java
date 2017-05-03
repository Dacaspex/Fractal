package fractals;

import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.TreeMap;

import fractals.threading.ImageGeneratorThread;
import fractals.threading.ImageStitcher;
import fractals.threading.ThreadFactory;
import gui.FractalPanel;
import imageProcessing.post.PostImageProcessor;
import util.Settings;

public class FractalManager {

	private TreeMap<String, AbstractFractal> fractalList;

	private AbstractFractal selectedFractal;

	private FractalPanel fractalPanel;

	private BufferedImage[] imageList;
	private int requestedWidth;
	private int requestedHeight;

	private ThreadFactory threadFactory;
	private int threadsRunning;
	private final int NUMBER_OF_THREADS = 9;

	private FractalGeneratingState generatingState;

	public FractalManager() {

		// Initialize variables
		fractalList = new TreeMap<String, AbstractFractal>();
		imageList = new BufferedImage[NUMBER_OF_THREADS];
		generatingState = FractalGeneratingState.IDLE;

		// Load and set default fractal
		loadDefaultFractals();
		setDefaultFractal();

		ImageGeneratorThread.fractalManager = this;

	}

	public FractalGeneratingState getGeneratingState() {

		return generatingState;

	}

	public void requestImage(int width, int height) {

		// Start generating a new fractal if no other fractal is being generated
		if (generatingState == FractalGeneratingState.IDLE) {

			requestedWidth = width;
			requestedHeight = height;
			generatingState = FractalGeneratingState.GENERATING_IMAGE;
			threadsRunning = NUMBER_OF_THREADS;

			// Create the thread factory that handles the thread creation
			threadFactory = new ThreadFactory(NUMBER_OF_THREADS);
			selectedFractal.requestImage(threadFactory, width, height);

		}

	}

	public void updateProgress(BufferedImage intermediateResult, int number) {

		// Update the current progress
		imageList[number] = intermediateResult;
		threadsRunning--;

		// All threads are done
		if (threadsRunning <= 0) {

			generatingState = FractalGeneratingState.STITCHING_IMAGE;

			// Hard kill threads
			threadFactory.killThreads();

			// Reset counter for cleaner transitions, then stitch images
			BufferedImage resultImage = stitchImages();
			fractalPanel.showImage(resultImage);
			generatingState = FractalGeneratingState.IDLE;

		}

	}

	public BufferedImage stitchImages() {

		// Create the image stitcher
		ImageStitcher imageStitcher = new ImageStitcher();
		BufferedImage resultImage = imageStitcher.stitchImages(imageList, requestedWidth, requestedHeight);

		// TODO move to separate method
		PostImageProcessor postImageProcessor = new PostImageProcessor(selectedFractal, false);
		postImageProcessor.applyEffects(resultImage);

		return resultImage;

	}

	/**
	 * Loads the default fractals into the list
	 */
	public void loadDefaultFractals() {

		fractalList.put("JuliaSet1", new JuliaFractal());
		fractalList.put("MandelBrotSet1", new MandelBrotFractal());

	}

	/**
	 * Sets the default fractal to be displayed at startup, set in the
	 * settings.xml
	 */
	public void setDefaultFractal() {

		selectedFractal = Settings.getDefaultFractal(this);

	}

	/**
	 * Add a fractal to the list of available fractals by it's identifier and
	 * the fractal itself
	 * 
	 * @param identifier
	 *            The identifier of the fractal. Should be a unique identifier
	 * @param fractal
	 *            An instance of the fractal
	 */
	public void addFractal(String identifier, AbstractFractal fractal) {

		fractalList.put(identifier, fractal);

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
	 * @param identifier
	 *            The identifier of the fractal to be selected
	 */
	public void setSelectedFractal(String identifier) {

		selectedFractal = fractalList.get(identifier);
		updateFractalPanel();

	}

	/**
	 * @return A list of names of the loaded fractals
	 */
	public String[][] getLoadedFractals() {

		String[][] list = new String[fractalList.size()][2];
		int index = 0;

		for (Map.Entry<String, AbstractFractal> entry : fractalList.entrySet()) {

			list[index][0] = entry.getValue().getIdentifier();
			list[index++][1] = entry.getValue().getName();

		}

		return list;

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

		fractalPanel.requestUpdate();

	}

	public AbstractFractal getFractalByIdentifier(String identifier) {

		if (!identifier.equals("")) {

			return fractalList.get(identifier);

		} else {

			return fractalList.firstEntry().getValue();

		}

	}

	public enum FractalGeneratingState {

		IDLE, GENERATING_IMAGE, STITCHING_IMAGE

	}

}
