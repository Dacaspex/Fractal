package render.threading;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ImageStitcher {

	/**
	 * TODO write documentation
	 */
	public BufferedImage stitchImages(BufferedImage[] images, int width, int height) {

		BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = resultImage.getGraphics();

		for (int i = 0; i < images.length; i++) {

			g.drawImage(images[i], (width / images.length) * i, 0, null);

		}

		g.dispose();
		return resultImage;

	}

}
