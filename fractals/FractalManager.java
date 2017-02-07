package fractals;

import java.awt.image.BufferedImage;
import java.util.HashMap;

public class FractalManager {
	
	private HashMap<String, AbstractFractal> fractalList;
	
	private AbstractFractal selectedFractal;
	
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
		
		selectedFractal = fractalList.get("Julia Set");		
		
	}
	
	public void addFractal(String name, AbstractFractal fractal) {
		
		fractalList.put(name, fractal);
		
	}
	
	public AbstractFractal getSelectedFractal() {
		
		return selectedFractal;
		
	}
	
	public void setSelectedFractal(String name) {
		
		selectedFractal = fractalList.get(name);
		
	}

}
