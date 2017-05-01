package util;

import complex.Complex;

public class ComplexValueParser {

	public static Complex parseFromString(String value) {

		if (ComplexValueParser.countOccurrenceInString(value, "+")
				+ ComplexValueParser.countOccurrenceInString(value, "-") > 2) {

			return null;

		}

		String splitter = "+";
		double real = Double.NaN;
		double imaginary = Double.NaN;
		boolean realIsPositive = true;
		boolean imaginaryIsPositive = true;

		if (value.subSequence(0, 1).toString().contains("-")) {

			realIsPositive = false;

		}

		if (value.substring(1).contains("-")) {

			splitter = "-";
			imaginaryIsPositive = false;

		}

		String[] tokens = value.substring(1).replaceAll(",", ".").split("[" + splitter + "]");

		for (String token : tokens) {

			try {

				if (token.contains("i") && token.indexOf("i") == token.length() - 1) {

					String parsedToken = (String) token.subSequence(0, token.length() - 1);
					imaginary = Double.parseDouble(parsedToken);

				} else {

					real = Double.parseDouble(token);

				}

			} catch (NumberFormatException exception) {

				return null;

			}

		}

		if (!realIsPositive) {

			real = -real;

		}

		if (!imaginaryIsPositive) {

			imaginary = -imaginary;

		}

		return new Complex(real, imaginary);

	}

	public static int countOccurrenceInString(String haystack, String needle) {

		int lastIndex = 0;
		int count = 0;

		while (lastIndex != -1) {

			lastIndex = haystack.indexOf(needle, lastIndex);

			if (lastIndex != -1) {

				count++;
				lastIndex += needle.length();

			}
		}

		return count;

	}

}
