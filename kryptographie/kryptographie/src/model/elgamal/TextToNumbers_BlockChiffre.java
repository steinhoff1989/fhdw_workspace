package model.elgamal;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TextToNumbers_BlockChiffre {

	public static class ChiffreEntry{
		int position;
		char character;
		public ChiffreEntry(final int position, final char character) {
			this.position = position;
			this.character = character;
		}
	}
	
	/**
	 * Returns the maximum k that is allowed to generate a 
	 * @param alphabetRange
	 * @param maximumBorder: the maximum Number that is allowed to display (mostly the modulo)
	 * @return
	 */
	public static int getMaximumK(final BigInteger alphabetRange, final BigInteger maximumBorder) {
		int maximumK = 0;
		while (alphabetRange.pow(maximumK + 1).compareTo(maximumBorder) <= 0) {
			maximumK++;
		}
		return maximumK;
	}
	
	public static int getMinimumL(final BigInteger alphabetRange, final BigInteger minimumBorder) {
		int minimumL = 0;
		while (alphabetRange.pow(minimumL).compareTo(minimumBorder) <= 0) {
			minimumL++;
		}
		return minimumL;
	}
	
	/**
	 * Generates a List of BigIntegers which represents the block numbers 
	 * for the given <text> 
	 * @param text: The text from which the block numbers shall be generated
	 * @param blockSize: The size of each block (not encrypted text its k, if text is encrypted its l)
	 * @param charset: The char-set that shall be used to generate the block numbers
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
					
					final Iterator<ChiffreEntry> iterator = Charset.getFHDWChiffreAlphabet().iterator();
					while(iterator.hasNext()){
						final ChiffreEntry current = iterator.next();
						if(character.equals(""+current.character)){
							characterValue = current.position;
						}
					}
					
					blockInteger = blockInteger
							.add(new BigInteger(""+characterValue).multiply(Charset.getCharsetRange(charset).pow(blockSize - 1 - y)));
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
							.add(characterValue.multiply(Charset.getCharsetRange(charset).pow(blockSize - 1 - y)));
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
}
