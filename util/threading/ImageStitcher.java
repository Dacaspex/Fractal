package util.threading;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ImageStitcher {

	/**
	 * This method stitches an array of images into a bigger image containing
	 * these images. It will do this in a raster format defined by the number of
	 * threads.
	 * 
	 * @param images
	 *            THe array of images that need to be stitched
	 * @param width
	 *            The result image width
	 * @param height
	 *            The result image height
	 * @return An image of width * height made up from the images in the images
	 *         array
	 */
	public BufferedImage stitchImages(BufferedImage[] images, int width, int height) {

		BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = resultImage.getGraphics();
		
		for (int i = 0; i < images.length; i++) {
			
			g.drawImage(images[i], (width / images.length) * i, 0, null);
			
		}
		
		g.dispose();
		return resultImage;

		// BufferedImage resultImage = new BufferedImage(width, height,
		// BufferedImage.TYPE_INT_RGB);
		// Graphics g = resultImage.getGraphics();
		// int columnWidth = (int) Math.sqrt(images.length);
		// int partialImageWidth = images[0].getWidth() - 1;
		// int partialImageHeight = images[0].getHeight() - 1;
		// int xStart = 0;
		// int yStart = 0;
		// int imageIndex = 0;
		//
		// for (int i = 0; i < columnWidth; i++) {
		//
		// xStart = 0;
		//
		// for (int j = 0; j < columnWidth; j++) {
		//
		// g.drawImage(images[imageIndex++], 1 + xStart, 1 + yStart, null);
		// xStart += partialImageWidth;
		//
		// }
		//
		// yStart += partialImageHeight;
		//
		// }
		//
		// g.dispose();
		// return resultImage;

	}

}
