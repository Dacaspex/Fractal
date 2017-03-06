package video;

import java.io.File;
import java.io.IOException;

import org.jcodec.api.SequenceEncoder8Bit;
import org.jcodec.common.io.NIOUtils;
import org.jcodec.common.model.Rational;

import video.animation.AnimatorQueue;

public class VideoEncoder {
	
	private SequenceEncoder8Bit encoder;
	
	private int framesPerSecond;
	private int numberOfFrames;
	private int currentFrameNumber;
	
	private String directory;
	
	private AnimatorQueue animatorQueue;
	
	public VideoEncoder(int framesPerSecond, int numberOfFrames, String directory) {
		
		this.framesPerSecond = framesPerSecond;
		this.numberOfFrames = numberOfFrames;
		this.directory = directory;
		
	}
	
	public void initialize() {
		
		initEncoder();
		animatorQueue.initAnimators(framesPerSecond, numberOfFrames, numberOfFrames);
		
	}
	
	public boolean initEncoder() {
		
		try {

			encoder = new SequenceEncoder8Bit(NIOUtils.writableChannel(new File(directory + "test.mp4")),
					Rational.R(framesPerSecond, 1));

		} catch (IOException e) {

			e.printStackTrace();
			return false;

		}
		
		return true;
		
	}
	
	public void updateAnimators() {
		
		animatorQueue.update(currentFrameNumber);
		
	}

}
