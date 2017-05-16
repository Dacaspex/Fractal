package gui.settings.utilComponents;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class SettingItemComponent {

	private SettingItemPanel labelPanel;
	private SettingItemPanel componentPanel;

	public SettingItemComponent(String description, JComponent component) {

		JLabel label = new JLabel(description);
		this.labelPanel = new SettingItemPanel(label);
		this.componentPanel = new SettingItemPanel(component);

	}

	public SettingItemPanel getLabel() {

		return labelPanel;

	}

	public SettingItemPanel getComponent() {

		return componentPanel;

	}

}
