package render;

import java.awt.image.BufferedImage;

/**
 * The listener interface for receiving updates about the render state of
 * the {@link Renderer}. The class that is interested in showing the rendered image
 * by the renderer, implements this interface.
 * 
 * @author Casper
 *
 */
public interface RenderListener {

	/**
	 * Invoked when the render is finished and an image has been generated.
	 * 
	 * @param image
	 *            The generated image
	 */
	public void renderFinished(BufferedImage image);

	/**
	 * Invoked when the render failed to render an image
	 */
	public void renderFailed();

}
