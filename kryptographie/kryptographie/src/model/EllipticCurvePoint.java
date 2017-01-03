package model;

import java.math.BigInteger;

public class EllipticCurvePoint {

	private final BigInteger x;
	private final BigInteger y;

	public EllipticCurvePoint(final BigInteger x, final BigInteger y){
		this.x = x;
		this.y=y;
	}
	
}
