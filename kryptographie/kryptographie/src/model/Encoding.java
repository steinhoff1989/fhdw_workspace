package model;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Encoding {
	
	public static class ChiffreEntry{
		int position;
		char character;
		public ChiffreEntry(final int position, final char character) {
			this.position = position;
			this.character = character;
		}
	}
	
	/**
	 * Encrypts the given text with the RSA algorithm to a List of encrypted BigIntegers
	 * @param text: The text that shall be encrypted
	 * @param blockSize: the length of each block (k)
	 * @param e
	 * @param n
	 * @param charset
	 * @return
	 */
	public static List<BigInteger> encrypt(final String text, final int blockSize, final BigInteger e, final BigInteger n, final String charset) {
		final List<BigInteger> blockNumbers = textToNumbersBlockChiffre(text, blockSize, charset);

		System.out.println("Number of blocks: " + blockNumbers.size());

		for (int i = 0; i < blockNumbers.size(); i++) {
			System.out.println("Block " + i + ": " + blockNumbers.get(i));
		}

		final List<BigInteger> chiffres = new ArrayList<BigInteger>();

		for (int i = 0; i < blockNumbers.size(); i++) {
			final BigInteger chiffre = ModArith.powerModulo(blockNumbers.get(i), e, n);
			chiffres.add(chiffre);
		}
		return chiffres;
	}

	public static String decrypt(final List<BigInteger> blockNumbers, final int blockSize, final BigInteger d, final BigInteger n,
			final String charset) {
		final List<BigInteger> decryptedBlockNumbers = new ArrayList<BigInteger>();

		for (int i = 0; i < blockNumbers.size(); i++) {
			final BigInteger decryptedBlockNumber = ModArith.powerModulo(blockNumbers.get(i), d, n);
			decryptedBlockNumbers.add(decryptedBlockNumber);
		}

		final String decryptedText = numbersToTextBlockChiffre(decryptedBlockNumbers, blockSize, charset);

		return decryptedText;
	}

	

	public static String numbersToText(final List<BigInteger> numbers) {
		String result = "";

		for (int i = 0; i < numbers.size(); i++) {
			final BigInteger number = numbers.get(i);

			byte[] textBytes = number.toByteArray();

			if (textBytes[0] == 0) {
				textBytes = Arrays.copyOfRange(textBytes, 1, textBytes.length);
			}
			// result += new String(textBytes, "UTF-8");

			// byte[] b = new byte[4];
			// b[3] = textBytes[0];

			try {
				result += new String(textBytes, "UTF-8");
			} catch (final UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			// result += number.toString(16) + ",";
		}

		if (result.endsWith(" ")) {
			result = result.trim();
		}

		return result;
	}

	public static String numbersToTextBlockChiffre(final List<BigInteger> numbers, final int blockSize, final String charset) {
		String result = "";
		for (int i = 0; i < numbers.size(); i++) {
			BigInteger number = numbers.get(i);
			final BigInteger range = getCharsetRange(charset);
			String temp = "";

			if (charset.equals("FHDW-Alphabet")) {
				for (int y = 0; y < blockSize; y++) {
				final int value = Integer.parseInt(number.mod(range).toString());
				number = number.divide(range);

					final Iterator<ChiffreEntry> iterator = getFHDWChiffreAlphabet().iterator();
					while(iterator.hasNext()){
						final ChiffreEntry current = iterator.next();
						if(value == current.position){
							temp = current.character+temp;
						}
					}
				}
				result = result + temp;
				

			} else {

				for (int y = 0; y < blockSize; y++) {
					final BigInteger value = number.mod(range);

					number = number.divide(range);

					byte[] bytes = value.toByteArray();
					if (bytes[0] == 0 && bytes.length > 1) {
						bytes = Arrays.copyOfRange(bytes, 1, bytes.length);
					}
					try {
						temp = new String(bytes, charset) + temp;
					} catch (final UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}

				result = result + temp;
			}
		}
		return result;
	}

	public static List<BigInteger> textToNumbers(final String text, final int blockSize) {
		final List<BigInteger> blockNumbers = new ArrayList<BigInteger>();
		final List<String> textBlocks = getTextBlocks(text, blockSize);

		for (int i = 0; i < textBlocks.size(); i++) {
			byte[] blockBytes = new byte[] {};
			try {
				blockBytes = textBlocks.get(i).getBytes("UTF-8");
			} catch (final UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			final BigInteger value = new BigInteger(1, blockBytes);
			blockNumbers.add(value);
		}

		return blockNumbers;
	}

	/**
	 * Generates a List of BigIntegers which represents the Blocknumbers 
	 * for the given <text> 
	 * @param text: The text from which the block numbers shall be generated
	 * @param blockSize: The size of each block
	 * @param charset: The charset that shall be used to generate the block numnbers
	 * @return
	 */
	public static List<BigInteger> textToNumbersBlockChiffre(final String text, final int blockSize, final String charset) {
		final List<BigInteger> blockNumbers = new ArrayList<BigInteger>();
		final List<String> textBlocks = getTextBlocks(text, blockSize);

		if (charset.equals("FHDW-Alphabet")) {
			for (int i = 0; i < textBlocks.size(); i++) {
				BigInteger blockInteger = BigInteger.ZERO;

				for (int y = 0; y < textBlocks.get(i).length(); y++) {
					final String character = textBlocks.get(i).substring(y, y + 1);
					
					int characterValue = 0;
					
					final Iterator<ChiffreEntry> iterator = getFHDWChiffreAlphabet().iterator();
					while(iterator.hasNext()){
						final ChiffreEntry current = iterator.next();
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
					final String character = textBlocks.get(i).substring(y, y + 1);
					byte[] characterBytes = new byte[] {};
					try {
						characterBytes = character.getBytes(charset);
					} catch (final UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					final BigInteger characterValue = new BigInteger(1, characterBytes);

					blockInteger = blockInteger
							.add(characterValue.multiply(getCharsetRange(charset).pow(blockSize - 1 - y)));
				}

				blockNumbers.add(blockInteger);
			}
		}

		return blockNumbers;
	}

	private static List<String> getTextBlocks(final String text, final int blockSize) {
		final List<String> textBlocks = new ArrayList<String>();

		for (int i = 0; i < text.length(); i += blockSize) {
			final String newBlock = text.substring(i, i + Math.min(blockSize, text.length() - i));
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

	public static BigInteger textToNumberUnicode(final String text) {
		try {
			final byte[] bytes = text.getBytes("UTF-8");
			return new BigInteger(1, bytes);
		} catch (final UnsupportedEncodingException e) {
			throw new Error();
		}
	}

	public static String numberToTextUnicode(final BigInteger integerValue) {
		try {
			final byte[] bytes = integerValue.toByteArray();
			return new String(bytes, "UTF-8");
		} catch (final UnsupportedEncodingException e) {
			throw new Error();
		}
	}

	/**
	 * Returns the maximum k that is allowed to generate a 
	 * @param alphabetRange
	 * @param maximumBorder: the maximum Number that is allowed to display (mostly the modulo)
	 * @return
	 */
	public static int getMaximumK(final BigInteger alphabetRange, final String maximumBorder) {
		// BigInteger range = getCharsetRange(charset);
		int maximumK = 0;

		while (alphabetRange.pow(maximumK + 1).compareTo(new BigInteger(maximumBorder)) <= 0) {
			maximumK++;
		}

		return maximumK;
	}
	
	public static int getMaximumK(final BigInteger alphabetRange, final BigInteger maximumBorder) {
		int maximumK = 0;
		while (alphabetRange.pow(maximumK + 1).compareTo(maximumBorder) <= 0) {
			maximumK++;
		}
		return maximumK;
	}

	public static int getMinimumL(final BigInteger alphabetRange, final String minimumBorder) {
		// BigInteger range = getCharsetRange(charset);
		int minimumL = 0;

		while (alphabetRange.pow(minimumL).compareTo(new BigInteger(minimumBorder)) <= 0) {
			minimumL++;
		}

		return minimumL;
	}

	public static BigInteger getCharsetRange(final String charsetName) {
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
		final List<String> availableCharsets = new ArrayList<String>();
		availableCharsets.add("FHDW-Alphabet");
		availableCharsets.add("windows-1250");
		availableCharsets.add("UTF-8");
		availableCharsets.add("US-ASCII");
		availableCharsets.add("IBM437");
		availableCharsets.add("ISO-8859-15");
		return availableCharsets;
	}

	public static List<ChiffreEntry> getFHDWChiffreAlphabet() {
		final LinkedList<ChiffreEntry> alphabet = new LinkedList<ChiffreEntry>();
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

		alphabet.add(new ChiffreEntry(i++, 'ß'));
		alphabet.add(new ChiffreEntry(i++, 'ä'));
		alphabet.add(new ChiffreEntry(i++, 'ö'));
		alphabet.add(new ChiffreEntry(i++, 'ü'));
		alphabet.add(new ChiffreEntry(i++, 'Ä'));
		alphabet.add(new ChiffreEntry(i++, 'Ö'));
		alphabet.add(new ChiffreEntry(i++, 'Ü'));
		alphabet.add(new ChiffreEntry(i++, '!'));
		alphabet.add(new ChiffreEntry(i++, '"'));
		alphabet.add(new ChiffreEntry(i++, '§'));
		alphabet.add(new ChiffreEntry(i++, '$'));
		alphabet.add(new ChiffreEntry(i++, '%'));
		alphabet.add(new ChiffreEntry(i++, '&'));
		alphabet.add(new ChiffreEntry(i++, '/'));
		alphabet.add(new ChiffreEntry(i++, '('));
		alphabet.add(new ChiffreEntry(i++, ')'));
		alphabet.add(new ChiffreEntry(i++, '='));
		alphabet.add(new ChiffreEntry(i++, '?'));
		alphabet.add(new ChiffreEntry(i++, '`'));
		alphabet.add(new ChiffreEntry(i++, '{'));
		alphabet.add(new ChiffreEntry(i++, '['));
		alphabet.add(new ChiffreEntry(i++, ']'));
		alphabet.add(new ChiffreEntry(i++, '}'));
		alphabet.add(new ChiffreEntry(i++, '\\'));
		alphabet.add(new ChiffreEntry(i++, '@'));
		alphabet.add(new ChiffreEntry(i++, '€'));
		alphabet.add(new ChiffreEntry(i++, '+'));
		alphabet.add(new ChiffreEntry(i++, '#'));
		alphabet.add(new ChiffreEntry(i++, ','));
		alphabet.add(new ChiffreEntry(i++, '.'));
		alphabet.add(new ChiffreEntry(i++, '-'));
		alphabet.add(new ChiffreEntry(i++, '*'));
		alphabet.add(new ChiffreEntry(i++, '\''));
		alphabet.add(new ChiffreEntry(i++, ';'));
		alphabet.add(new ChiffreEntry(i++, ':'));
		alphabet.add(new ChiffreEntry(i++, '_'));
		alphabet.add(new ChiffreEntry(i++, '<'));
		alphabet.add(new ChiffreEntry(i++, '>'));
		alphabet.add(new ChiffreEntry(i++, '|'));
		alphabet.add(new ChiffreEntry(i++, '^'));
		alphabet.add(new ChiffreEntry(i++, '°'));
		alphabet.add(new ChiffreEntry(i++, '~'));
		alphabet.add(new ChiffreEntry(i++, ' '));
		alphabet.add(new ChiffreEntry(i++, '\n'));
		return alphabet;
	}
}
