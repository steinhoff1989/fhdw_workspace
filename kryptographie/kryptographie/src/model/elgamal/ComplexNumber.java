package model.elgamal;

import java.math.BigInteger;

public class ComplexNumber {

	private Fraction real;

	public Fraction getReal() {
		return this.real;
	}

	public Fraction getImaginary() {
		return this.imaginary;
	}

	private Fraction imaginary;

	public ComplexNumber(final Fraction real, final Fraction imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}

	public boolean isNull() {
		return (this.real.equals(Fraction.ZERO) && this.imaginary.equals(Fraction.ZERO));
	}

	public ComplexNumber divide(final ComplexNumber divisor) {
		try {
			final ComplexNumber divisorHelper = new ComplexNumber(divisor.real,
					divisor.imaginary.multiply(Fraction.create(BigInteger.valueOf(-1), BigInteger.ONE)));

//			return this.multiply(divisorHelper);
			
			final Fraction resultDividendReal = this.real.multiply(divisorHelper.real).add(this.imaginary.multiply(
					divisorHelper.imaginary.multiply(Fraction.create(BigInteger.valueOf(-1), BigInteger.ONE))));
			final Fraction resultDividendImaginary = this.real.multiply(divisorHelper.imaginary)
					.add(this.imaginary.multiply(divisorHelper.real));

			Fraction resultDivisorReal;
			resultDivisorReal = divisor.real.multiply(divisorHelper.real).add(divisor.imaginary.multiply(
					divisorHelper.imaginary.multiply(Fraction.create(BigInteger.valueOf(-1), BigInteger.ONE))));

			return new ComplexNumber(resultDividendReal.divide(resultDivisorReal),
					resultDividendImaginary.divide(resultDivisorReal));

		} catch (final FractionConstructionException e) {
			throw new Error();
		}
	}

	public void functionF() {
		try {
			final Fraction real = this.real.subtract(Fraction.create(BigInteger.ONE, BigInteger.valueOf(2)));
			final BigInteger[] bigIntegerReal = real.getDividend().divideAndRemainder(real.getDivisor());
			if (bigIntegerReal[1].compareTo(BigInteger.ZERO) > 0) {
				bigIntegerReal[0] = bigIntegerReal[0].add(BigInteger.ONE);
			}

			final Fraction imaginary = this.imaginary.subtract(Fraction.create(BigInteger.ONE, BigInteger.valueOf(2)));
			final BigInteger[] bigIntegerImaginary = imaginary.getDividend().divideAndRemainder(imaginary.getDivisor());
			if (bigIntegerImaginary[1].compareTo(BigInteger.ZERO) > 0) {
				bigIntegerImaginary[0] = bigIntegerImaginary[0].add(BigInteger.ONE);
			}

			this.real = Fraction.create(bigIntegerReal[0], BigInteger.ONE);
			this.imaginary = Fraction.create(bigIntegerImaginary[0], BigInteger.ONE);
			
		} catch (final FractionConstructionException e) {
			throw new Error();
		}
	}

	public ComplexNumber multiply(final ComplexNumber c) {
		final Fraction resultReal = this.real.multiply(c.real).add(this.imaginary.multiply(
				c.imaginary.multiply(Fraction.NEG_ONE)));
		final Fraction resultImaginary = this.real.multiply(c.imaginary)
				.add(this.imaginary.multiply(c.real));
		return new ComplexNumber(resultReal, resultImaginary);
	}

	@Override
	public String toString(){
		return this.real.toString() + "+" + this.imaginary.toString() + "i";
	}

	public ComplexNumber subtract(final ComplexNumber subtrahend) {
		return new ComplexNumber(this.real.subtract(subtrahend.real), this.imaginary.subtract(subtrahend.imaginary));
	}
	
}
