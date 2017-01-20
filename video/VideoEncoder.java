package video;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import fractals.AbstractFractal;
import fractals.JuliaFractal;
import fractals.Scaling;

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
		
		// TODO temporary disabled
//		BufferedImage frame = fractal.getImage(new Scaling(1, -1, 1, -1), 1000, 1000);
//		File outputFile = new File(directory + "\\test.png");
//		
//		try {
//			ImageIO.write(frame, "PNG", outputFile);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}

}
