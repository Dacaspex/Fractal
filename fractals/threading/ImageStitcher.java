package fractals.threading;

import java.awt.image.BufferedImage;

public class ImageStitcher {
	
	public BufferedImage stitchImages(BufferedImage[] images, int width, int height) {
		
		BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		int step = (int) Math.sqrt(images.length);
		
		// TODO stich images
		
		return resultImage;
		
	}

}
