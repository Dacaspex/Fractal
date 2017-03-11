package fractals.threading;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ImageStitcher {

	public BufferedImage stitchImages(BufferedImage[] images, int width, int height) {

		BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = resultImage.getGraphics();
		int columnWidth = (int) Math.sqrt(images.length);
		int partialImageWidth = images[0].getWidth() - 1;
		int partialImageHeight = images[0].getHeight() - 1;
		int xStart = 0;
		int yStart = 0;
		int imageIndex = 0;

		for (int i = 0; i < columnWidth; i++) {

			xStart = 0;

			for (int j = 0; j < columnWidth; j++) {

				g.drawImage(images[imageIndex++], 1 + xStart, 1 + yStart, null);
				xStart += partialImageWidth;

			}

			yStart += partialImageHeight;

		}

		g.dispose();
		return resultImage;

	}

}
