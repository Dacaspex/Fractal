package gui.settings.utilComponents;

import java.awt.Font;

import javax.swing.JComponent;
import javax.swing.JLabel;

public class SettingItemComponent {

	private SettingItemPanel labelPanel;
	private SettingItemPanel componentPanel;
	private SettingItemPanelType type;

	public SettingItemComponent(String description, JComponent component) {

		JLabel label = new JLabel(description);
		this.labelPanel = new SettingItemPanel(label);
		this.componentPanel = new SettingItemPanel(component);
		type = SettingItemPanelType.SETTING;

	}

	public SettingItemComponent(String title) {

		JLabel label = new JLabel(title);
		label.setFont(new Font(null, Font.BOLD, 14));
		this.labelPanel = new SettingItemPanel(label);
		type = SettingItemPanelType.SECTION_TITLE;

	}

	public SettingItemPanel getLabel() {

		return labelPanel;

	}

	public SettingItemPanel getComponent() {

		return componentPanel;

	}
	
	public SettingItemPanelType getType() {
		
		return type;
		
	}

	public enum SettingItemPanelType {

		SETTING, SECTION_TITLE

	}

}
