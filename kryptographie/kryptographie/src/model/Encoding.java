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
		public ChiffreEntry(int position, char character) {
			this.position = position;
			this.character = character;
		}
	}
	
	public static List<BigInteger> encrypt(String block, int blockSize, BigInteger e, BigInteger n, String charset) {
		List<BigInteger> blockNumbers = textToNumbersBlockChiffre(block, blockSize, charset);

		System.out.println("Number of blocks: " + blockNumbers.size());

		for (int i = 0; i < blockNumbers.size(); i++) {
			System.out.println("Block " + i + ": " + blockNumbers.get(i));
		}

		List<BigInteger> chiffres = new ArrayList<BigInteger>();

		for (int i = 0; i < blockNumbers.size(); i++) {
			BigInteger chiffre = ModArith.powerModulo(blockNumbers.get(i), e, n);
			chiffres.add(chiffre);
//			System.out.println(blockNumbers.get(i) + " --> " + chiffre);
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
			BigInteger decryptedBlockNumber = ModArith.powerModulo(blockNumbers.get(i), d, n);
			decryptedBlockNumbers.add(decryptedBlockNumber);
		}

		String decryptedText = numbersToTextBlockChiffre(decryptedBlockNumbers, blockSize, charset);

		// String encrypt = numbersToText(chiffres);

		return decryptedText;
	}

	

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
