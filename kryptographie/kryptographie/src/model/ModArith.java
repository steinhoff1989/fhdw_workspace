package model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class ModArith {
	
	public static BigInteger euklid(BigInteger a, BigInteger b) {
		if (a.mod(b).equals(BigInteger.ZERO))
			return b;
		else {
			return euklid(b, a.mod(b));
		}
	}
	
	public static BigInteger[] euklidExt(BigInteger a, BigInteger b) {
		BigInteger[] result = euklidExtHelper(a, b);
		if (result[1].compareTo(BigInteger.ZERO) <= 0) {
			result[1] = result[1].add(b);
		}
		if (result[2].compareTo(BigInteger.ZERO) <= 0) {
			result[2] = result[2].add(a);
		}
		return result;
	}

	public static BigInteger[] euklidExtHelper(BigInteger a, BigInteger b) {
		if (b.equals(BigInteger.ZERO)) {
			return new BigInteger[] { a, BigInteger.ONE, BigInteger.ZERO };
		} else {
			BigInteger q = a.divide(b);
			BigInteger[] result = euklidExtHelper(b, a.mod(b));
			BigInteger third = result[1].subtract(q.multiply(result[2]));

			return new BigInteger[] { result[0], result[2], third };
		}
	}
	/**
	 * Nur verwenden, wenn a und modul teilerfremd sind
	 */
	public static BigInteger modularInverse(BigInteger a, BigInteger modul) {
		BigInteger[] res = euklidExt(a, modul);
		return res[1];
	}
	
	public static BigInteger powerModulo(BigInteger basis, BigInteger exponent, BigInteger modul) {
		String exponentBinay = exponent.toString(2);

		int k = exponentBinay.length();
		List<BigInteger> results = new ArrayList<BigInteger>();

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

}
