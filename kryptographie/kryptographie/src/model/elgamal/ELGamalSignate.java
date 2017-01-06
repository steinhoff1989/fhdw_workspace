package model.elgamal;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import model.ModArith;
import model.TrustCenter;

public class ELGamalSignate {

	private final ElGamal_publicKey publicKey;
	private final ElGamal_privateKey privateKey;
	
	public ELGamalSignate(final ElGamal_publicKey publicKey, final ElGamal_privateKey privateKey) {
		super();
		this.publicKey = publicKey;
		this.privateKey = privateKey;
	}
	
	public ELGamalSignate(final String pathToPublicKey, final String pathToPrivateKey) throws ArrayIndexOutOfBoundsException, IOException {
		super();
		this.publicKey = new ElGamal_publicKey(pathToPublicKey);
		this.privateKey = new ElGamal_privateKey(pathToPrivateKey);
	}

	public ELGamalSignate(final BigInteger privateKeyX, final BigInteger publicKeyP, final BigInteger publicKeyQ, final EllipticCurvePoint publicKeyG, final EllipticCurvePoint publicKeyY) {
		super();
		this.privateKey = new ElGamal_privateKey(privateKeyX);
		this.publicKey = new ElGamal_publicKey(publicKeyP, publicKeyQ, publicKeyG, publicKeyY);
	}
	
	public Signature signateAText(final String text){
		return this.signate(this.calculateHashOfByteArray(text.getBytes()));
	}
	
	public Signature signateAFile(final String pathToFile) throws IOException{
		final FileInputStream fin = new FileInputStream(pathToFile);
		final byte[] buffer = new byte[fin.available()];
		fin.read(buffer);
		fin.close();
		
		return this.signate(this.calculateHashOfByteArray(buffer));
	}
	
	private Signature signate(final BigInteger hash){
		final BigInteger randomK = TrustCenter.getRandomBetween(BigInteger.valueOf(3), this.publicKey.getQ());
		
		try {
			final EllipticCurvePoint pointKG = EC_Ypower2EqualsXpower3MinusX.powerFast(this.publicKey.getG(), randomK, this.publicKey.getP());
			final BigInteger r = pointKG.getX().mod(this.publicKey.getQ());
			if(r.equals(BigInteger.ZERO)){
				return this.signate(hash);
			}
			
			final BigInteger kInvers = ModArith.modularInverse(randomK, this.publicKey.getQ()).mod(this.publicKey.getQ());
			
			final BigInteger s = hash.add(this.privateKey.getX().multiply(r)).multiply(kInvers).mod(this.publicKey.getQ());
			if(s.equals(BigInteger.ZERO)){
				return this.signate(hash);
			}
			
			return new Signature(r, s);
		} catch (final InfinityPointAccuredException e) {
			return this.signate(hash);
		}
	}
	
	private BigInteger calculateHashOfByteArray(final byte[] toHash){
		MessageDigest mDigest;
		try {
			mDigest = MessageDigest.getInstance("SHA1");
		} catch (final NoSuchAlgorithmException e) {
			throw new Error("SHA1 is not a Hash Function");
		}
        final byte[] hashBytes = mDigest.digest(toHash);

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
