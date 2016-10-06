package model;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

public class SignaturTest {
	
	@Test
	public void testSignatur01() throws NoSuchAlgorithmException{
		BigInteger sender_n = new BigInteger("143");
		BigInteger sender_e = new BigInteger("77");
		BigInteger sender_d = new BigInteger("53");
		String chiffrat = "Test123";
		BigInteger signatur = Signatur.signaturErstellenChiffratSHA(chiffrat, sender_d, sender_n);
		Assert.assertTrue(Signatur.signaturVerifizierenChiffratSHA(chiffrat, signatur, sender_e, sender_n));
	}

}
