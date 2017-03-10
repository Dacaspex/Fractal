package fractals.threading;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ImageStitcher {
	
	public BufferedImage stitchImages(BufferedImage[] images, int width, int height) {
		
		BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = resultImage.getGraphics();
		
		int xScaleStep = (int) (width / Math.sqrt(images.length));
		int yScaleStep = (int) (height / Math.sqrt(images.length));
		
		for (int i = 0; i < images.length; i++) {
			
			int xFactor = Math.floorMod(i, (int) Math.sqrt(images.length));
			int yFactor = Math.floorDiv(i, (int) Math.sqrt(images.length));
			
			g.drawImage(images[i], xFactor * xScaleStep, yFactor * yScaleStep, null);
			
		}
		
		g.dispose();
		return resultImage;
		
	}

}
