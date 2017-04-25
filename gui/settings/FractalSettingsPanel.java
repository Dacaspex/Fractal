package gui.settings;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import fractals.AbstractFractal;
import fractals.colorSchemes.AbstractColorScheme;
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

	protected void buildGUI() {

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		TitledBorder titleBorder = BorderFactory.createTitledBorder("Settings");
		setBorder(titleBorder);

	}

	public void updateInformation() {

		remove(colorSchemeComboBox);

		colorSchemeComboBox = new ColorSchemeComboBox();

		AbstractColorScheme[] colorSchemes = fractal.getColorSchemeManager().getAvailableColorSchemes();

		for (AbstractColorScheme colorScheme : colorSchemes) {

			ColorSchemeComboBoxItem item = new ColorSchemeComboBoxItem(colorScheme);
			colorSchemeComboBox.addItem(item);
			if (fractal.getColorSchemeManager().getActiveColorScheme().getIdentifier()
					.equals(colorScheme.getIdentifier())) {

				colorSchemeComboBox.setSelectedItem(colorScheme);

			}

		}

		add(colorSchemeComboBox);
		revalidate();
		repaint();

	}

}
