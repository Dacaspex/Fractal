package gui.settings;

import javax.swing.JLabel;

import fractals.AbstractFractal;

public class JuliaFractalSettingsPanel extends FractalSettingsPanel {

	private static final long serialVersionUID = 905291527554013054L;

	public JuliaFractalSettingsPanel(AbstractFractal fractal) {

		super(fractal);

	}
	
	@Override
	protected void buildGUI() {
		
		super.buildGUI();
		
		add(new JLabel("Julia Settings"));
		
	}

}
