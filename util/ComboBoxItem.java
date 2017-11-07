package util;

public class ComboBoxItem<T> {

	private String description;
	private T value;

	public ComboBoxItem(String description, T value) {
		this.description = description;
		this.value = value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return description;
	}

}
