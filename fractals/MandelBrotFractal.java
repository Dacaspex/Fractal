package fractals;

import java.awt.image.BufferedImage;

import complex.Complex;
import fractals.colorSchemes.MandelbrotColorScheme;

public class MandelBrotFractal extends AbstractFractal {
	
	private int maxIterations;
	private MandelbrotColorScheme mandleBrotColoring;
	
	public MandelBrotFractal() {
		
		maxIterations = 512;
		mandleBrotColoring = new MandelbrotColorScheme();
		scale = new Scale(-1.5, 0.5, -1, 1);
		
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
		for (i = 0; i < maxIterations && value.getModulus() < 2; i++) {
			
			value.power(2).add(constant);
			
		}
		
		return i;
		
	}

}
