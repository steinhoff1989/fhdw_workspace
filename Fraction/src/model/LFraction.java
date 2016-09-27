package model;

import java.math.BigInteger;

public class LFraction  {

	public static final LFraction ZERO = new LFraction(BigInteger.ZERO, BigInteger.ONE);
	public static final LFraction ONE = new LFraction(BigInteger.ONE, BigInteger.ONE);
	public static final LFraction TEN = new LFraction(BigInteger.TEN, BigInteger.ONE);
	public static final LFraction NEG_ONE = new LFraction(BigInteger.valueOf(-1), BigInteger.ONE);

	private BigInteger enumerator;
	private BigInteger denominator;

	private static final String FRACTION_STRING_SEPARATOR_SLASH = "/";
	private static final String FRACTION_STRING_SEPARATOR_DOT = ".";
	private static final String FRACTION_STRING_SEPARATOR_E = "E";
	private static final int FRACTION_STRING_SEPARATOR_SLASH_LENGTH = FRACTION_STRING_SEPARATOR_SLASH.length();
	private static final int FRACTION_STRING_SEPARATOR_DOT_LENGTH = FRACTION_STRING_SEPARATOR_DOT.length();
	// private static final int FRACTION_STRING_SEPARATOR_E_LENGTH =

	private LFraction(final BigInteger enumerator, final BigInteger denominator) {
		this.enumerator = enumerator;
		this.denominator = denominator;
	}

	/**
	 * Returns a fraction in normalized form, i.e. enumerator and denominator
	 * have been divided by its gcd.
	 * 
	 * @param enumerator
	 *            is the intended enumerator of the result
	 * @param denominator
	 *            is the intended denominator of the result
	 * @return the normalized fraction equal to enumerator / denominator.
	 * @throws FractionConstructionException
	 *             if the given denominator is equal to zero
	 */
	public static LFraction create(final BigInteger enumerator, final BigInteger denominator)
			throws FractionConstructionException {
		if (denominator.equals(BigInteger.ZERO))
			throw new FractionConstructionException();
		return new LFraction(enumerator, denominator).normalise();
	}

	/**
	 * Creates a Fraction object in normalized form.
	 * 
	 * @param fractionString
	 * @return
	 * @throws FractionConstructionException
	 */
	public static LFraction create(final String fractionString) throws FractionConstructionException {
		final int fractionSeparatorSlashPosition = fractionString.indexOf(FRACTION_STRING_SEPARATOR_SLASH);
		try {
			if (fractionSeparatorSlashPosition >= 0) {
				final String enumeratorString = fractionString.substring(0, fractionSeparatorSlashPosition);
				final String denominatorString = fractionString.substring(
						fractionSeparatorSlashPosition + FRACTION_STRING_SEPARATOR_SLASH_LENGTH,
						fractionString.length());
				if (enumeratorString.equals("")) {
					return create(BigInteger.valueOf(1), new BigInteger(denominatorString));
				} else {
					return create(new BigInteger(enumeratorString), new BigInteger(denominatorString));
				}
			} else if (fractionString.indexOf(FRACTION_STRING_SEPARATOR_E) >= 0) {
				// final String beforeE =
				// fractionString.substring(0,fractionString.indexOf(FRACTION_STRING_SEPARATOR_E));
				// final String afterE =
				// fractionString.substring(fractionString.indexOf(FRACTION_STRING_SEPARATOR_E)
				// + 1, fractionString.length());
				//
				// int exponent = Integer.valueOf(afterE);
				//
				// return
				// LFraction.create(beforeE).multiply(LFraction.create(String.valueOf(BigInteger.TEN.pow(exponent))));
				//

				final String fractionDecimalString = Double.toString(Double.parseDouble(fractionString));
				return stringWithDotToFraction(fractionDecimalString,
						fractionDecimalString.indexOf(FRACTION_STRING_SEPARATOR_DOT));

			} else if (fractionString.indexOf(FRACTION_STRING_SEPARATOR_DOT) >= 0) {
				final int fractionSeparatorDotPosition = fractionString.indexOf(FRACTION_STRING_SEPARATOR_DOT);
				return stringWithDotToFraction(fractionString, fractionSeparatorDotPosition);
			} else {
				return LFraction.create(new BigInteger(fractionString), BigInteger.ONE);
			}
		} catch (IndexOutOfBoundsException | NumberFormatException re) {
			throw new FractionConstructionException();
		}
	}

	private static LFraction stringWithDotToFraction(final String fractionString,
			final int fractionSeparatorDotPosition) throws FractionConstructionException {
		final String beforeDot = fractionString.substring(0, fractionSeparatorDotPosition);
		final String afterDot = fractionString.substring(
				fractionSeparatorDotPosition + FRACTION_STRING_SEPARATOR_DOT_LENGTH, fractionString.length());
		final String enumeratorString = beforeDot + afterDot;
		final int decimalplaces = fractionString.length() - FRACTION_STRING_SEPARATOR_DOT_LENGTH
				- fractionSeparatorDotPosition;
		// final String denominatorString = BigInteger.valueOf((long)
		// Math.pow(10, decimalplaces)).toString();
		final BigInteger denominator = BigInteger.valueOf(10).pow(decimalplaces);
		return create(new BigInteger(enumeratorString), denominator);
	}

	/**
	 * Normalizes a LFraction object to its best representant
	 * 
	 * @return returns the best representant of am LFraction object
	 */
	private LFraction normalise() {
		if (this.denominator.compareTo(BigInteger.ZERO) == -1) {
			this.enumerator = this.enumerator.multiply(BigInteger.valueOf(-1));
			this.denominator = this.denominator.multiply(BigInteger.valueOf(-1));
		}
		final BigInteger gcd = this.enumerator.gcd(this.denominator);
		return new LFraction(this.enumerator.divide(gcd), this.denominator.divide(gcd));
	}

	public BigInteger getEnumerator() {
		return enumerator;
	}

	public BigInteger getDenominator() {
		return denominator;
	}

	/**
	 * Adds two fractions
	 * 
	 * @param summand
	 *            defines second summand
	 * @return the result of adding two summands
	 */
	public LFraction add(final LFraction summand) {
		try {
			return create(
					this.enumerator.multiply(summand.denominator).add(this.denominator.multiply(summand.enumerator)),
					this.denominator.multiply(summand.denominator)).normalise();
		} catch (final FractionConstructionException e) {
			throw new Error(e);
		}
	}

	/**
	 * Multiplies two fractions
	 * 
	 * @param factor
	 *            defines second factor
	 * @return product of two factors of type fraction
	 */
	public LFraction multiply(final LFraction factor) {
		try {
			return create(this.enumerator.multiply(factor.enumerator), this.denominator.multiply(factor.denominator));
		} catch (final FractionConstructionException e) {
			throw new Error(e);
		}
	}

	/**
	 * Returns the quotient of the receiver (as dividend) and the argument
	 * divisor.
	 * 
	 * @param divisor
	 * @return Quotient of receiver and divisor
	 */
	public LFraction divide(final LFraction divisor) throws FractionConstructionException {
		return this.multiply(create(divisor.denominator, divisor.enumerator));
	}

	/**
	 * Subtracts a fraction from another fraction
	 * 
	 * @param minuend
	 *            defines the fraction, which will be subtracted from the other
	 *            one.
	 * @return the result of a subtraction of two fractions
	 */
	public LFraction subtract(final LFraction minuend) {
		try {
			return LFraction.create(
					this.enumerator.multiply(minuend.denominator)
							.subtract(this.denominator.multiply(minuend.enumerator)),
					this.denominator.multiply(minuend.denominator)).normalise();
		} catch (final FractionConstructionException e) {
			throw new Error(e);
		}
	}

	@Override
	public boolean equals(final Object object) {
		if (object instanceof LFraction) {
			final LFraction argument = (LFraction) object;
			return this.enumerator.multiply(argument.denominator)
					.equals(argument.enumerator.multiply(this.denominator));
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		return this.enumerator.toString() + FRACTION_STRING_SEPARATOR_SLASH + this.denominator.toString();
	}

	public int hashCode() {
		return this.normalise().enumerator.hashCode();
	}

	public LFraction power(BigInteger exponent) throws ZeroPowerZeroException, FractionConstructionException {
		if (this.equals(LFraction.ZERO) && exponent.equals(BigInteger.ZERO)) {
			throw new ZeroPowerZeroException();
		}
		if (exponent.equals(BigInteger.ZERO)) {
			return LFraction.ONE;
		}
		if (this.equals(LFraction.ZERO)) {
			return LFraction.ZERO;
		}
		if (exponent.compareTo(BigInteger.ZERO) >= 0) {
			return this.multiply(this.power(exponent.subtract(BigInteger.ONE)));
		} else {
			LFraction temp = LFraction.create(this.denominator, this.enumerator);
			return temp.power(exponent.multiply(BigInteger.valueOf(-1)));
		}
	}

//	@Override
//	public int compareTo(Object o) {
//		if (o instanceof LFraction) {
//			LFraction temp = (LFraction) o;
//			try {
//				LFraction first = LFraction.create(this.enumerator.multiply(temp.denominator),
//						this.denominator.multiply(((LFraction) o).denominator));
//				LFraction second = LFraction.create(temp.enumerator.multiply(this.denominator),
//						this.denominator.multiply(((LFraction) o).denominator));
//				return first.enumerator.compareTo(second.enumerator);
//			} catch (FractionConstructionException e) {
//				throw new Error();
//			}
//		}
//		throw new Error();
//	}
}
