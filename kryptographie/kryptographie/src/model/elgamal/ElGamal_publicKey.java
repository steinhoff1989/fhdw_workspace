package model.elgamal;

import java.math.BigInteger;

public class ElGamal_publicKey {

	private final BigInteger p;
	private final EllipticCurvePoint g;
	private final EllipticCurvePoint y;
	
	public ElGamal_publicKey(final BigInteger p, final EllipticCurvePoint g, final EllipticCurvePoint y) {
		super();
		this.p = p;
		this.g = g;
		this.y = y;
	}

	public BigInteger getP() {
		return this.p;
	}

	public EllipticCurvePoint getG() {
		return this.g;
	}

	public EllipticCurvePoint getY() {
		return this.y;
	}
}
