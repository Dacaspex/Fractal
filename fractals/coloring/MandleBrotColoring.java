package fractals.coloring;

import java.awt.Color;

public class MandleBrotColoring extends AbstractColorScheme {
	
	public MandleBrotColoring() {
		
		name = "MandelBrot Colorscheme";
		
	}

	@Override
	public int getRGBValue(Object[] object) {
		
		double continuousIndex = (double) (int) object[0];
		
		double delta = 0.1;
		
		if (continuousIndex > 512 - delta) {
			
			return Color.BLACK.getRGB();
			
		}

		int r = (int) Math.abs((Math.sin(0.016 * continuousIndex + 4) * 230 + 25));
		int g = (int) Math.abs((Math.sin(0.013 * continuousIndex + 2) * 230 + 25));
		int b = (int) Math.abs((Math.sin(0.01 * continuousIndex + 1) * 230 + 25));

		return new Color(r, g, b).getRGB();

	}

}
