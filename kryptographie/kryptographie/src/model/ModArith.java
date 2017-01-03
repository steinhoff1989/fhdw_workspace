package model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ModArith {

	public static BigInteger euklid(final BigInteger a, final BigInteger b) {
		if (a.mod(b).equals(BigInteger.ZERO))
			return b;
		else {
			return euklid(b, a.mod(b));
		}
	}

	public static BigInteger[] euklidExt(final BigInteger a, final BigInteger b) {
		final BigInteger[] result = euklidExtHelper(a, b);
		if (result[1].compareTo(BigInteger.ZERO) <= 0) {
			result[1] = result[1].add(b);
		}
		if (result[2].compareTo(BigInteger.ZERO) <= 0) {
			result[2] = result[2].add(a);
		}
		return result;
	}

	public static BigInteger[] euklidExtHelper(final BigInteger a, final BigInteger b) {
		if (b.equals(BigInteger.ZERO)) {
			return new BigInteger[] { a, BigInteger.ONE, BigInteger.ZERO };
		} else {
			final BigInteger q = a.divide(b);
			final BigInteger[] result = euklidExtHelper(b, a.mod(b));
			final BigInteger third = result[1].subtract(q.multiply(result[2]));

			return new BigInteger[] { result[0], result[2], third };
		}
	}

	/**
	 * Nur verwenden, wenn a und modul teilerfremd sind
	 */
	public static BigInteger modularInverse(final BigInteger a, final BigInteger modul) {
		final BigInteger[] res = euklidExt(a, modul);
		return res[1];
	}

	public static BigInteger powerModulo(final BigInteger basis, final BigInteger exponent, final BigInteger modul) {
		String exponentBinay = exponent.toString(2);

		final int k = exponentBinay.length();
		final List<BigInteger> results = new ArrayList<BigInteger>();

		BigInteger result = basis;
		results.add(basis);
		for (int i = 1; i < k; i++) {
			result = result.multiply(result);
			result = result.mod(modul);
			results.add(result);
		}

		result = BigInteger.ONE;
		for (int i = k; i > 0; i--) {
			if (exponentBinay.endsWith("1")) {
				result = result.multiply(results.get(results.size() - i));
				result = result.mod(modul);
			}
			exponentBinay = exponentBinay.substring(0, exponentBinay.length() - 1);
		}
		return result;
	}

	public static ComplexNumber euclidComplex(final BigInteger a, final BigInteger b) {
		final List<ComplexNumber> g = new ArrayList<ComplexNumber>();
		int k = 1;
		try {
			g.add(0, new ComplexNumber(Fraction.create(a, BigInteger.ONE), Fraction.ZERO));
			g.add(1, new ComplexNumber(Fraction.create(b, BigInteger.ONE), Fraction.ONE));
		} catch (final FractionConstructionException e) {
			throw new Error();
		}

		while (!g.get(k).isNull()) {
			final ComplexNumber c = g.get(k - 1).divide(g.get(k));
			c.functionF();
			g.add(k+1, g.get(k-1).subtract(g.get(k).multiply(c)));
			k = k+1;
		}
		
		return g.get(k-1);
	}
	
	

}
