package model;

import java.util.List;

import org.junit.Test;

import model.elgamal.Chiffrat;
import model.elgamal.ElGamal;
import model.elgamal.ElGamalDecrypt;
import model.elgamal.ElGamalEncrypt;

public class ElGamalEncryptTest {

	@Test
	public void ElGamalEncryptTest1() {
		final ElGamal elGamal = new ElGamal(256, 0.99);

		final ElGamalEncrypt elGamalEncrypt = new ElGamalEncrypt(elGamal.getEllipticCurve(), "Das ist ein Test", elGamal.getPublicKey());
		final List<Chiffrat> chiffrats = elGamalEncrypt.encrypt();
		for (int i = 0; i < chiffrats.size(); i++) {
			System.out.println(chiffrats.get(i));
		}
		
		final ElGamalDecrypt elGamalDecrypt = new ElGamalDecrypt(elGamal.getPrivateKey(), chiffrats, elGamal.getEllipticCurve());
		System.out.println(elGamalDecrypt.decryppt());
	}
}
