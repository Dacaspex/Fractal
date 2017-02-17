package fractals;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class TestFractal extends AbstractFractal {

	@Override
	public BufferedImage getImage(int imageWidth, int imageHeight) {
		
		BufferedImage image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
		
		for (int i = 0; i < imageWidth; i++) {
			
			for (int j = 0; j < imageHeight; j++) {
				
				image.setRGB(i, j, Color.GREEN.getRGB());
				
			}
			
		}
		
		return image;
		
	}

}
