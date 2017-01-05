package model.elgamal;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import model.elgamal.TextToNumbers_BlockChiffre.ChiffreEntry;


public class NumbersToText_BlockChiffre {

	
	/**
	 * Generates the text from the given list of numbers
	 * @param numbers: List of block numbers that should be translated to a text
	 * @param blockSize: The size of each block (not encrypted text its k, if text is encrypted its l)
	 * @param charset: The char-set that shall be used to generate the text
	 * @return
	 */
	public static String numbersToTextBlockChiffre(final List<BigInteger> numbers, final int blockSize, final String charset) {
		String result = "";
		for (int i = 0; i < numbers.size(); i++) {
			BigInteger number = numbers.get(i);
			final BigInteger range = Charset.getCharsetRange(charset);
			String temp = "";

			if (charset.equals("FHDW-Alphabet")) {
				for (int y = 0; y < blockSize; y++) {
				final int value = Integer.parseInt(number.mod(range).toString());
				number = number.divide(range);

					final Iterator<ChiffreEntry> iterator = Charset.getFHDWChiffreAlphabet().iterator();
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
}
