package video;

import fractals.JuliaFractal;
import fractals.colorSchemes.JuliaColorScheme;

public class TestAnimator extends Animator {
	
	private JuliaFractal fractal;
	
	public TestAnimator(JuliaFractal fractal) {
		
		this.fractal = fractal;
		
	}
	
	public void animate() {
		
		int max = fractal.getMaxIterations();
		fractal.setMaxIterations(max + 10);
		
		JuliaColorScheme coloring = fractal.getJuliaColoring();
		coloring.setSteps(max + 10);
		coloring.generateGradientMap();
		
	}

}
