package model;


import java.math.BigInteger;
import java.util.List;

import org.junit.Test;

import model.elgamal.Charset;
import model.elgamal.ElGamal;
import model.elgamal.NumbersToText_BlockChiffre;
import model.elgamal.TextToNumbers_BlockChiffre;

public class TextToNumbers_BlockChiffreTest {

	@Test
	public void TextToNumbers_BlockChiffreTest1() {
		final ElGamal elGamal = new ElGamal(8, 0.99);
		
		final BigInteger prime = elGamal.getPublicKey().getP();
		
		final int maximumK =  TextToNumbers_BlockChiffre.getMaximumK(Charset.getCharsetRange(Charset.FHDW_Alphabet), prime);
		
		final List<BigInteger> numbers = TextToNumbers_BlockChiffre.textToNumbersBlockChiffre("Das ist ein Test", maximumK , Charset.FHDW_Alphabet);
		
		for(int i=0;i<numbers.size();i++){
			System.out.println(numbers.get(i));
		}
		
		final String text = NumbersToText_BlockChiffre.numbersToTextBlockChiffre(numbers, maximumK, Charset.FHDW_Alphabet);
		System.out.println(text);
	}
	@Test
	public void TextToNumbers_BlockChiffreTest2() {
		final ElGamal elGamal = new ElGamal(15, 0.99);
		
		final BigInteger prime = elGamal.getPublicKey().getP();
		
		final int maximumK =  TextToNumbers_BlockChiffre.getMaximumK(Charset.getCharsetRange(Charset.FHDW_Alphabet), prime);
		
		final List<BigInteger> numbers = TextToNumbers_BlockChiffre.textToNumbersBlockChiffre("Das ist ein Test", maximumK , Charset.FHDW_Alphabet);
		
		for(int i=0;i<numbers.size();i++){
			System.out.println(numbers.get(i));
		}
		
		final String text = NumbersToText_BlockChiffre.numbersToTextBlockChiffre(numbers, maximumK, Charset.FHDW_Alphabet);
		System.out.println(text);
	}
	
	@Test
	public void TextToNumbers_BlockChiffreTest3() {
		final ElGamal elGamal = new ElGamal(50, 0.99);
		
		final BigInteger prime = elGamal.getPublicKey().getP();
		
		final int maximumK =  TextToNumbers_BlockChiffre.getMaximumK(Charset.getCharsetRange(Charset.FHDW_Alphabet), prime);
		
		final List<BigInteger> numbers = TextToNumbers_BlockChiffre.textToNumbersBlockChiffre("Das ist ein Test", maximumK , Charset.FHDW_Alphabet);
		
		for(int i=0;i<numbers.size();i++){
			System.out.println(numbers.get(i));
		}
		
		final String text = NumbersToText_BlockChiffre.numbersToTextBlockChiffre(numbers, maximumK, Charset.FHDW_Alphabet);
		System.out.println(text);
	}
	
	@Test
	public void TextToNumbers_BlockChiffreTest4() {
		final ElGamal elGamal = new ElGamal(128, 0.99);
		
		final BigInteger prime = elGamal.getPublicKey().getP();
		
		final int maximumK =  TextToNumbers_BlockChiffre.getMaximumK(Charset.getCharsetRange(Charset.FHDW_Alphabet), prime);
		
		final List<BigInteger> numbers = TextToNumbers_BlockChiffre.textToNumbersBlockChiffre("Das ist ein Test", maximumK , Charset.FHDW_Alphabet);
		
		for(int i=0;i<numbers.size();i++){
			System.out.println(numbers.get(i));
		}
		
		final String text = NumbersToText_BlockChiffre.numbersToTextBlockChiffre(numbers, maximumK, Charset.FHDW_Alphabet);
		System.out.println(text);
	}
	
	@Test
	public void TextToNumbers_BlockChiffreTest5() {
		final ElGamal elGamal = new ElGamal(256, 0.99);
		
		final BigInteger prime = elGamal.getPublicKey().getP();
		
		final int maximumK =  TextToNumbers_BlockChiffre.getMaximumK(Charset.getCharsetRange(Charset.FHDW_Alphabet), prime);
		
		final List<BigInteger> numbers = TextToNumbers_BlockChiffre.textToNumbersBlockChiffre("Das ist ein Test", maximumK , Charset.FHDW_Alphabet);
		
		for(int i=0;i<numbers.size();i++){
			System.out.println(numbers.get(i));
		}
		
		final String text = NumbersToText_BlockChiffre.numbersToTextBlockChiffre(numbers, maximumK, Charset.FHDW_Alphabet);
		System.out.println(text);
	}
}
