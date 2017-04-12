package fractals.colorSchemes;

import java.awt.Color;

public class MandelbrotColorScheme extends AbstractColorScheme {
	
	public MandelbrotColorScheme() {
		
		name = "MandelBrot Colorscheme";
		
	}

	public int getRGBValue(double value) {
		
		double delta = 0.1;
		
		if (value > 512 - delta) {
			
			return Color.BLACK.getRGB();
			
		}

		int r = (int) Math.abs((Math.sin(0.016 * value + 4) * 230 + 25));
		int g = (int) Math.abs((Math.sin(0.013 * value + 2) * 230 + 25));
		int b = (int) Math.abs((Math.sin(0.01 * value + 1) * 230 + 25));

		return new Color(r, g, b).getRGB();

	}

}
