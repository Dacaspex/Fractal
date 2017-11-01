package fractals.settings.properties.valdiators;

public class IntegerValidator extends Validator {

	private boolean negativeAllowed;
	private boolean positiveAllowed;
	private boolean zeroAllowed;
	private int lowerBound;
	private int upperBound;

	public IntegerValidator() {
		this.negativeAllowed = true;
		this.positiveAllowed = true;
		this.zeroAllowed = true;
		this.lowerBound = Integer.MIN_VALUE;
		this.upperBound = Integer.MAX_VALUE;
	}
	
	public IntegerValidator setNegativeAllowed(boolean negativeAllowed) {
		this.negativeAllowed = negativeAllowed;
		return this;
	}
	
	public IntegerValidator setPositiveAllowed(boolean positiveAllowed) {
		this.positiveAllowed = positiveAllowed;
		return this;
	}
	
	public IntegerValidator setZeroAllowed(boolean zeroAllowed) {
		this.zeroAllowed = zeroAllowed;
		return this;
	}
	
	public IntegerValidator setLowerBound(int lowerBound) {
		this.lowerBound = lowerBound;
		return this;
	}
	
	public IntegerValidator setUpperBound(int upperBound) {
		this.upperBound = upperBound;
		return this;
	}

	public boolean validate(Object object) {

		if (!isInteger(object)) {
			return false;
		}

		int value = Integer.parseInt((String) object);

		return hasCorrectSign(value) && isZeroAllowed(value);

	}

	public boolean isInteger(Object value) {
		try {
			Integer.parseInt((String) value);
		} catch (NumberFormatException exception) {
			return false;
		}
		return true;
	}

	public boolean hasCorrectSign(int value) {
		if (!negativeAllowed && value < 0) {
			message = "The value is not allowed to be negative";
			return false;
		} else if (!positiveAllowed && value > 0) {
			message = "The value is not allowed to be positive";
			return false;
		}
		return true;
	}

	public boolean isZeroAllowed(int value) {
		if (!zeroAllowed && value == 0) {
			message = "The value is not allowed to be zero";
			return false;
		}
		return true;
	}

	public boolean isBetweenBounds(int value) {
		if (value < lowerBound || value > upperBound) {
			message = "The value is outside the bounds";
			return false;
		}
		return true;
	}

}
