package model.elgamal;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import model.ModArith;

public class ElGamalVerificate {

	ElGamal_publicKey publicKey;
	Signature signature;
	String text;

	public ElGamalVerificate(final ElGamal_publicKey publicKey, final Signature signature, final String text) {
		super();
		this.publicKey = publicKey;
		this.signature = signature;
		this.text = text;
	}

	public boolean verificate() throws InfinityPointAccuredException {
		final BigInteger w = ModArith.modularInverse(this.signature.getS(), this.publicKey.getQ()).mod(this.publicKey.getQ());
		final BigInteger hashOfText = this.calculateHashOfText();
		final BigInteger u1 = w.multiply(hashOfText).mod(this.publicKey.getQ());
		final BigInteger u2 = this.signature.getR().multiply(w).mod(this.publicKey.getQ());

		final EllipticCurvePoint u1g = EC_Ypower2EqualsXpower3MinusX.powerFast(this.publicKey.getG(), u1, this.publicKey.getP());
		final EllipticCurvePoint u2y = EC_Ypower2EqualsXpower3MinusX.powerFast(this.publicKey.getY(), u2, this.publicKey.getP());

		final EllipticCurvePoint uv = EC_Ypower2EqualsXpower3MinusX.calculateConjunctionPoint(u1g, u2y, this.publicKey.getP());
		
		System.out.println("Q: "+this.publicKey.getQ());
		System.out.println("r: "+this.signature.getR().mod(this.publicKey.getQ()));
		System.out.println("u: "+uv.getX().mod(this.publicKey.getQ()));
		
		return uv.getX().mod(this.publicKey.getQ()).equals(this.signature.getR().mod(this.publicKey.getQ()));
	}

	public BigInteger calculateHashOfText() {
		MessageDigest mDigest;
		try {
			mDigest = MessageDigest.getInstance("SHA1");
		} catch (final NoSuchAlgorithmException e) {
			throw new Error("SHA1 is not a Hash Function");
		}
		final byte[] hashBytes = mDigest.digest(this.text.getBytes());

		final BigInteger hash = new BigInteger(1, hashBytes);
		return hash;
	}
}
