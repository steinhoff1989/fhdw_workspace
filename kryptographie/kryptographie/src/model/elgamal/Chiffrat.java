package model.elgamal;

import java.math.BigInteger;

public class Chiffrat {

	private final EllipticCurvePoint a;
	private final BigInteger b1;
	private final BigInteger b2;

	public Chiffrat(final EllipticCurvePoint a, final BigInteger b1, final BigInteger b2){
		this.a = a;
		this.b1 = b1;
		this.b2 = b2;
	}

	public EllipticCurvePoint getA() {
		return this.a;
	}

	public BigInteger getB1() {
		return this.b1;
	}

	public BigInteger getB2() {
		return this.b2;
	}

	@Override
	public String toString() {
		return "("+this.a+","+this.b1+","+this.b2+")";
	}
	
}
