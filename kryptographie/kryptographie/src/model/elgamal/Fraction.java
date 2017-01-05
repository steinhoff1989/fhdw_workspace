package model.elgamal;

import java.math.BigInteger;

import model.ZeroPowerZeroException;

public class Fraction {

	public static final Fraction ZERO = new Fraction(BigInteger.ZERO, BigInteger.ONE);
	public static final Fraction ONE = new Fraction(BigInteger.ONE, BigInteger.ONE);
	public static final Fraction TEN = new Fraction(BigInteger.TEN, BigInteger.ONE);
	public static final Fraction NEG_ONE = new Fraction(BigInteger.valueOf(-1), BigInteger.ONE);
	private static final String FRACTION_STRING_SEPARATOR_SLASH = "/";
	
	private BigInteger dividend;
	private BigInteger divisor;

	public BigInteger getDividend() {
		return this.dividend;
	}

	public BigInteger getDivisor() {
		return this.divisor;
	}

	private Fraction(final BigInteger dividend, final BigInteger divisor){
		this.dividend = dividend;
		this.divisor=divisor;
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
	public static Fraction create(final BigInteger enumerator, final BigInteger denominator)
			throws FractionConstructionException {
		if (denominator.equals(BigInteger.ZERO))
			throw new FractionConstructionException();
		return new Fraction(enumerator, denominator).normalize();
	}
	
	/**
	 * Normalizes a LFraction object to its best representant
	 * 
	 * @return returns the best representant of am LFraction object
	 */
	private Fraction normalize() {
		if (this.divisor.compareTo(BigInteger.ZERO) == -1) {
			this.dividend = this.dividend.multiply(BigInteger.valueOf(-1));
			this.divisor = this.divisor.multiply(BigInteger.valueOf(-1));
		}
		final BigInteger gcd = this.dividend.gcd(this.divisor);
		return new Fraction(this.dividend.divide(gcd), this.divisor.divide(gcd));
	}
	
	/**
	 * Multiplies two fractions
	 * 
	 * @param factor
	 *            defines second factor
	 * @return product of two factors of type fraction
	 */
	public Fraction multiply(final Fraction factor) {
		try {
			return create(this.dividend.multiply(factor.dividend), this.divisor.multiply(factor.divisor));
		} catch (final FractionConstructionException e) {
			throw new Error(e);
		}
	}
	
	@Override
	public String toString() {
		return this.dividend.toString() + FRACTION_STRING_SEPARATOR_SLASH + this.divisor.toString();
	}
	
	/**
	 * Returns the quotient of the receiver (as dividend) and the argument
	 * divisor.
	 * 
	 * @param divisor
	 * @return Quotient of receiver and divisor
	 */
	public Fraction divide(final Fraction divisor) throws FractionConstructionException {
		return this.multiply(create(divisor.divisor, divisor.dividend));
	}
	
	/**
	 * Adds two fractions
	 * 
	 * @param summand
	 *            defines second summand
	 * @return the result of adding two summands
	 */
	public Fraction add(final Fraction summand) {
		try {
			return create(
					this.dividend.multiply(summand.divisor).add(this.divisor.multiply(summand.dividend)),
					this.divisor.multiply(summand.divisor)).normalize();
		} catch (final FractionConstructionException e) {
			throw new Error(e);
		}
	}
	
	/**
	 * Subtracts a fraction from another fraction
	 * 
	 * @param minuend
	 *            defines the fraction, which will be subtracted from the other
	 *            one.
	 * @return the result of a subtraction of two fractions
	 */
	public Fraction subtract(final Fraction minuend) {
		try {
			return Fraction.create(
					this.dividend.multiply(minuend.divisor)
							.subtract(this.divisor.multiply(minuend.dividend)),
					this.divisor.multiply(minuend.divisor)).normalize();
		} catch (final FractionConstructionException e) {
			throw new Error(e);
		}
	}

	@Override
	public boolean equals(final Object object) {
		if (object instanceof Fraction) {
			final Fraction argument = (Fraction) object;
			return this.dividend.multiply(argument.divisor)
					.equals(argument.dividend.multiply(this.divisor));
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return this.normalize().dividend.hashCode();
	}

	public Fraction power(final BigInteger exponent) throws ZeroPowerZeroException, FractionConstructionException {
		if (this.equals(Fraction.ZERO) && exponent.equals(BigInteger.ZERO)) {
			throw new ZeroPowerZeroException();
		}
		if (exponent.equals(BigInteger.ZERO)) {
			return Fraction.ONE;
		}
		if (this.equals(Fraction.ZERO)) {
			return Fraction.ZERO;
		}
		if (exponent.compareTo(BigInteger.ZERO) >= 0) {
			return this.multiply(this.power(exponent.subtract(BigInteger.ONE)));
		} else {
			final Fraction temp = Fraction.create(this.divisor, this.dividend);
			return temp.power(exponent.multiply(BigInteger.valueOf(-1)));
		}
	}
}
