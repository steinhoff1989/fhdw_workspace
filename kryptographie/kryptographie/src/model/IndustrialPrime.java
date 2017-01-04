package model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IndustrialPrime {

	private int remainder;
	private int modulo;
	private BigInteger value;
	private BigInteger xToSquare;
	private BigInteger yToSquare;

	public IndustrialPrime() {
	};

	public IndustrialPrime(final int binaryLength, final double propabilityPercent, final int remainder,
			final int modulo) {
		this(new BigInteger("2").pow(binaryLength - 1), new BigInteger("2").pow(binaryLength).subtract(BigInteger.ONE),
				propabilityPercent, remainder, modulo);
	}

	public IndustrialPrime(final BigInteger minValue, final BigInteger maxValue, final double propabilityPercent,
			final int remainder, final int modulo) {
		this.remainder = remainder;
		this.modulo = modulo;
		final BigInteger random = this.getRandom(minValue, maxValue, remainder, modulo);

		this.value = this.getIndustrialPrimeHelper(1, propabilityPercent, minValue, maxValue, random, remainder,
				modulo);
		final List<BigInteger> xSquaredPlusYSquared = this.getValueAsXSquaredPlusYSquared();
		this.xToSquare = xSquaredPlusYSquared.get(0);
		this.yToSquare = xSquaredPlusYSquared.get(1);
	}

	private BigInteger getRandom(final BigInteger minValue, final BigInteger maxValue, final int remainder,
			final int modulo) {
		BigInteger random;
		random = this.getRandomBetween(minValue, maxValue);
		if (random.mod(BigInteger.valueOf(modulo)).equals(BigInteger.valueOf(remainder))) {
			return random;
		}
		return this.getRandom(minValue, maxValue, remainder, modulo);
	}

	/**
	 * Returns a random BigInteger between <minValue> and <maxValue>
	 * 
	 * @param minValue
	 * @param maxValue
	 */
	private BigInteger getRandomBetween(final BigInteger minValue, final BigInteger maxValue) {
		BigInteger r;
		do {
		    r = new BigInteger(maxValue.bitLength(), new Random());
		} while (r.compareTo(minValue) <= 0 || r.compareTo(maxValue) >= 0);
		
		return r;
		
//		final BigInteger random = new BigDecimal(maxValue).multiply(new BigDecimal(Math.random())).toBigInteger();
//		Math
//
//		if (random.compareTo(maxValue) <= 0 && random.compareTo(minValue) >= 0) {
//			return random;
//		} else {
//			return this.getRandomBetween(minValue, maxValue);
//		}
	}

	private BigInteger getIndustrialPrimeHelper(final double probabilityNotPrime, final double minPropability,
			final BigInteger minValue, final BigInteger maxValue, final BigInteger potentialPrime, final int remainder,
			final int modulo) {
		if (1 - probabilityNotPrime >= minPropability) {
			return potentialPrime;
		}

		if (this.testNumber(potentialPrime)) {
			return this.getIndustrialPrimeHelper(probabilityNotPrime * 0.5, minPropability, minValue, maxValue,
					potentialPrime, remainder, modulo);
		} else {
			if (potentialPrime.add(BigInteger.valueOf(modulo)).compareTo(maxValue) <= 0) {
				return this.getIndustrialPrimeHelper(1, minPropability, minValue, maxValue,
						potentialPrime.add(BigInteger.valueOf(modulo)), remainder, modulo);
			} else {
				return this.getIndustrialPrimeHelper(1, minPropability, minValue, maxValue,
						this.getRandom(minValue, maxValue, remainder, modulo), remainder, modulo);
			}
		}
	}

	/**
	 * Tests for a random number between 0 and <potentialPrime> if
	 * <potentialPrime> is a a valid prime. Each call will reduce the
	 * probability that <potentialPrime> is not a prime by 0.5 Example: If
	 * calling <testNumber> 5x, the probability that <potentialPrime> is prime
	 * will be 1-1/32
	 * 
	 * @param potentialPrime
	 * @return
	 */
	private Boolean testNumber(final BigInteger potentialPrime) {
		final BigInteger random = this.getRandomBetween(BigInteger.ZERO, potentialPrime);
		final BigInteger result = ModArith.powerModulo(random,
				(potentialPrime.subtract(BigInteger.ONE)).divide(new BigInteger("2")), potentialPrime);
		return result.equals(BigInteger.ONE) || result.equals(potentialPrime.subtract(BigInteger.ONE));
	}

	public boolean isSquareReminder(final BigInteger c) {
		final BigInteger exponent = (this.value.subtract(BigInteger.ONE)).divide(BigInteger.valueOf(2));
		final BigInteger result = ModArith.powerModulo(c, exponent, this.value);
		if (result.equals(BigInteger.valueOf(1))) {
			return true;
		}
		if (result.equals(this.value.subtract(BigInteger.ONE))) {
			return false;
		} else {
			throw new Error();
		}
	}

	private List<BigInteger> getValueAsXSquaredPlusYSquared(){
		if(this.value.mod(BigInteger.valueOf(4)).equals(BigInteger.ONE)){
			final BigInteger randomZ = this.getRandomBetween(BigInteger.ONE, this.value);
			if(!this.isSquareReminder(randomZ)){
				final BigInteger exponent = this.value.subtract(BigInteger.ONE).divide(BigInteger.valueOf(4));
				final BigInteger w = ModArith.powerModulo(randomZ, exponent, this.value);
				
				final ComplexNumber gcd = ModArith.euclidComplex(this.value, w);
				
				final List<BigInteger> result = new ArrayList<BigInteger>();
				
				if(gcd.getReal().getDivisor().equals(BigInteger.ONE) &&
						gcd.getImaginary().getDivisor().equals(BigInteger.ONE)){
					
					if(gcd.getReal().getDividend().mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)){
						result.add(0, gcd.getReal().getDividend());
						result.add(1, gcd.getImaginary().getDividend());
					}else{
						result.add(0, gcd.getImaginary().getDividend());
						result.add(1, gcd.getReal().getDividend());
					}
					
					return result;
				}else{
					return this.getValueAsXSquaredPlusYSquared();
				}
			}else{
				return this.getValueAsXSquaredPlusYSquared();
			}
		}else{
			throw new UnsupportedOperationException();
		}
	}

	public BigInteger getxToSquare() {
		return this.xToSquare;
	}

	public BigInteger getyToSquare() {
		return this.yToSquare;
	}

	public int getRemainder() {
		return this.remainder;
	}

	public int getModulo() {
		return this.modulo;
	}

	public BigInteger getValue() {
		return this.value;
	}

	//Testing only!
	protected void setValue(final BigInteger value) {
		this.value = value;
	}
}
