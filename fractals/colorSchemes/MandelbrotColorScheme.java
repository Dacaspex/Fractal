package fractals.colorSchemes;

import java.awt.Color;

public class MandelbrotColorScheme extends SimpleWaveColorScheme {

	private double blackThreshold;

	public MandelbrotColorScheme() {

		name = "MandelBrot Colorscheme";
		blackThreshold = 0.1;

	}

	public int getRGBValue(double value) {

		if (value > 512 - blackThreshold) {

			return Color.BLACK.getRGB();

		}

		return super.getRGBValue(value);

	}

}
