package model;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Functions {

	public static int getRandomBetweenCount = 0;
	public static int runCount3Mod4 = 0;
	private static int runCountIndustryalPrime = 0;

	public static void setRunCountIndustryalPrime(int runCountIndustryalPrime) {
		Functions.runCountIndustryalPrime = runCountIndustryalPrime;
	}

	public static int getRunCountIndustryalPrime() {
		return runCountIndustryalPrime;
	}

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

			// if(third.compareTo(BigInteger.ZERO) <= 0){
			// third = third.add(a);
			// }

			return new BigInteger[] { result[0], result[2], third };
		}
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
		BigInteger d = euklidExt(e, phiN)[1];

		List<BigInteger> result = new ArrayList<BigInteger>();

		result.add(p);
		result.add(q);
		result.add(n);
		result.add(e);
		result.add(d);
		result.add(phiN);

		return result;
	}

	public static List<BigInteger> encrypt(String block, int blockSize, BigInteger e, BigInteger n, String charset) {
		List<BigInteger> blockNumbers = textToNumbersBlockChiffre(block, blockSize, charset);

		System.out.println("Number of blocks: " + blockNumbers.size());

		for (int i = 0; i < blockNumbers.size(); i++) {
			System.out.println("Block " + i + ": " + blockNumbers.get(i));
		}

		List<BigInteger> chiffres = new ArrayList<BigInteger>();

		for (int i = 0; i < blockNumbers.size(); i++) {
			BigInteger chiffre = powerModulo(blockNumbers.get(i), e, n);
			chiffres.add(chiffre);
			System.out.println(blockNumbers.get(i) + " --> " + chiffre);
		}

		// String encrypt = numbersToText(chiffres);

		return chiffres;
	}

	public static String decrypt(List<BigInteger> blockNumbers, int blockSize, BigInteger d, BigInteger n,
			String charset) {
		// List<BigInteger> blockNumbers = textToNumbers(block, blockSize);

		// System.out.println("Number of blocks: " + blockNumbers.size());

		// for(int i=0;i<blockNumbers.size();i++){
		// System.out.println("Block "+i+": "+blockNumbers.get(i));
		// }

		List<BigInteger> decryptedBlockNumbers = new ArrayList<BigInteger>();

		for (int i = 0; i < blockNumbers.size(); i++) {
			BigInteger decryptedBlockNumber = powerModulo(blockNumbers.get(i), d, n);
			decryptedBlockNumbers.add(decryptedBlockNumber);
		}

		String decryptedText = numbersToTextBlockChiffre(decryptedBlockNumbers, blockSize, charset);

		// String encrypt = numbersToText(chiffres);

		return decryptedText;
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
		BigInteger result = powerModulo(random, (potentialPrime.subtract(BigInteger.ONE)).divide(new BigInteger("2")),
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

	// private List<String> getBlocksList(String text, int blockSize)
	// {
	// return getTextBlocks(text, blockSize);
	// }
	//
	// private BigInteger calculateBlock2Number(String block, int blockSize)
	// {
	// BigInteger result = BigInteger.ZERO;
	//
	// for (int i = blockSize-1; i>=0; i--)
	// {
	// BigInteger charNumber = new
	// BigInteger(String.valueOf(getCharNumber(block.charAt(blockSize - 1 -
	// i))));
	// BigInteger charachtersCount = new
	// BigInteger(String.valueOf(getCharaterSetCount()));
	//
	// result = result.add(charNumber).multiply(charachtersCount.pow(i));
	// }
	//
	// return result;
	// }

	// private int getCharNumber(char charToConvert){
	// return Character.getNumericValue(charToConvert);
	// }
	//
	// private int getCharaterSetCount(){
	// return Character.SIZE;
	// }

	// private byte[] getUTF8Byte(String input) throws
	// UnsupportedEncodingException{
	// return input.getBytes("UTF-8");
	// }

	public static String numbersToText(List<BigInteger> numbers) {
		String result = "";

		for (int i = 0; i < numbers.size(); i++) {
			BigInteger number = numbers.get(i);

			byte[] textBytes = number.toByteArray();

			if (textBytes[0] == 0) {
				textBytes = Arrays.copyOfRange(textBytes, 1, textBytes.length);
			}
			// result += new String(textBytes, "UTF-8");

			// byte[] b = new byte[4];
			// b[3] = textBytes[0];

			try {
				result += new String(textBytes, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// result += number.toString(16) + ",";
		}

		if (result.endsWith(" ")) {
			result = result.trim();
		}

		return result;
	}

	public static String numbersToTextBlockChiffre(List<BigInteger> numbers, int blockSize, String charset) {
		String result = "";
		for (int i = 0; i < numbers.size(); i++) {
			BigInteger number = numbers.get(i);
			BigInteger range = getCharsetRange(charset);
			String temp = "";

			if (charset.equals("FHDW-Alphabet")) {
				for (int y = 0; y < blockSize; y++) {
				int value = Integer.parseInt(number.mod(range).toString());
				number = number.divide(range);

					Iterator<ChiffreEntry> iterator = getFHDWChiffreAlphabet().iterator();
					while(iterator.hasNext()){
						ChiffreEntry current = iterator.next();
						if(value == current.position){
							temp = current.character+temp;
						}
					}
				}
				result = result + temp;
				

			} else {

				for (int y = 0; y < blockSize; y++) {
					BigInteger value = number.mod(range);

					number = number.divide(range);

					byte[] bytes = value.toByteArray();
					if (bytes[0] == 0 && bytes.length > 1) {
						bytes = Arrays.copyOfRange(bytes, 1, bytes.length);
					}
					try {
						temp = new String(bytes, charset) + temp;
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

				result = result + temp;
			}
		}
		return result;
	}

	public static List<BigInteger> textToNumbers(String text, int blockSize) {
		List<BigInteger> blockNumbers = new ArrayList<BigInteger>();
		List<String> textBlocks = getTextBlocks(text, blockSize);

		for (int i = 0; i < textBlocks.size(); i++) {
			byte[] blockBytes = new byte[] {};
			try {
				blockBytes = textBlocks.get(i).getBytes("UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BigInteger value = new BigInteger(1, blockBytes);
			blockNumbers.add(value);
		}

		return blockNumbers;
	}

	public static List<BigInteger> textToNumbersBlockChiffre(String text, int blockSize, String charset) {
		List<BigInteger> blockNumbers = new ArrayList<BigInteger>();
		List<String> textBlocks = getTextBlocks(text, blockSize);

		if (charset.equals("FHDW-Alphabet")) {
			for (int i = 0; i < textBlocks.size(); i++) {
				BigInteger blockInteger = BigInteger.ZERO;

				for (int y = 0; y < textBlocks.get(i).length(); y++) {
					String character = textBlocks.get(i).substring(y, y + 1);
					
					int characterValue = 0;
					
					Iterator<ChiffreEntry> iterator = getFHDWChiffreAlphabet().iterator();
					while(iterator.hasNext()){
						ChiffreEntry current = iterator.next();
						if(character.equals(""+current.character)){
							characterValue = current.position;
						}
					}
					
					blockInteger = blockInteger
							.add(new BigInteger(""+characterValue).multiply(getCharsetRange(charset).pow(blockSize - 1 - y)));
				}
				
				blockNumbers.add(blockInteger);
			}
		} else {
			for (int i = 0; i < textBlocks.size(); i++) {
				BigInteger blockInteger = BigInteger.ZERO;

				for (int y = 0; y < textBlocks.get(i).length(); y++) {
					String character = textBlocks.get(i).substring(y, y + 1);
					byte[] characterBytes = new byte[] {};
					try {
						characterBytes = character.getBytes(charset);
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					BigInteger characterValue = new BigInteger(1, characterBytes);

					blockInteger = blockInteger
							.add(characterValue.multiply(getCharsetRange(charset).pow(blockSize - 1 - y)));
				}

				blockNumbers.add(blockInteger);
			}
		}

		return blockNumbers;
	}

	private static List<String> getTextBlocks(String text, int blockSize) {
		List<String> textBlocks = new ArrayList<String>();

		for (int i = 0; i < text.length(); i += blockSize) {
			String newBlock = text.substring(i, i + Math.min(blockSize, text.length() - i));
			textBlocks.add(newBlock);
		}

		if (textBlocks.get(textBlocks.size() - 1).length() < blockSize) {
			String last = textBlocks.get(textBlocks.size() - 1);
			while (last.length() < blockSize) {
				last = last + " ";
			}
			textBlocks.remove(textBlocks.size() - 1);
			textBlocks.add(last);
		}

		return textBlocks;
	}

	public static BigInteger textToNumberUnicode(String text) {
		try {
			byte[] bytes = text.getBytes("UTF-8");
			Map<String, Charset> charsets = Charset.availableCharsets();
			return new BigInteger(1, bytes);
		} catch (UnsupportedEncodingException e) {
			throw new Error();
		}
	}

	public static String numberToTextUnicode(BigInteger integerValue) {
		try {
			byte[] bytes = integerValue.toByteArray();
			return new String(bytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new Error();
		}
	}

	public static int getMaximumK(BigInteger alphabetRange, String maximumBorder) {
		// BigInteger range = getCharsetRange(charset);
		int maximumK = 0;

		while (alphabetRange.pow(maximumK + 1).compareTo(new BigInteger(maximumBorder)) <= 0) {
			maximumK++;
		}

		return maximumK;
	}

	public static int getMinimumL(BigInteger alphabetRange, String minimumBorder) {
		// BigInteger range = getCharsetRange(charset);
		int minimumL = 0;

		while (alphabetRange.pow(minimumL).compareTo(new BigInteger(minimumBorder)) <= 0) {
			minimumL++;
		}

		return minimumL;
	}

	public static BigInteger getCharsetRange(String charsetName) {
		switch (charsetName) {
		case "UTF-8":
			return new BigInteger("2").pow(32);
		case "US-ASCII":
			return new BigInteger("2").pow(7);
		case "windows-1250":
			return new BigInteger("2").pow(8);
		case "IBM437":
			return new BigInteger("2").pow(8);
		case "ISO-8859-15":
			return new BigInteger("2").pow(8);
		case "FHDW-Alphabet":
			return new BigInteger("" + getFHDWChiffreAlphabet().size());
		default:
			return new BigInteger("2").pow(7);
		}

	}

	public static List<String> getAvailableCharsetNames() {
		List<String> availableCharsets = new ArrayList<String>();
		availableCharsets.add("FHDW-Alphabet");
		availableCharsets.add("windows-1250");
		availableCharsets.add("UTF-8");
		availableCharsets.add("US-ASCII");
		availableCharsets.add("IBM437");
		availableCharsets.add("ISO-8859-15");
		return availableCharsets;
	}

	@SuppressWarnings("serial")
	public static List<ChiffreEntry> getFHDWChiffreAlphabet() {
		LinkedList<ChiffreEntry> alphabet = new LinkedList<ChiffreEntry>();
		int i = 0;
		for (int j = 0; j < 26; j++) {
			alphabet.add(new ChiffreEntry(i, Character.valueOf((char) ('A' + j))));
			i++;
		}

		for (int j = 0; j < 26; j++) {
			alphabet.add(new ChiffreEntry(i, Character.valueOf((char) ('a' + j))));
			i++;
		}

		for (int j = 0; j < 10; j++) {
			alphabet.add(new ChiffreEntry(i, Character.valueOf((char) ('0' + j))));
			i++;
		}

		alphabet.add(new ChiffreEntry(i++, '.'));
		alphabet.add(new ChiffreEntry(i++, ','));
		alphabet.add(new ChiffreEntry(i++, ':'));
		alphabet.add(new ChiffreEntry(i++, ';'));
		alphabet.add(new ChiffreEntry(i++, '-'));
		alphabet.add(new ChiffreEntry(i++, '!'));
		alphabet.add(new ChiffreEntry(i++, '?'));
		alphabet.add(new ChiffreEntry(i++, '"'));
		alphabet.add(new ChiffreEntry(i++, '('));
		alphabet.add(new ChiffreEntry(i++, ')'));
		alphabet.add(new ChiffreEntry(i++, '*'));
		alphabet.add(new ChiffreEntry(i++, ' '));
		alphabet.add(new ChiffreEntry(i++, '\n'));
		alphabet.add(new ChiffreEntry(i++, '€'));
		alphabet.add(new ChiffreEntry(i++, '@'));
		return alphabet;
	}

}
