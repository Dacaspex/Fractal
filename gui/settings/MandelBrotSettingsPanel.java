package gui.settings;

import javax.swing.JLabel;

import fractals.AbstractFractal;

public class MandelBrotSettingsPanel extends FractalSettingsPanel {

	private static final long serialVersionUID = 794795730103012414L;

	public MandelBrotSettingsPanel(AbstractFractal fractal) {

		super(fractal);

	}
	
	@Override
	protected void buildGUI() {
		
		super.buildGUI();
		
		add(new JLabel("Mandelbrot Settings"));
		
	}

}
