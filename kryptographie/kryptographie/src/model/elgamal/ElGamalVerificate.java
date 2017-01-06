package model.elgamal;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import model.ModArith;

public class ElGamalVerificate {

	ElGamal_publicKey publicKey;
	Signature signature;

	public ElGamalVerificate(final ElGamal_publicKey publicKey, final Signature signature) {
		super();
		this.publicKey = publicKey;
		this.signature = signature;
	}

	public ElGamalVerificate(final String pathToPublicKey, final String pathToSignature) throws ArrayIndexOutOfBoundsException, IOException {
		super();
		this.publicKey = new ElGamal_publicKey(pathToPublicKey);
		this.signature = new Signature(pathToSignature);
	}

	public boolean verificateText(final String text) throws InfinityPointAccuredException {
		return this.verificate(this.calculateHashOfByteArray(text.getBytes()));
	}

	public boolean verificateCipherList(final String pathToFile) throws IOException {
		final FileInputStream fin = new FileInputStream(pathToFile);
		final byte[] buffer = new byte[fin.available()];
		fin.read(buffer);
		fin.close();

		return this.verificate(this.calculateHashOfByteArray(buffer));
	}

	private boolean verificate(final BigInteger hash) {
		final BigInteger w = ModArith.modularInverse(this.signature.getS(), this.publicKey.getQ())
				.mod(this.publicKey.getQ());
		final BigInteger u1 = w.multiply(hash).mod(this.publicKey.getQ());
		final BigInteger u2 = this.signature.getR().multiply(w).mod(this.publicKey.getQ());

		try {
			final EllipticCurvePoint u1g = EC_Ypower2EqualsXpower3MinusX.powerFast(this.publicKey.getG(), u1,
					this.publicKey.getP());
			final EllipticCurvePoint u2y = EC_Ypower2EqualsXpower3MinusX.powerFast(this.publicKey.getY(), u2,
					this.publicKey.getP());

			final EllipticCurvePoint uv = EC_Ypower2EqualsXpower3MinusX.calculateConjunctionPoint(u1g, u2y,
					this.publicKey.getP());
//			System.out.println("Q: " + this.publicKey.getQ());
//			System.out.println("r: " + this.signature.getR().mod(this.publicKey.getQ()));
//			System.out.println("u: " + uv.getX().mod(this.publicKey.getQ()));

			return uv.getX().mod(this.publicKey.getQ()).equals(this.signature.getR().mod(this.publicKey.getQ()));
		} catch (final InfinityPointAccuredException e) {
			// Is it possible?
			throw new Error();
		}

	}

	private BigInteger calculateHashOfByteArray(final byte[] byteArray) {
		MessageDigest mDigest;
		try {
			mDigest = MessageDigest.getInstance("SHA1");
		} catch (final NoSuchAlgorithmException e) {
			throw new Error("SHA1 is not a Hash Function");
		}
		final byte[] hashBytes = mDigest.digest(byteArray);

		final BigInteger hash = new BigInteger(1, hashBytes);
		return hash;
	}
}
