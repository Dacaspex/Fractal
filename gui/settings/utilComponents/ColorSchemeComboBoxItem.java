package gui.settings.utilComponents;

import fractals.colorSchemes.AbstractColorScheme;

public class ColorSchemeComboBoxItem {

	private String value;
	private String description;

	public ColorSchemeComboBoxItem(AbstractColorScheme colorScheme) {

		this.value = colorScheme.getIdentifier();
		this.description = colorScheme.getName();

	}

	public String getValue() {

		return value;

	}

	public void setValue(String value) {

		this.value = value;

	}

	public String getDescription() {

		return description;

	}

	public void setDescription(String description) {

		this.description = description;

	}

	@Override
	public String toString() {

		return description;

	}

}
