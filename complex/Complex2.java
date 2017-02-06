package complex;

public class Complex2 {

	private double real;
	private double imaginary;

	public Complex2(double real, double imaginary) {

		this.real = real;
		this.imaginary = imaginary;

	}

	public Complex2() {

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

	public double getMagnitude() {

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

	public Complex2 add(Complex c) {

		real += c.getReal();
		imaginary += c.getImaginary();

		return this;

	}

	public Complex2 subtract(Complex c) {

		real -= c.getReal();
		imaginary -= c.getImaginary();

		return this;

	}

	public Complex2 multiply(Complex2 complex) {

		double real = this.real * complex.getReal() - this.imaginary * complex.getImaginary();
		double imaginary = this.real * complex.getImaginary() + this.imaginary * complex.getReal();

		this.real = real;
		this.imaginary = imaginary;

		return this;

	}

	public Complex2 multiply(double k) {

		real = k * real;
		imaginary = k * imaginary;

		return this;

	}

	public Complex2 square() {

		double real = Math.pow(this.real, 2) - Math.pow(this.imaginary, 2);
		double imaginary = 2 * this.real * this.imaginary;

		this.real = real;
		this.imaginary = imaginary;

		return this;

	}

	public Complex2 power(int power) {

		Complex2 complex = clone();

		for (int i = 0; i < power - 1; i++) {

			this.multiply(complex);

		}

		return this;

	}

	public Complex2 clone() {

		return new Complex2(getReal(), getImaginary());

	}

}