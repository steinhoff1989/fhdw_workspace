package model.elgamal;

import java.math.BigInteger;

public class EllipticCurvePoint {

	private final BigInteger x;
	public BigInteger getX() {
		return this.x;
	}

	public BigInteger getY() {
		return this.y;
	}

	private final BigInteger y;

	public EllipticCurvePoint(final BigInteger x, final BigInteger y){
		this.x = x;
		this.y=y;
	}

	@Override
	public String toString() {
		return "("+this.x.toString()+","+this.y.toString()+")";
	}
	
	
	
}
