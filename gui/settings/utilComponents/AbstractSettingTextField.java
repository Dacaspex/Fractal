package gui.settings.utilComponents;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import fractals.settings.SettingsManager;
import gui.settings.FractalSettingsPanel;

public abstract class AbstractSettingTextField implements DocumentListener {

	protected static final long serialVersionUID = -7520168836917423078L;

	protected FractalSettingsPanel settingsPanel;
	protected SettingsManager settingsManager;
	protected JTextField textField;
	protected JLabel descriptionLabel;
	protected String description;
	protected String defaultText;

	public AbstractSettingTextField(FractalSettingsPanel settingsPanel, SettingsManager settingsManager,
			String description, String defaultText) {

		this.settingsPanel = settingsPanel;
		this.settingsManager = settingsManager;
		this.description = description;
		this.defaultText = defaultText;
		this.textField = new JTextField();
		this.descriptionLabel = new JLabel();

	}

	public void buildGUI() {

		descriptionLabel.setText(description);

		textField.setText(defaultText);
		textField.getDocument().addDocumentListener(this);

		SettingItemPanel labelPanel = new SettingItemPanel(descriptionLabel);
		SettingItemPanel textFieldPanel = new SettingItemPanel(textField);

		settingsPanel.add(labelPanel);
		settingsPanel.add(textFieldPanel);

	}

	public abstract void documentUpdate(DocumentEvent event);

	@Override
	public void changedUpdate(DocumentEvent e) {

		documentUpdate(e);

	}

	@Override
	public void insertUpdate(DocumentEvent e) {

		documentUpdate(e);

	}

	@Override
	public void removeUpdate(DocumentEvent e) {

		documentUpdate(e);

	}

}
