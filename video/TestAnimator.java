package video;

import fractals.JuliaFractal;
import fractals.colorSchemes.SimpleWaveColorScheme;
import video.animation.animators.AbstractAnimator;

public class TestAnimator extends AbstractAnimator {
	
	private JuliaFractal fractal;
	
	public TestAnimator(JuliaFractal fractal) {
		
		this.fractal = fractal;
		
	}
	
	public void animate() {
		
		int max = fractal.getMaxIterations();
		fractal.setMaxIterations(max + 10);
		
	}

}
