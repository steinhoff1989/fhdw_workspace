package model.elgamal;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import model.ModArith;
import model.TrustCenter;

public class ELGamalSignate {

	private final ElGamal_publicKey publicKey;
	private final ElGamal_privateKey privateKey;
	private String text;

	public ELGamalSignate(final ElGamal_publicKey publicKey, final ElGamal_privateKey privateKey, final String text) {
		super();
		this.publicKey = publicKey;
		this.privateKey = privateKey;
		this.text = text;
	}

	public ELGamalSignate(final BigInteger privateKeyX, final BigInteger publicKeyP, final BigInteger publicKeyQ, final EllipticCurvePoint publicKeyG, final EllipticCurvePoint publicKeyY) {
		super();
		this.privateKey = new ElGamal_privateKey(privateKeyX);
		this.publicKey = new ElGamal_publicKey(publicKeyP, publicKeyQ, publicKeyG, publicKeyY);
	}
	
	public Signature signate(){
		final BigInteger randomK = TrustCenter.getRandomBetween(BigInteger.valueOf(3), this.publicKey.getQ());
		
		try {
			final EllipticCurvePoint pointKG = EC_Ypower2EqualsXpower3MinusX.powerFast(this.publicKey.getG(), randomK, this.publicKey.getP());
			final BigInteger r = pointKG.getX().mod(this.publicKey.getQ());
			if(r.equals(BigInteger.ZERO)){
				return this.signate();
			}
			
			final BigInteger kInvers = ModArith.modularInverse(randomK, this.publicKey.getQ()).mod(this.publicKey.getQ());
			
			final BigInteger s = this.calculateHashOfText().add(this.privateKey.getX().multiply(r)).multiply(kInvers).mod(this.publicKey.getQ());
			if(s.equals(BigInteger.ZERO)){
				return this.signate();
			}
			
			return new Signature(r, s);
		} catch (final InfinityPointAccuredException e) {
			return this.signate();
		}
	}
	
	public BigInteger calculateHashOfText(){
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

	public ElGamal_publicKey getPublicKey() {
		return this.publicKey;
	}

	public ElGamal_privateKey getPrivateKey() {
		return this.privateKey;
	}
}
