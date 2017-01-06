package model;

import org.junit.Test;

import model.elgamal.CipherList;
import model.elgamal.ELGamalSignate;
import model.elgamal.ElGamal;
import model.elgamal.ElGamalDecrypt;
import model.elgamal.ElGamalEncrypt;
import model.elgamal.ElGamalVerificate;
import model.elgamal.InfinityPointAccuredException;
import model.elgamal.Signature;

public class ElGamalSignateTest {

	@Test
	public void ElGamalSignateTest1() {
		final ElGamal elGamal = new ElGamal(12, 0.99);

		final ElGamalEncrypt elGamalEncrypt = new ElGamalEncrypt("Das ist ein Test", elGamal.getPublicKey());
		final CipherList chiffrats = elGamalEncrypt.encrypt();
		
		System.out.println(chiffrats.toString());
		
		final ELGamalSignate signate = new ELGamalSignate(elGamal.getPublicKey(), elGamal.getPrivateKey(), chiffrats.toString());
		final Signature signature = signate.signate();
		
		System.out.println("Signature: "+signature);
		
		final ElGamalDecrypt elGamalDecrypt = new ElGamalDecrypt(elGamal.getPrivateKey(), elGamal.getPublicKey(), chiffrats);
		System.out.println(elGamalDecrypt.decrypt());
		
		final ElGamalVerificate verificate = new ElGamalVerificate(elGamal.getPublicKey(), signature, chiffrats.toString());
		try {
			System.out.println(verificate.verificate());
		} catch (final InfinityPointAccuredException e) {
			System.out.println("InfinityPointAccuredException");
		}
	}
	
	@Test
	public void ElGamalSignateTest2() {
		final ElGamal elGamal = new ElGamal(64, 0.99);

		final ElGamalEncrypt elGamalEncrypt = new ElGamalEncrypt("Das ist ein Test", elGamal.getPublicKey());
		final CipherList chiffrats = elGamalEncrypt.encrypt();
		
		System.out.println(chiffrats.toString());
		
		final ELGamalSignate signate = new ELGamalSignate(elGamal.getPublicKey(), elGamal.getPrivateKey(), chiffrats.toString());
		final Signature signature = signate.signate();
		
		System.out.println("Signature: "+signature);
		
		final ElGamalDecrypt elGamalDecrypt = new ElGamalDecrypt(elGamal.getPrivateKey(), elGamal.getPublicKey(), chiffrats);
		System.out.println(elGamalDecrypt.decrypt());
		
		final ElGamalVerificate verificate = new ElGamalVerificate(elGamal.getPublicKey(), signature, chiffrats.toString());
		try {
			System.out.println(verificate.verificate());
		} catch (final InfinityPointAccuredException e) {
			System.out.println("InfinityPointAccuredException");
		}
	}

	
	@Test
	public void ElGamalSignateTest3() {
		final ElGamal elGamal = new ElGamal(128, 0.99);

		final ElGamalEncrypt elGamalEncrypt = new ElGamalEncrypt("Das ist ein Test", elGamal.getPublicKey());
		final CipherList chiffrats = elGamalEncrypt.encrypt();
		
		System.out.println(chiffrats.toString());
		
		final ELGamalSignate signate = new ELGamalSignate(elGamal.getPublicKey(), elGamal.getPrivateKey(), chiffrats.toString());
		final Signature signature = signate.signate();
		
		System.out.println("Signature: "+signature);
		
		final ElGamalDecrypt elGamalDecrypt = new ElGamalDecrypt(elGamal.getPrivateKey(), elGamal.getPublicKey(), chiffrats);
		System.out.println(elGamalDecrypt.decrypt());
		
		final ElGamalVerificate verificate = new ElGamalVerificate(elGamal.getPublicKey(), signature, chiffrats.toString());
		try {
			System.out.println(verificate.verificate());
		} catch (final InfinityPointAccuredException e) {
			System.out.println("InfinityPointAccuredException");
		}
	}
	
	@Test
	public void ElGamalSignateTest4() {
		final ElGamal elGamal = new ElGamal(256, 0.99);

		final ElGamalEncrypt elGamalEncrypt = new ElGamalEncrypt("Das ist ein Test", elGamal.getPublicKey());
		final CipherList chiffrats = elGamalEncrypt.encrypt();
		
		System.out.println(chiffrats.toString());
		
		final ELGamalSignate signate = new ELGamalSignate(elGamal.getPublicKey(), elGamal.getPrivateKey(), chiffrats.toString());
		final Signature signature = signate.signate();
		
		System.out.println("Signature: "+signature);
		
		final ElGamalDecrypt elGamalDecrypt = new ElGamalDecrypt(elGamal.getPrivateKey(), elGamal.getPublicKey(), chiffrats);
		System.out.println(elGamalDecrypt.decrypt());
		
		final ElGamalVerificate verificate = new ElGamalVerificate(elGamal.getPublicKey(), signature, chiffrats.toString());
		try {
			System.out.println(verificate.verificate());
		} catch (final InfinityPointAccuredException e) {
			System.out.println("InfinityPointAccuredException");
		}
	}
	
	@Test
	public void ElGamalSignateTest5() {
		final ElGamal elGamal = new ElGamal(512, 0.99);

		System.out.println();
		System.out.println();
		
		final ElGamalEncrypt elGamalEncrypt = new ElGamalEncrypt("Das ist ein Test", elGamal.getPublicKey());
		final CipherList chiffrats = elGamalEncrypt.encrypt();
		
		System.out.println("Chiffrat" + chiffrats.toString());
		
		final ELGamalSignate signate = new ELGamalSignate(elGamal.getPublicKey(), elGamal.getPrivateKey(), chiffrats.toString());
		final Signature signature = signate.signate();
		
		System.out.println("Signature: "+signature);
		
		final ElGamalDecrypt elGamalDecrypt = new ElGamalDecrypt(elGamal.getPrivateKey(), elGamal.getPublicKey(), chiffrats);
		System.out.println("Entschlüsselte Text: "+elGamalDecrypt.decrypt());
		
		final ElGamalVerificate verificate = new ElGamalVerificate(elGamal.getPublicKey(), signature, chiffrats.toString());
		try {
			System.out.println("Signatur » u = r (mod q) :" + verificate.verificate());
		} catch (final InfinityPointAccuredException e) {
			System.out.println("InfinityPointAccuredException");
		}
	}
}
