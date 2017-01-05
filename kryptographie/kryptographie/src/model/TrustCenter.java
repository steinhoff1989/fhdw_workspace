package model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrustCenter {
	public static int getRandomBetweenCount = 0;
	public static int runCount3Mod4 = 0;
	private static int runCountIndustryalPrime = 0;

	public static void setRunCountIndustryalPrime(final int runCountIndustryalPrime) {
		TrustCenter.runCountIndustryalPrime = runCountIndustryalPrime;
	}

	public static int getRunCountIndustryalPrime() {
		return runCountIndustryalPrime;
	}

	public static List<BigInteger> getKeys(final int binaryLength, final double propabilityPercent) {
		final BigInteger p = getIndustrialPrime(binaryLength, propabilityPercent);

		BigInteger q = getIndustrialPrime(binaryLength, propabilityPercent);

		while (q.equals(p)) {
			q = getIndustrialPrime(binaryLength, propabilityPercent);
		}

		final BigInteger n = p.multiply(q);
		final BigInteger phiN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

		System.out.println("p: " + p);
		System.out.println("q: " + q);
		System.out.println("n: " + n);
		System.out.println("phi(n): " + phiN);

		final BigInteger e = getIndustrialPrime(BigInteger.ZERO, n, propabilityPercent);
		final BigInteger d = ModArith.modularInverse(e, phiN);

		final List<BigInteger> result = new ArrayList<BigInteger>();

		result.add(p);
		result.add(q);
		result.add(n);
		result.add(e);
		result.add(d);
		result.add(phiN);

		return result;
	}

	public static BigInteger getIndustrialPrime(final int binaryLength, final double propabilityPercent) {
		final BigInteger maxValue = new BigInteger("2").pow(binaryLength).subtract(BigInteger.ONE);
		final BigInteger minValue = new BigInteger("2").pow(binaryLength - 1);
		
		return getIndustrialPrime(minValue, maxValue, propabilityPercent);
	}

	public static BigInteger getIndustrialPrime(final BigInteger minValue, final BigInteger maxValue, final double propabilityPercent) {
		final BigInteger random3Mod4 = getRandom3Mod4(minValue, maxValue);
		return getIndustrialPrimeHelper(1, propabilityPercent, minValue, maxValue, random3Mod4);
	}

	/**
	 * 
	 * @param probabilityNotPrime:
	 *            The probability that <potentialPrime> is not not prime
	 * @param minPropability:
	 *            The minimum probability that must be reached before
	 *            <potentialPrime> will returned
	 * @param binaryLength
	 * @param potentialPrime
	 * @return
	 */
//	public static BigInteger getIndustrialPrimeHelper(final double probabilityNotPrime, final double minPropability,
//			final int binaryLength, final BigInteger potentialPrime) {
//		if (1 - probabilityNotPrime >= minPropability) {
//			return potentialPrime;
//		}
//
//		if (testNumber(potentialPrime)) {
//			// runCountIndustryalPrime++;
//			return getIndustrialPrimeHelper(probabilityNotPrime * 0.5, minPropability, binaryLength, potentialPrime);
//		} else {
//			runCountIndustryalPrime++;
//			if (potentialPrime.add(new BigInteger("4"))
//					.compareTo(new BigInteger("2").pow(binaryLength).subtract(BigInteger.ONE)) <= 0) {
//				return getIndustrialPrimeHelper(1, minPropability, binaryLength,
//						potentialPrime.add(new BigInteger("4")));
//			} else {
//				return getIndustrialPrimeHelper(1, minPropability, binaryLength, getRandom3Mod4(binaryLength));
//			}
//		}
//	}

	public static BigInteger getIndustrialPrimeHelper(final double probabilityNotPrime, final double minPropability,
			final BigInteger minValue, final BigInteger maxValue, final BigInteger potentialPrime) {
		if (1 - probabilityNotPrime >= minPropability) {
			return potentialPrime;
		}

		if (testNumber(potentialPrime)) {
			// runCountIndustryalPrime++;
			return getIndustrialPrimeHelper(probabilityNotPrime * 0.5, minPropability, minValue, maxValue,
					potentialPrime);
		} else {
			runCountIndustryalPrime++;
			if (potentialPrime.add(new BigInteger("4")).compareTo(maxValue) <= 0) {
				return getIndustrialPrimeHelper(1, minPropability, minValue, maxValue,
						potentialPrime.add(new BigInteger("4")));
			} else {
				return getIndustrialPrimeHelper(1, minPropability, minValue, maxValue,
						getRandom3Mod4(minValue, maxValue));
			}
		}
	}
	
	public static boolean isPrime(final double minPropability, final BigInteger potentialPrime){
		if(potentialPrime.equals(BigInteger.valueOf(2))) return true;
		return isPrimeHelper(1, minPropability, potentialPrime);
	}

	public static boolean isPrimeHelper(final double probabilityNotPrime, final double minPropability, final BigInteger potentialPrime){
		if (1 - probabilityNotPrime >= minPropability) {
			return true;
		}

		if (testNumber(potentialPrime)) {
			return isPrimeHelper(probabilityNotPrime * 0.5, minPropability, potentialPrime);
		} else {
			return false;
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
	public static Boolean testNumber(final BigInteger potentialPrime) {
		final BigInteger random = getRandomBetween(BigInteger.ONE, potentialPrime);
		final BigInteger result = ModArith.powerModulo(random,
				(potentialPrime.subtract(BigInteger.ONE)).divide(new BigInteger("2")), potentialPrime);
		return result.equals(BigInteger.ONE) || result.equals(potentialPrime.subtract(BigInteger.ONE));
	}

	/**
	 * Returns a random BigInteger with the specified binary length
	 * <binaryLength> which equals to 3 (mod 4)
	 * 
	 * @param binaryLength
	 * @return
	 */
//	public static BigInteger getRandom3Mod4(final int binaryLength) {
//		final BigInteger random = getRandom(binaryLength);
//		if (random.mod(new BigInteger("4")).equals(new BigInteger("3"))) {
//			runCount3Mod4 = 0;
//			return random;
//		}
//		runCount3Mod4++;
//		return getRandom3Mod4(binaryLength);
//	}

	public static BigInteger getRandom3Mod4(final BigInteger minValue, final BigInteger maxValue) {
		final BigInteger random = getRandomBetween(minValue, maxValue);
		if (random.mod(new BigInteger("4")).equals(new BigInteger("3"))) {
			runCount3Mod4 = 0;
			return random;
		}
		runCount3Mod4++;
		return getRandom3Mod4(minValue, maxValue);
	}

	/**
	 * Returns a BigInteger with a binary length defined by <binaryLength>
	 * 
	 * @param binaryLength
	 *            The length of the returned BigIntegers binary equivalent
	 * @return
	 */
//	public static BigInteger getRandom(final int binaryLength) {
//		final BigInteger maxValue = new BigInteger("2").pow(binaryLength).subtract(BigInteger.ONE);
//		final BigInteger minValue = new BigInteger("2").pow(binaryLength - 1);
//
//		return getRandomBetween(minValue, maxValue);
//	}

	/**
	 * Returns a random BigInteger between <minValue> and <maxValue>
	 * 
	 * @param minValue
	 * @param maxValue
	 */
	public static BigInteger getRandomBetween(final BigInteger minValue, final BigInteger maxValue) {
		BigInteger r;
		do {
		    r = new BigInteger(maxValue.bitLength(), new Random());
		} while (r.compareTo(minValue) <= 0 || r.compareTo(maxValue) >= 0);
		
		return r;
	}
}
