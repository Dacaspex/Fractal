package gui.settings;

import java.awt.Dimension;
import java.util.Iterator;
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
	
	private AbstractFractal fractal;
	
	public InformationPanel(AbstractFractal fractal) {
		
		this.fractal = fractal;
		
		buildGUI();
		
	}
	
	private void buildGUI() {
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		TitledBorder titleBorder = BorderFactory.createTitledBorder("Information");
		setBorder(titleBorder);
		
		showInformation();
				
	}
	
	public void showInformation() {
		
		Iterator<Entry<String, String>> iterator = fractal.getInformation().entrySet().iterator();
		
		while (iterator.hasNext()) {
			
			Map.Entry<String, String> entry = (Entry<String, String>) iterator.next();
			
			JLabel label = new JLabel(entry.getKey() + " : " + entry.getValue());
			add(label);
			
		}
		
	}

}
