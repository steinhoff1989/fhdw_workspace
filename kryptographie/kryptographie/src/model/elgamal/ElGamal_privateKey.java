package model.elgamal;

import java.math.BigInteger;

public class ElGamal_privateKey {

	private final BigInteger x;

	public ElGamal_privateKey(final BigInteger x) {
		super();
		this.x = x;
	}

	public BigInteger getX() {
		return this.x;
	}
}
