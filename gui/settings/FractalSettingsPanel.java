package gui.settings;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import fractals.AbstractFractal;

public class FractalSettingsPanel extends JPanel {

	private static final long serialVersionUID = 7273863811094892240L;

	private JComboBox<String> colorSchemeList;

	private AbstractFractal fractal;

	public FractalSettingsPanel(AbstractFractal fractal) {

		colorSchemeList = new JComboBox<String>();
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

		remove(colorSchemeList);

		colorSchemeList = new JComboBox<String>() {

			private static final long serialVersionUID = -5652515143824310711L;

			@Override
			public Dimension getMaximumSize() {
				Dimension max = super.getMaximumSize();
				max.height = getPreferredSize().height;
				return max;
			}

		};

		String[][] colorSchemes = fractal.getColorSchemeManager().getAvailableColorSchemes();

		for (String[] colorScheme : colorSchemes) {

			colorSchemeList.addItem(colorScheme[1]);
			if (fractal.getColorSchemeManager().getActiveColorScheme().getIdentifier().equals(colorScheme[0])) {

				colorSchemeList.setSelectedItem(colorScheme[1]);

			}

		}

		add(colorSchemeList);
		revalidate();
		repaint();

	}

}
