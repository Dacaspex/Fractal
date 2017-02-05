package complex;

public class Complex {

	private double modulus;
	private double argument;

	public Complex(double modulus, double argument) {

		this.modulus = modulus;
		this.argument = argument;

	}

	public Complex() {

		this(0, 0);

	}

	public double getModulus() {

		return modulus;

	}

	public void setModulus(double modulus) {

		this.modulus = modulus;

	}

	public double getArgument() {

		return argument;

	}

	public void setArgument(double argument) {

		// TODO mod 2PI
		this.argument = argument;

	}

	public Complex set(double real, double imaginary) {

		modulus = Math.sqrt(Math.pow(real, 2) + Math.pow(imaginary, 2));
		argument = calculateArgument(real, imaginary);

		return this;

	}

	public double getReal() {

		return modulus * Math.cos(argument);

	}

	public void setReal(double real) {

		return;

	}

	public double getImaginary() {

		return modulus * Math.sin(argument);

	}

	public void setImaginary(double imaginary) {

		return;

	}

	/**
	 * Calculates the argument of the complex number. It will return the angle
	 * between the positive x axis in radians
	 * 
	 * @return The argument of the complex number in radians
	 */
	public double calculateArgument(double real, double imaginary) {

		if (real > 0) {

			return Math.atan(imaginary / real);

		} else if (real < 0 && imaginary >= 0) {

			return Math.atan(imaginary / real) + Math.PI;

		} else if (real < 0 && imaginary < 0) {

			return Math.atan(imaginary / real) - Math.PI;

		} else if (real == 0 && imaginary > 0) {

			return Math.PI / 2;

		} else if (real == 0 && imaginary < 0) {

			return -(Math.PI / 2);

		} else {

			return Integer.MAX_VALUE;

		}

	}

	public Complex add(Complex c) {

		double real = getReal();
		double imaginary = getImaginary();

		real += c.getReal();
		imaginary += c.getImaginary();

		set(real, imaginary);

		return this;

	}

	public Complex subtract(Complex c) {

		double real = c.getReal();
		double imaginary = c.getImaginary();

		real -= c.getReal();
		imaginary -= c.getImaginary();

		set(real, imaginary);

		return this;

	}

	public Complex multiply(Complex c) {

		double real = getReal() * c.getReal() - getImaginary() * c.getImaginary();
		double imaginary = getReal() * c.getImaginary() + getImaginary() * c.getReal();

		set(real, imaginary);

		return this;

	}

	public Complex multiply(double k) {

		double real = k * getReal();
		double imaginary = k * getImaginary();

		set(real, imaginary);

		return this;

	}

	public Complex power(double power) {
		
		modulus = Math.pow(modulus, power);
		argument = argument * power;

		return this;

	}

	public Complex clone() {

		return new Complex(getModulus(), getArgument());

	}

	public static Complex createFromCartesianForm(double real, double imaginary) {

		return new Complex().set(real, imaginary);

	}

}
