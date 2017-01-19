package fractals;

import java.awt.image.BufferedImage;

public interface AbstractFractal {
	
	 BufferedImage getImage(Scaling scaling, int imageWidth, int imageHeight);
	 
	 int getRGBValue(int number);

}
