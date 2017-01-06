package model.elgamal;

import java.math.BigInteger;

public abstract class EllipticCurve {

	private IndustrialPrime p;
	protected BigInteger q;
	protected BigInteger numberOfElements;

//	public IndustrialPrime getP() {
//		return this.p;
//	}
//	
//	protected void setP(final IndustrialPrime p) {
//		this.p = p;
//	}
//
//	public EllipticCurve(final IndustrialPrime p){
//		this.p = p;
//		this.numberOfElements = this.calculateNumberOfElements();
//	}
//	
//	public EllipticCurve(){
//	}
//	
//	abstract protected BigInteger calculateNumberOfElements();
//	
//	abstract public EllipticCurvePoint calculateGeneratingElementOfSubgroupHAndOrderOfQ();
//	
//	public BigInteger getNumberOfElements() {
//		return this.numberOfElements;
//	}
//
//	public BigInteger getQ() {
//		return this.q;
//	}
//
//	abstract public EllipticCurvePoint powerFast(final EllipticCurvePoint basis_g, final BigInteger exponent)
//			throws InfinityPointAccuredException;
//	
//	abstract public EllipticCurvePoint calculateConjunctionPoint(EllipticCurvePoint point1, EllipticCurvePoint point2) throws InfinityPointAccuredException;
}
