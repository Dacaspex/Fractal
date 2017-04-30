package fractals.settings;

import fractals.JuliaFractal;
import gui.FractalPanel;
import gui.settings.FractalSettingsPanel;
import gui.settings.JuliaFractalSettingsPanel;

public class JuliaSettingsManager implements SettingsManager {

	private JuliaFractal juliaFractal;
	private JuliaFractalSettingsPanel settingsPanel;

	public JuliaSettingsManager(JuliaFractal juliaFractal) {

		this.juliaFractal = juliaFractal;
		this.settingsPanel = new JuliaFractalSettingsPanel(juliaFractal, this);

	}

	@Override
	public FractalSettingsPanel getSettingsPanel() {

		return settingsPanel;

	}

	public void setMaxIterations(String iterationsString) {

		try {

			int iterations = Integer.parseInt(iterationsString);

			if (iterations <= 0) {

				return;

			}

			juliaFractal.setMaxIterations(iterations);
			FractalPanel.getFractalPanel().requestUpdate();

		} catch (NumberFormatException exception) {

			return;

		}

	}

	public void setEscapeValue(String escapeValueString) {

		try {

			double escapeValue = Double.parseDouble(escapeValueString);

			if (escapeValue <= 0) {

				return;

			}

			juliaFractal.setEscapeValue(escapeValue);
			FractalPanel.getFractalPanel().requestUpdate();

		} catch (NumberFormatException exception) {

			return;

		}

	}

}
