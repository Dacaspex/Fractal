package gui.settings;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import fractals.AbstractFractal;

public class InformationPanel extends JPanel {

	private static final long serialVersionUID = 8778798234532864944L;

	private List<JLabel> labels;
	private AbstractFractal fractal;

	public InformationPanel(AbstractFractal fractal) {

		labels = new ArrayList<JLabel>();
		this.fractal = fractal;

		buildGUI();

	}

	public void setFractal(AbstractFractal fractal) {

		this.fractal = fractal;

	}

	private void buildGUI() {

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		TitledBorder titleBorder = BorderFactory.createTitledBorder("Information");
		setBorder(titleBorder);

		updateInformation();

	}

	public void updateInformation() {

		removeLabels();

		labels = new ArrayList<JLabel>();
		Iterator<Entry<String, String>> iterator = fractal.getInformation().entrySet().iterator();
		JLabel label;

		while (iterator.hasNext()) {

			Map.Entry<String, String> entry = (Entry<String, String>) iterator.next();
			label = new JLabel("<html><font color=black>" + entry.getKey() + "</font> : <font color=blue>"
					+ entry.getValue() + "</font></html>");
			labels.add(label);
			this.add(label);

		}

		revalidate();
		repaint();

	}

	public void removeLabels() {

		for (JLabel label : labels) {

			remove(label);

		}

	}

}
