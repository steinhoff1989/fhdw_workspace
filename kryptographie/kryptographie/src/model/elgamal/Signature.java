package model.elgamal;

import java.math.BigInteger;

public class Signature {

	private final BigInteger r;
	private final BigInteger s;
	public Signature(final BigInteger r, final BigInteger s) {
		super();
		this.r = r;
		this.s = s;
	}

	public BigInteger getR() {
		return this.r;
	}
	public BigInteger getS() {
		return this.s;
	}

	@Override
	public String toString() {
		return "("+this.r+","+this.s+")";
	}
}
