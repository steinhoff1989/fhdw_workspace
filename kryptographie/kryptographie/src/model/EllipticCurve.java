package model;

import java.math.BigInteger;

public abstract class EllipticCurve {

	private IndustrialPrime p;

	public IndustrialPrime getP() {
		return this.p;
	}
	
	protected void setP(final IndustrialPrime p) {
		this.p = p;
	}

	public EllipticCurve(final IndustrialPrime p){
		this.p = p;
	}
	
	public EllipticCurve(){
	}
	
	abstract public BigInteger getNumberOfElements();
	
}
