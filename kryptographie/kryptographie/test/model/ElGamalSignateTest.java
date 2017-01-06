package model;

import java.io.IOException;

import org.junit.Test;

import model.elgamal.CipherList;
import model.elgamal.ELGamalSignate;
import model.elgamal.ElGamal;
import model.elgamal.ElGamalDecrypt;
import model.elgamal.ElGamalEncrypt;
import model.elgamal.ElGamalVerificate;
import model.elgamal.Signature;

public class ElGamalSignateTest {

	@Test
	public void ElGamalSignateTest1() throws IOException {
		final String PATH_TO_CIPHER_LIST = "C:\\Steini\\trash\\cipherList.txt";
		final ElGamal elGamal = new ElGamal(12, 0.99);

		final ElGamalEncrypt elGamalEncrypt = new ElGamalEncrypt("Das ist ein Test", elGamal.getPublicKey());
		final CipherList chiffrats = elGamalEncrypt.encrypt();
		
		System.out.println(chiffrats.toString());
		
		final ELGamalSignate signate = new ELGamalSignate(elGamal.getPublicKey(), elGamal.getPrivateKey());
		final Signature signature = signate.signateAFile(PATH_TO_CIPHER_LIST);
		
		System.out.println("Signature: "+signature);
		
		final ElGamalDecrypt elGamalDecrypt = new ElGamalDecrypt(elGamal.getPrivateKey(), elGamal.getPublicKey(), chiffrats);
		System.out.println("Entschlüsselte Text: "+elGamalDecrypt.decrypt());
		
		final ElGamalVerificate verificate = new ElGamalVerificate(elGamal.getPublicKey(), signature);
		System.out.println("Signatur » u = r (mod q) :" + verificate.verificateCipherList(PATH_TO_CIPHER_LIST));
	}
	
	@Test
	public void ElGamalSignateTest2() throws IOException {
		final String PATH_TO_CIPHER_LIST = "C:\\Steini\\trash\\cipherList.txt";
		final ElGamal elGamal = new ElGamal(64, 0.99);

		final ElGamalEncrypt elGamalEncrypt = new ElGamalEncrypt("Das ist ein Test", elGamal.getPublicKey());
		final CipherList chiffrats = elGamalEncrypt.encrypt();
		
		System.out.println(chiffrats.toString());
		
		final ELGamalSignate signate = new ELGamalSignate(elGamal.getPublicKey(), elGamal.getPrivateKey());
		final Signature signature = signate.signateAFile(PATH_TO_CIPHER_LIST);
		
		System.out.println("Signature: "+signature);
		
		final ElGamalDecrypt elGamalDecrypt = new ElGamalDecrypt(elGamal.getPrivateKey(), elGamal.getPublicKey(), chiffrats);
		System.out.println("Entschlüsselte Text: "+elGamalDecrypt.decrypt());
		
		final ElGamalVerificate verificate = new ElGamalVerificate(elGamal.getPublicKey(), signature);
		System.out.println("Signatur » u = r (mod q) :" + verificate.verificateCipherList(PATH_TO_CIPHER_LIST));
	}

	
	@Test
	public void ElGamalSignateTest3() throws IOException {
		final String PATH_TO_CIPHER_LIST = "C:\\Steini\\trash\\cipherList.txt";
		final ElGamal elGamal = new ElGamal(128, 0.99);

		final ElGamalEncrypt elGamalEncrypt = new ElGamalEncrypt("Das ist ein Test", elGamal.getPublicKey());
		final CipherList chiffrats = elGamalEncrypt.encrypt();
		
		System.out.println(chiffrats.toString());
		
		final ELGamalSignate signate = new ELGamalSignate(elGamal.getPublicKey(), elGamal.getPrivateKey());
		final Signature signature = signate.signateAFile(PATH_TO_CIPHER_LIST);
		
		System.out.println("Signature: "+signature);
		
		final ElGamalDecrypt elGamalDecrypt = new ElGamalDecrypt(elGamal.getPrivateKey(), elGamal.getPublicKey(), chiffrats);
		System.out.println("Entschlüsselte Text: "+elGamalDecrypt.decrypt());
		
		final ElGamalVerificate verificate = new ElGamalVerificate(elGamal.getPublicKey(), signature);
		System.out.println("Signatur » u = r (mod q) :" + verificate.verificateCipherList(PATH_TO_CIPHER_LIST));
	}
	
	@Test
	public void ElGamalSignateTest4() throws IOException {
		final String PATH_TO_CIPHER_LIST = "C:\\Steini\\trash\\cipherList.txt";
		final ElGamal elGamal = new ElGamal(256, 0.99);

		final ElGamalEncrypt elGamalEncrypt = new ElGamalEncrypt("Das ist ein Test", elGamal.getPublicKey());
		final CipherList chiffrats = elGamalEncrypt.encrypt();
		
		System.out.println(chiffrats.toString());
		
		final ELGamalSignate signate = new ELGamalSignate(elGamal.getPublicKey(), elGamal.getPrivateKey());
		final Signature signature = signate.signateAFile(PATH_TO_CIPHER_LIST);
		
		System.out.println("Signature: "+signature);
		
		final ElGamalDecrypt elGamalDecrypt = new ElGamalDecrypt(elGamal.getPrivateKey(), elGamal.getPublicKey(), chiffrats);
		System.out.println("Entschlüsselte Text: "+elGamalDecrypt.decrypt());
		
		final ElGamalVerificate verificate = new ElGamalVerificate(elGamal.getPublicKey(), signature);
		System.out.println("Signatur » u = r (mod q) :" + verificate.verificateCipherList(PATH_TO_CIPHER_LIST));
	}
	
	@Test
	public void ElGamalSignateTest5() throws IOException {
		final String PATH_TO_CIPHER_LIST = "C:\\Steini\\trash\\cipherList.txt";
		final ElGamal elGamal = new ElGamal(512, 0.99);

		System.out.println();
		System.out.println();
		
		final ElGamalEncrypt elGamalEncrypt = new ElGamalEncrypt("Das ist ein Test", elGamal.getPublicKey());
		final CipherList chiffrats = elGamalEncrypt.encrypt();
		
		System.out.println("Chiffrat" + chiffrats.toString());
		
		chiffrats.saveToFile(PATH_TO_CIPHER_LIST, true);
		
		final ELGamalSignate signate = new ELGamalSignate(elGamal.getPublicKey(), elGamal.getPrivateKey());
		final Signature signature = signate.signateAFile(PATH_TO_CIPHER_LIST);
		
		System.out.println("Signature: "+signature);
		
		final ElGamalDecrypt elGamalDecrypt = new ElGamalDecrypt(elGamal.getPrivateKey(), elGamal.getPublicKey(), chiffrats);
		System.out.println("Entschlüsselte Text: "+elGamalDecrypt.decrypt());
		
		final ElGamalVerificate verificate = new ElGamalVerificate(elGamal.getPublicKey(), signature);
		System.out.println("Signatur » u = r (mod q) :" + verificate.verificateCipherList(PATH_TO_CIPHER_LIST));
	}
}
