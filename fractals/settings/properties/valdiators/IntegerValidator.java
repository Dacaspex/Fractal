package fractals.settings.properties.valdiators;

public class IntegerValidator extends Validator {

	public boolean allowNegative;
	public boolean allowPositive;
	public boolean allowZero;
	public int lowerBound;
	public int upperBound;

	public IntegerValidator() {
		this.allowNegative = true;
		this.allowPositive = true;
		this.allowZero = true;
		this.lowerBound = Integer.MIN_VALUE;
		this.upperBound = Integer.MAX_VALUE;
	}

	public boolean validate(Object value) {
		
		if (!isInteger(value)) {
			return false;
		}
		
		int _value = (Integer) value;
		
		return hasCorrectSign(_value);
		
	}

	public boolean isInteger(Object value) {
		return value instanceof Integer;
	}

	public boolean hasCorrectSign(int value) {
		if (!allowNegative && value < 0) {
			return false;
		} else if (!allowPositive && value > 0) {
			return false;
		} else {
			return true;
		}
	}

}
