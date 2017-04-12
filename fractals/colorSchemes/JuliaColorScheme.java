package fractals.colorSchemes;

import java.awt.Color;

public class JuliaColorScheme extends AbstractColorScheme {
	
	public JuliaColorScheme() {
		
		identifier = "JuliaColorScheme1";
		name = "Julia Color Scheme";
		
	}

	public int getRGBValue(double continuousIndex) {

		int r = (int) Math.abs((Math.sin(0.016 * continuousIndex + 4) * 230 + 25));
		int g = (int) Math.abs((Math.sin(0.013 * continuousIndex + 2) * 230 + 25));
		int b = (int) Math.abs((Math.sin(0.01 * continuousIndex + 1) * 230 + 25));

		return new Color(r, g, b).getRGB();

	}

}
