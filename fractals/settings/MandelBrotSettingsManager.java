package fractals.settings;

import fractals.MandelBrotFractal;
import gui.FractalPanel;
import gui.settings.FractalSettingsPanel;
import gui.settings.MandelBrotSettingsPanel;

public class MandelBrotSettingsManager implements SettingsManager {

	private MandelBrotFractal mandelBrotFractal;
	private MandelBrotSettingsPanel settingsPanel;

	public MandelBrotSettingsManager(MandelBrotFractal mandelBrotFractal) {

		this.mandelBrotFractal = mandelBrotFractal;
		this.settingsPanel = new MandelBrotSettingsPanel(mandelBrotFractal, this);

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

			mandelBrotFractal.setMaxIterations(iterations);
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

			mandelBrotFractal.setEscapeValue(escapeValue);
			FractalPanel.getFractalPanel().requestUpdate();

		} catch (NumberFormatException exception) {

			return;

		}

	}

}
