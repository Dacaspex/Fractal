package gui.settings.utilComponents;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TextFieldComponent extends JTextField implements DocumentListener {

	private static final long serialVersionUID = 6321075506736836219L;

	public TextFieldComponent(String defaultText) {

		this.setText(defaultText);
		this.getDocument().addDocumentListener(this);

	}

	public void documentUpdate(DocumentEvent event) {

	}

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
