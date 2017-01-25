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
import fractals.Scale;

@SuppressWarnings("deprecation")
public class VideoEncoder {
	
	private SequenceEncoder8Bit encoder;

	private int framesPerSecond;
	private int numberOfFrames;
	
	private String directory;

	private AbstractFractal fractal2;
	private JuliaFractal fractal;
	
	private Animator animator;

	public VideoEncoder() {

		framesPerSecond = 10;
		numberOfFrames = 100;
		encoder = null;
		fractal = new JuliaFractal();
		directory = "";
		animator = new TestAnimator(fractal);
		
		 initEncoder();

	}
	
	public void initEncoder() {
		
		try {
			
			encoder = new SequenceEncoder8Bit(NIOUtils.writableChannel(new File(directory + "test.mp4")), Rational.R(framesPerSecond, 1));
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public void render() throws IOException {
		
		BufferedImage rawImage;
		Picture8Bit picture;
		
		long time;
		
		for (int i = 0; i < numberOfFrames; i++) {
			
			System.out.println("frame number: " + i);
			time = System.currentTimeMillis();
			
			animator.animate();
			rawImage = fractal.getImage(new Scale(-1, 1, -1, 1), 1000, 1000);
			picture = AWTUtil.fromBufferedImageRGB8Bit(rawImage);
			encoder.encodeNativeFrame(picture);
			
			System.out.println("Finished in: " + (System.currentTimeMillis() - time));
			System.out.println();
			
		}
		
		encoder.finish();
		
	}

}
