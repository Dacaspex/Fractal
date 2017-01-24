package video;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.jcodec.api.SequenceEncoder8Bit;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Picture8Bit;
import org.jcodec.common.model.Rational;
import org.jcodec.scale.AWTUtil;

import fractals.AbstractFractal;
import fractals.JuliaFractal;
import fractals.Scaling;

@SuppressWarnings("deprecation")
public class VideoEncoder {

	private int framesPerSecond;
	private int numberOfFrames;

	private String directory;

	private Animator animator;

	private AbstractFractal fractal;

	public VideoEncoder() {

		framesPerSecond = 10;
		numberOfFrames = 100;

		directory = "";

		// TODO testing purposes
		fractal = new JuliaFractal();

	}
	
	public void render() {
		
		
		
	}

	public void renderTest() {
		
		SequenceEncoder8Bit enc = null;

		try {
			
			enc = new SequenceEncoder8Bit(NIOUtils.writableChannel(new File("test.mp4")), Rational.R(framesPerSecond, 1));
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
		BufferedImage image = fractal.getImage(new Scaling(-1, 1, -1, 1), 1920, 1080);
		
		Picture8Bit picture = AWTUtil.fromBufferedImageRGB8Bit(image);
		
		try {
			enc.encodeNativeFrame(picture);
			enc.encodeNativeFrame(picture);
			enc.encodeNativeFrame(picture);
			enc.encodeNativeFrame(picture);
			enc.encodeNativeFrame(picture);
			enc.encodeNativeFrame(picture);
			enc.encodeNativeFrame(picture);
			enc.encodeNativeFrame(picture);
			enc.encodeNativeFrame(picture);
			enc.encodeNativeFrame(picture);
			
			enc.finish();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
