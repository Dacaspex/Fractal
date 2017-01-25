package fractals;

import java.awt.image.BufferedImage;

public interface AbstractFractal {

	BufferedImage getImage(Scale scaling, int imageWidth, int imageHeight);

	int getRGBValue(int referenceNumber);

}
