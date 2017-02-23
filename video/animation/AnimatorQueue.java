package video.animation;

import java.util.ArrayList;

import video.animation.animators.AbstractAnimator;

public class AnimatorQueue {
	
	private ArrayList<AbstractAnimator> queue;
	
	public AnimatorQueue() {
		
		queue = new ArrayList<AbstractAnimator>();
		
	}
	
	public void update(int currentFrameNumber) {
		
		for (AbstractAnimator animator : queue) {
			
			animator.setCurrentFrameNumber(currentFrameNumber);
			
		}
		
	}
	
	public void animate() {
		
		for (AbstractAnimator animator : queue) {
			
			animator.animate();
			
		}
		
	}

}
