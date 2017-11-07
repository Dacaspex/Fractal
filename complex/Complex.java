package complex;

public class Complex {

	private double real;
	private double imaginary;

	public Complex(double real, double imaginary) {

		this.real = real;
		this.imaginary = imaginary;

	}

	public Complex() {

		this(0, 0);

	}

	public double getReal() {

		return real;

	}

	public void setReal(double real) {

		this.real = real;

	}

	public double getImaginary() {

		return imaginary;

	}

	public void setImaginary(double imaginary) {

		this.imaginary = imaginary;

	}

	public double getModulus() {

		return Math.sqrt(Math.pow(real, 2) + Math.pow(imaginary, 2));

	}

	public double getArgument() {

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

		real += c.getReal();
		imaginary += c.getImaginary();

		return this;

	}

	public Complex subtract(Complex c) {

		real -= c.getReal();
		imaginary -= c.getImaginary();

		return this;

	}

	public Complex multiply(Complex complex) {

		double real = this.real * complex.getReal() - this.imaginary * complex.getImaginary();
		double imaginary = this.real * complex.getImaginary() + this.imaginary * complex.getReal();

		this.real = real;
		this.imaginary = imaginary;

		return this;

	}

	public Complex multiply(double k) {

		real = k * real;
		imaginary = k * imaginary;

		return this;

	}

	public Complex square() {

		double real = Math.pow(this.real, 2) - Math.pow(this.imaginary, 2);
		double imaginary = 2 * this.real * this.imaginary;

		this.real = real;
		this.imaginary = imaginary;

		return this;

	}

	public Complex power(double power) {

		for (int i = 0; i < power - 1; i++) {

			this.multiply(this);

		}

		return this;

	}

	public Complex clone() {

		return new Complex(getReal(), getImaginary());

	}

	public String toString() {

		String glue = "";

		if (imaginary >= 0) {
			glue = "+";
		}

		return real + glue + imaginary + "i";
	}

}