package model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class TrustCenter {
	public static int getRandomBetweenCount = 0;
	public static int runCount3Mod4 = 0;
	private static int runCountIndustryalPrime = 0;

	public static void setRunCountIndustryalPrime(int runCountIndustryalPrime) {
		TrustCenter.runCountIndustryalPrime = runCountIndustryalPrime;
	}

	public static int getRunCountIndustryalPrime() {
		return runCountIndustryalPrime;
	}

	public static List<BigInteger> getKeys(int binaryLength, double propabilityPercent) {
		BigInteger p = getIndustrialPrime(binaryLength, propabilityPercent);

		BigInteger q = getIndustrialPrime(binaryLength, propabilityPercent);

		while (q.equals(p)) {
			q = getIndustrialPrime(binaryLength, propabilityPercent);
		}

		BigInteger n = p.multiply(q);
		BigInteger phiN = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

		System.out.println("p: " + p);
		System.out.println("q: " + q);
		System.out.println("n: " + n);
		System.out.println("phi(n): " + phiN);

		BigInteger e = getIndustrialPrime(BigInteger.ZERO, n, propabilityPercent);
		BigInteger d = ModArith.modularInverse(e, phiN);

		List<BigInteger> result = new ArrayList<BigInteger>();

		result.add(p);
		result.add(q);
		result.add(n);
		result.add(e);
		result.add(d);
		result.add(phiN);

		return result;
	}

	

	public static BigInteger getIndustrialPrime(int binaryLength, double propabilityPercent) {
		BigInteger random3Mod4 = getRandom3Mod4(binaryLength);
		return getIndustrialPrimeHelper(1, propabilityPercent, binaryLength, random3Mod4);
	}

	public static BigInteger getIndustrialPrime(BigInteger minValue, BigInteger maxValue, double propabilityPercent) {
		BigInteger random3Mod4 = getRandom3Mod4(minValue, maxValue);
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
	public static BigInteger getIndustrialPrimeHelper(double probabilityNotPrime, double minPropability,
			int binaryLength, BigInteger potentialPrime) {
		if (1 - probabilityNotPrime >= minPropability) {
			return potentialPrime;
		}

		if (testNumber(potentialPrime)) {
			// runCountIndustryalPrime++;
			return getIndustrialPrimeHelper(probabilityNotPrime * 0.5, minPropability, binaryLength, potentialPrime);
		} else {
			runCountIndustryalPrime++;
			if (potentialPrime.add(new BigInteger("4"))
					.compareTo(new BigInteger("2").pow(binaryLength).subtract(BigInteger.ONE)) <= 0) {
				return getIndustrialPrimeHelper(1, minPropability, binaryLength,
						potentialPrime.add(new BigInteger("4")));
			} else {
				return getIndustrialPrimeHelper(1, minPropability, binaryLength, getRandom3Mod4(binaryLength));
			}
		}
	}

	public static BigInteger getIndustrialPrimeHelper(double probabilityNotPrime, double minPropability,
			BigInteger minValue, BigInteger maxValue, BigInteger potentialPrime) {
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
	public static Boolean testNumber(BigInteger potentialPrime) {
		BigInteger random = getRandomBetween(BigInteger.ZERO, potentialPrime);
		BigInteger result = ModArith.powerModulo(random, (potentialPrime.subtract(BigInteger.ONE)).divide(new BigInteger("2")),
				potentialPrime);
		return result.equals(BigInteger.ONE) || result.equals(potentialPrime.subtract(BigInteger.ONE));
	}

	/**
	 * Returns a random BigInteger with the specified binary length
	 * <binaryLength> which equals to 3 (mod 4)
	 * 
	 * @param binaryLength
	 * @return
	 */
	public static BigInteger getRandom3Mod4(int binaryLength) {
		BigInteger random = getRandom(binaryLength);
		if (random.mod(new BigInteger("4")).equals(new BigInteger("3"))) {
			runCount3Mod4 = 0;
			return random;
		}
		runCount3Mod4++;
		return getRandom3Mod4(binaryLength);
	}

	public static BigInteger getRandom3Mod4(BigInteger minValue, BigInteger maxValue) {
		BigInteger random = getRandomBetween(minValue, maxValue);
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
	public static BigInteger getRandom(int binaryLength) {
		// Random rnd = new Random();
		// BigInteger random = new BigInteger(binaryLength, rnd);

		BigInteger maxValue = new BigInteger("2").pow(binaryLength).subtract(BigInteger.ONE);
		BigInteger minValue = new BigInteger("2").pow(binaryLength - 1);

		return getRandomBetween(minValue, maxValue);

		// if(random.compareTo(maxValue) <= 0 && random.compareTo(minValue) >=
		// 0){
		// getRandomBetweenCount = 0;
		// return random;
		// }else{
		// getRandomBetweenCount++;
		// return getRandom(binaryLength);
		// }
	}

	/**
	 * Returns a random BigInteger between <minValue> and <maxValue>
	 * 
	 * @param minValue
	 * @param maxValue
	 */
	public static BigInteger getRandomBetween(BigInteger minValue, BigInteger maxValue) {
		BigInteger random = new BigDecimal(maxValue).multiply(new BigDecimal(Math.random())).toBigInteger();

		if (random.compareTo(maxValue) <= 0 && random.compareTo(minValue) >= 0) {
			getRandomBetweenCount = 0;
			return random;
		} else {
			getRandomBetweenCount++;
			return getRandomBetween(minValue, maxValue);
		}
	}
}
