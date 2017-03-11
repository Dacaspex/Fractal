package video.animation.animators;

public abstract class AbstractAnimator {
	
	/**
	 * The frames per second defined by the video encoder
	 */
	protected static int framesPerSecond;
	
	/**
	 * The number of frames that the total animation will have
	 */
	protected static int numberOfFrames;
	
	/**
	 * The current frame number of the animation
	 */
	protected static int currentFrameNumber;
	
	public static int getFramesPerSecond() {
		
		return framesPerSecond;
		
	}

	public static void setFramesPerSecond(int framesPerSecond) {
		
		AbstractAnimator.framesPerSecond = framesPerSecond;
		
	}

	public static int getNumberOfFrames() {
		
		return numberOfFrames;
		
	}

	public static void setNumberOfFrames(int numberOfFrames) {
		
		AbstractAnimator.numberOfFrames = numberOfFrames;
		
	}

	public static int getCurrentFrameNumber() {
		
		return currentFrameNumber;
		
	}

	public static void setCurrentFrameNumber(int currentFrameNumber) {
		
		AbstractAnimator.currentFrameNumber = currentFrameNumber;
		
	}

	public abstract void animate();

}
