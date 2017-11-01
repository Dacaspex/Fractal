package fractals.settings.properties.valdiators;

public class Validator {
	
	protected String message;
	
	public String getMessage() {
		return message;
	}
	
	public boolean validate(Object value) {
		return true;
	}

}
