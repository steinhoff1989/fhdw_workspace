package model;

import java.io.IOException;

import org.junit.Test;

import model.elgamal.CipherList;
import model.elgamal.ElGamal;
import model.elgamal.ElGamalDecrypt;
import model.elgamal.ElGamalEncrypt;
import model.elgamal.ElGamal_privateKey;
import model.elgamal.ElGamal_publicKey;

public class ElGamalEncryptTest {

	@Test
	public void ElGamalEncryptTest1() throws IOException {
		final String PATH_TO_FILE = "C:\\Steini\\trash\\chiffrat.txt";
		final String textToEncrypt = "Das ist ein Test";
		
		System.out.println("Text to encrypt: "+textToEncrypt);
		
		final ElGamal elGamal = new ElGamal(256, 0.99);

		final ElGamalEncrypt elGamalEncrypt = new ElGamalEncrypt(textToEncrypt, elGamal.getPublicKey());
		final CipherList chiffrats = elGamalEncrypt.encrypt();
		for (int i = 0; i < chiffrats.getCiphers().size(); i++) {
			chiffrats.getCiphers().get(i).saveToFile(PATH_TO_FILE);
			System.out.println("Chiffratblock "+i+": "+chiffrats.getCiphers().get(i));
		}
		
		final ElGamalDecrypt elGamalDecrypt = new ElGamalDecrypt(elGamal.getPrivateKey(), elGamal.getPublicKey(), chiffrats);
		System.out.println("Decrypted chiffrat blocks: "+elGamalDecrypt.decrypt());
	}
	
	@Test
	public void ElGamalEncryptTest2() throws IOException {
		final String textToBeEncrypted = "Falsches Üben von Xylophonmusik quält jeden größeren Zwerg.";
		final String PATH_TO_PRIVATE_KEY = "C:\\Steini\\trash\\private_key.txt";
		final String PATH_TO_PUBLIC_KEY = "C:\\Steini\\trash\\public_key.txt";
		final String PATH_TO_CIPHER_LIST = "C:\\Steini\\trash\\cipherList.txt";

		final ElGamal elGamal = new ElGamal(128, 0.99);
		elGamal.getPrivateKey().saveToFile(PATH_TO_PRIVATE_KEY, true);
		elGamal.getPublicKey().saveToFile(PATH_TO_PUBLIC_KEY, true);

		final ElGamalEncrypt encrypt = new ElGamalEncrypt(textToBeEncrypted, new ElGamal_publicKey(PATH_TO_PUBLIC_KEY));
		encrypt.encrypt().saveToFile(PATH_TO_CIPHER_LIST, true);

		final ElGamalDecrypt decrypt = new ElGamalDecrypt(new ElGamal_privateKey(PATH_TO_PRIVATE_KEY),
				new ElGamal_publicKey(PATH_TO_PUBLIC_KEY), new CipherList(PATH_TO_CIPHER_LIST));
		System.out.println(decrypt.decrypt());
	}
}
