package video.animation;

import java.util.ArrayList;

import video.animation.animators.AbstractAnimator;

public class AnimatorQueue {
	
	private ArrayList<AbstractAnimator> queue;
	
	public AnimatorQueue() {
		
		queue = new ArrayList<AbstractAnimator>();
		
	}
	
	public void initAnimators(int framesPerSecond, int numberOfFrames, int currentFrameNumber) {
		
		AbstractAnimator.setFramesPerSecond(framesPerSecond);
		AbstractAnimator.setNumberOfFrames(numberOfFrames);
		AbstractAnimator.setCurrentFrameNumber(currentFrameNumber);
		
	}
	
	public void update(int currentFrameNumber) {
		
		AbstractAnimator.setCurrentFrameNumber(currentFrameNumber);
		
	}
	
	public void animate() {
		
		for (AbstractAnimator animator : queue) {
			
			animator.animate();
			
		}
		
	}

}
