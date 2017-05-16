package fractals.settings;

import javax.swing.event.DocumentEvent;

import complex.Complex;
import fractals.JuliaFractal;
import gui.FractalPanel;
import test.SettingItemComponent;
import test.TextFieldComponent;
import util.ComplexValueParser;

public class JuliaSettingsManager implements SettingsManager {

	private JuliaFractal juliaFractal;

	public JuliaSettingsManager(JuliaFractal juliaFractal) {

		this.juliaFractal = juliaFractal;

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

	public void setConstant(String value) {

		Complex constant = ComplexValueParser.parseFromString(value);

		if (constant != null) {

			juliaFractal.setConstant(constant);
			FractalPanel.getFractalPanel().requestUpdate();

		}

	}

	@Override
	public SettingItemComponent[] getSettingComponents() {

		// Max iterations
		TextFieldComponent maxIterationsTextField = new TextFieldComponent(
				Integer.toString(juliaFractal.getMaxIterations())) {
			private static final long serialVersionUID = -6033309334749328555L;

			@Override
			public void documentUpdate(DocumentEvent event) {
				setMaxIterations(this.getText());
			}
		};
		SettingItemComponent maxIterationsSettingItem = new SettingItemComponent("Max iterations:",
				maxIterationsTextField);

		// Escape value
		TextFieldComponent escapeValueTextField = new TextFieldComponent(
				Double.toString(juliaFractal.getEscapeValue())) {
			private static final long serialVersionUID = -6033309334749328555L;

			@Override
			public void documentUpdate(DocumentEvent event) {
				setEscapeValue(this.getText());
			}
		};
		SettingItemComponent escapeValueSettingsItem = new SettingItemComponent("Escape value:", escapeValueTextField);

		// Constant
		String constantString = Double.toString(juliaFractal.getConstant().getReal());
		constantString += (juliaFractal.getConstant().getImaginary() < 0) ? "-" : "+";
		constantString += Double.toString(juliaFractal.getConstant().getImaginary());
		constantString += "i";
		TextFieldComponent constantTextField = new TextFieldComponent(constantString) {
			private static final long serialVersionUID = -6033309334749328555L;

			@Override
			public void documentUpdate(DocumentEvent event) {
				setConstant(this.getText());
			}
		};
		SettingItemComponent constantSettingItem = new SettingItemComponent("Constant:", constantTextField);

		return new SettingItemComponent[] { maxIterationsSettingItem, escapeValueSettingsItem, constantSettingItem };
	}

}
