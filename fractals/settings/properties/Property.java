package fractals.settings.properties;

import fractals.settings.properties.valdiators.Validator;

public class Property<T> {

	protected String name;
	protected PropertyType type;
	protected boolean isAction;
	protected T value;
	protected Validator validator;

	public Property(String name, PropertyType type) {
		this.name = name;
		this.type = type;
		this.isAction = false;
		this.validator = new Validator();
	}

	public Property(String name, boolean isAction) {
		this.name = name;
		this.isAction = isAction;
		this.validator = new Validator();
	}

	public Property(String name) {
		this.name = name;
		this.validator = new Validator();
	}

	public Property() {
		this.validator = new Validator();
	}

	public String getName() {
		return name;
	}

	public Property<T> setName(String name) {
		this.name = name;
		return this;
	}

	public PropertyType getType() {
		return type;
	}

	public Property<T> setType(PropertyType type) {
		this.type = type;
		return this;
	}

	public boolean isAction() {
		return isAction;
	}

	public Property<T> setValue(T value) {
		this.value = value;
		return this;
	}

	public T getValue() {
		return value;
	}

	public Validator getValidator() {
		return validator;
	}

	public Property<T> setValidator(Validator validator) {
		this.validator = validator;
		return this;
	}

	public enum PropertyType {
		INTEGER, FLOAT, COMPLEX, BOOLEAN, COLOR, SELECTION
	}

}
