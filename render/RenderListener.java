package render;

import java.awt.image.BufferedImage;

public interface RenderListener {
	
	public void onRenderFinished(BufferedImage image);
	
	public void onRenderFailed();

}
