package render.threading;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * This class is a utility class for the threading functionality of the program.
 * It provides a stitching method that follows from the subdivision of the
 * images created by the multi-threading.
 * 
 * @author Casper
 * @see ImageGeneratorThread
 *
 */
public class ImageStitcher {

	/**
	 * Stitches a group of images together in a horizontal way.
	 * 
	 * @param images
	 *            The set of images
	 * @param width
	 *            The width of the result image
	 * @param height
	 *            The height of the result image
	 * @return The stitched image comprised of the image list
	 */
	public BufferedImage stitch(BufferedImage[] images, int width, int height) {

		// Initialize
		BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = resultImage.getGraphics();

		// Stitch
		for (int i = 0; i < images.length; i++) {
			g.drawImage(images[i], (width / images.length) * i, 0, null);
		}

		g.dispose();
		return resultImage;

	}

}
