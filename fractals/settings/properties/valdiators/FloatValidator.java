package fractals.settings.properties.valdiators;

public class FloatValidator extends Validator {
	
	private float upperBound;
	private float lowerBound;
	
	public FloatValidator() {
		
		this.upperBound = Float.MAX_VALUE;
		this.lowerBound = -Float.MAX_VALUE;
		
	}
	
	public FloatValidator setUpperBound(float upperBound) {
		this.upperBound = upperBound;
		return this;
	}
	
	public FloatValidator setLowerBound(float lowerBound) {
		this.lowerBound = lowerBound;
		return this;
	}
	
	@Override
	public boolean validate(Object object) {
		
		if (!isFloat(object)) {
			return false;
		}
		
		float value = Float.parseFloat((String) object);
		
		return isBetweenBounds(value);
		
	}
	
	public boolean isBetweenBounds(float value) {
		if (value < lowerBound || value > upperBound) {
			message = "The value is outside the bounds";
			return false;
		}
		return true;
	}
	
	public boolean isFloat(Object value) {
		try {
			Float.parseFloat((String) value);
		} catch (NumberFormatException exception) {
			return false;
		}
		return true;
	}


}
