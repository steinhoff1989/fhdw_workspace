package model;

import java.io.IOException;

import org.junit.Test;

import model.elgamal.CipherList;
import model.elgamal.ElGamal;
import model.elgamal.ElGamalDecrypt;
import model.elgamal.ElGamalEncrypt;

public class ChiffratTest {

	@Test
	public void ChiffratTest1() throws IOException {

		final String textToEncrypt = "Test";
		final String PATH_TO_FILE = "C:\\Steini\\trash\\chiffrat.txt";
		
		System.out.println("Text to encrypt: " + textToEncrypt);

		final ElGamal elGamal = new ElGamal(16, 0.99);

		final ElGamalEncrypt elGamalEncrypt = new ElGamalEncrypt(textToEncrypt,
				elGamal.getPublicKey());
		final CipherList chiffrats = elGamalEncrypt.encrypt();
		System.out.println("Encrypted chiffrat list:");
		System.out.println(chiffrats.toString());

		chiffrats.saveToFile(PATH_TO_FILE, true);
		System.out.println("Successfully saved to file.");
		
		final CipherList chiffratListFromFile = new CipherList(PATH_TO_FILE);
		System.out.println();
		System.out.println("Successfully read from file.");
		System.out.println("Readed chiffrat list:");
		System.out.println(chiffratListFromFile.toString());
	}
	
	@Test
	public void ChiffratTest2() throws IOException {

		final String textToEncrypt = "The quick brown fox jumps over the lazy dog";
		final String PATH_TO_FILE = "C:\\Steini\\trash\\chiffrat.txt";
		
		System.out.println("Text to encrypt: " + textToEncrypt);

		final ElGamal elGamal = new ElGamal(16, 0.99);

		final ElGamalEncrypt elGamalEncrypt = new ElGamalEncrypt(textToEncrypt,
				elGamal.getPublicKey());
		final CipherList chiffrats = elGamalEncrypt.encrypt();
		System.out.println("Encrypted chiffrat list:");
		System.out.println(chiffrats.toString());

		chiffrats.saveToFile(PATH_TO_FILE, true);
		System.out.println("Successfully saved to file.");
		
		final CipherList chiffratListFromFile = new CipherList(PATH_TO_FILE);
		System.out.println();
		System.out.println("Successfully read from file.");
		System.out.println("Readed chiffrat list:");
		System.out.println(chiffratListFromFile.toString());
		
		final ElGamalDecrypt decrypt = new ElGamalDecrypt(elGamal.getPrivateKey(), elGamal.getPublicKey(), chiffratListFromFile);
		System.out.println(decrypt.decrypt());
	}

}
