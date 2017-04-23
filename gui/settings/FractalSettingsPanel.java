package gui.settings;

import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import fractals.AbstractFractal;
import gui.settings.utilComponents.ColorSchemeComboBox;
import gui.settings.utilComponents.ColorSchemeComboBoxItem;

public class FractalSettingsPanel extends JPanel {

	private static final long serialVersionUID = 7273863811094892240L;

	private ColorSchemeComboBox colorSchemeComboBox;

	private AbstractFractal fractal;

	public FractalSettingsPanel(AbstractFractal fractal) {

		this.colorSchemeComboBox = new ColorSchemeComboBox();
		this.fractal = fractal;

		buildGUI();

	}

	public void setFractal(AbstractFractal fractal) {

		this.fractal = fractal;

	}

	private void buildGUI() {

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		TitledBorder titleBorder = BorderFactory.createTitledBorder("Settings");
		setBorder(titleBorder);
		updateInformation();

	}

	public void updateInformation() {

		remove(colorSchemeComboBox);

		colorSchemeComboBox = new ColorSchemeComboBox();

		String[][] colorSchemes = fractal.getColorSchemeManager().getAvailableColorSchemes();

		for (String[] colorScheme : colorSchemes) {

			ColorSchemeComboBoxItem item = new ColorSchemeComboBoxItem(colorScheme[0], colorScheme[1]);
			colorSchemeComboBox.addItem(item);
			if (fractal.getColorSchemeManager().getActiveColorScheme().getIdentifier().equals(colorScheme[0])) {

				colorSchemeComboBox.setSelectedItem(colorScheme[1]);

			}

		}

		add(colorSchemeComboBox);
		revalidate();
		repaint();

	}

}
