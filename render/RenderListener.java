package render;

import java.awt.image.BufferedImage;

public interface RenderListener {

	public void renderFinished(BufferedImage image);

	public void renderFailed();

}
