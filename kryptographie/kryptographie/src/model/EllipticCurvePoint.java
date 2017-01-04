package model;

import java.math.BigInteger;

public class EllipticCurvePoint {

	private final BigInteger x;
	protected BigInteger getX() {
		return this.x;
	}

	protected BigInteger getY() {
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
