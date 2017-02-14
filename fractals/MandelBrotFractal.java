package fractals;

import java.awt.image.BufferedImage;

import org.w3c.dom.Element;

import complex.Complex;
import fractals.colorSchemes.MandelbrotColorScheme;
import util.Settings;

public class MandelBrotFractal extends AbstractFractal {
	
	private int maxIterations;
	private double escapeValue;
	
	private MandelbrotColorScheme mandleBrotColoring;
	
	public MandelBrotFractal() {
		
		name = "Mandelbrot Set";
		mandleBrotColoring = new MandelbrotColorScheme();
		
		loadDefaultSettings();
		
	}

	@Override
	public BufferedImage getImage(int imageWidth, int imageHeight) {

		BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);

		double xTransformFactor = ((scale.getxDifference()) / (double) (imageWidth - 1));
		double yTransformFactor = ((scale.getyDifference()) / (double) (imageHeight - 1));

		for (double i = 0; i < imageHeight; i++) {

			for (double j = 0; j < imageWidth; j++) {

				double x = scale.getxMin() + j * xTransformFactor;
				double y = scale.getyMin() + i * yTransformFactor;

				Complex constant = new Complex(x, y);
				
				int escapeNumber = getEscapeValue(constant);
				int colorValue = mandleBrotColoring.getRGBValue(new Object[] {escapeNumber});
				image.setRGB((int) j, (int) i, colorValue);

			}

		}
		
		return image;

	}
	
	public int getEscapeValue(Complex constant) {
		
		Complex value = new Complex(0, 0);
		
		int i;
		for (i = 0; i < maxIterations && value.getModulus() < escapeValue; i++) {
			
			value.power(2).add(constant);
			
		}
		
		return i;
		
	}
	
	@Override
	public void loadDefaultSettings() {
		
		super.loadDefaultSettings();
		
		Element defaultSettingsElement = Settings.getFractalSettingsDOM(name);
		
		int maxIterations = Integer.parseInt(defaultSettingsElement.getElementsByTagName("maxIterations").item(0).getTextContent());
		double escapeValue = Double.parseDouble(defaultSettingsElement.getElementsByTagName("escapeValue").item(0).getTextContent());
		
		this.maxIterations = maxIterations;
		this.escapeValue = escapeValue;
		
	}

}
