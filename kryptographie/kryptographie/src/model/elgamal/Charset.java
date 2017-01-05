package model.elgamal;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.elgamal.TextToNumbers_BlockChiffre.ChiffreEntry;


public class Charset {

	public static final String UTF_8 = "UTF-8";
	public static final String US_ASCII = "US-ASCII";
	public static final String WINDOWS_1250 = "windows-1250";
	public static final String IBM437 = "IBM437";
	public static final String ISO_8859_15 = "ISO-8859-15";
	public static final String FHDW_Alphabet = "FHDW-Alphabet";
	
	public Charset(){}
	
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
