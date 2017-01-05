package model.elgamal;

import java.math.BigInteger;

public class ElGamal_publicKey {

	private final EllipticCurve ec;
	private final BigInteger p;
	private final BigInteger q;
	private final EllipticCurvePoint g;
	private final EllipticCurvePoint y;
	
	public ElGamal_publicKey(final EllipticCurve ec, final BigInteger p, final BigInteger q, final EllipticCurvePoint g, final EllipticCurvePoint y) {
		super();
		this.ec = ec;
		this.p = p;
		this.q = q;
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

	public BigInteger getQ() {
		return this.q;
	}

	public EllipticCurve getEc() {
		return this.ec;
	}
}
