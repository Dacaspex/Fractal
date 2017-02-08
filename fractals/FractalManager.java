package fractals;

import java.awt.image.BufferedImage;
import java.util.HashMap;

import gui.FractalPanel;
import util.Settings;

public class FractalManager {
	
	private HashMap<String, AbstractFractal> fractalList;
	
	private AbstractFractal selectedFractal;
	
	private FractalPanel fractalPanel;
	
	public FractalManager() {
		
		fractalList = new HashMap<String, AbstractFractal>();
		
		loadDefaultFractals();
		setDefaultFractal();
		
	}
	
	public BufferedImage generateImage(int width, int height) {
		
		return selectedFractal.getImage(width, height);
		
	}
	
	public void loadDefaultFractals() {
		
		fractalList.put("Julia Set", new JuliaFractal());
		fractalList.put("Mandelbrot Set", new MandelBrotFractal());
		
	}
	
	public void setDefaultFractal() {
		
		selectedFractal = fractalList.get(Settings.getDefaultFractal());
		
	}
	
	public void addFractal(String name, AbstractFractal fractal) {
		
		fractalList.put(name, fractal);
		
	}
	
	public AbstractFractal getSelectedFractal() {
		
		return selectedFractal;
		
	}
	
	public void setSelectedFractal(String name) {
		
		selectedFractal = fractalList.get(name);
		updateFractalPanel();
		
	}
	
	public String[] getLoadedFractals() {
		
		String[] fractalNames = new String[fractalList.size()];
		return fractalList.keySet().toArray(fractalNames);
		
	}
	
	public void setFractalPanel(FractalPanel fractalPanel) {
		
		this.fractalPanel = fractalPanel;
		
	}
	
	public void updateFractalPanel() {
		
		fractalPanel.draw();
		
	}

}
