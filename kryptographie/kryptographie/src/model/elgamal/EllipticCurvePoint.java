package model.elgamal;

import java.math.BigInteger;

public class EllipticCurvePoint {

	private final BigInteger x;
	private final BigInteger y;

	/**
	 * Represents a Point of an elliptic curve with the coordinates (x, y)
	 * @param x: x coordinate
	 * @param y: y coordinate
	 */
	public EllipticCurvePoint(final BigInteger x, final BigInteger y){
		this.x = x;
		this.y=y;
	}

	@Override
	public String toString() {
		return "("+this.x.toString()+","+this.y.toString()+")";
	}
	
	public BigInteger getX() {
		return this.x;
	}
	
	public BigInteger getY() {
		return this.y;
	}
}
