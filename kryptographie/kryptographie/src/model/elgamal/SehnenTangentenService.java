package model.elgamal;

import java.math.BigInteger;

import model.ModArith;

public class SehnenTangentenService {

	public EllipticCurvePoint calculateConjunctionPoint(final EllipticCurvePoint p1, final EllipticCurvePoint p2, final BigInteger aOfEllipticCurve, final IndustrialPrime prime) throws InfinityPointAccuredException {
		final BigInteger m = this.calculateM(p1, p2, aOfEllipticCurve, prime);
		return this.calculateP3(p1, p2, m, prime);
	}

	public BigInteger calculateM(final EllipticCurvePoint p1, final EllipticCurvePoint p2, final BigInteger aOfEllipticCurve, final IndustrialPrime prime) throws InfinityPointAccuredException {
		if(p1.getX().equals(p2.getX()) &&
				p1.getY().add(p2.getY()).mod(prime.getValue()).equals(BigInteger.ZERO)){
			throw new InfinityPointAccuredException();
		}
		
		if(!p1.getX().equals(p2.getX())){
			final BigInteger dividend = p2.getY().subtract(p1.getY());
			BigInteger divisor = p2.getX().subtract(p1.getX());
			
			divisor = ModArith.modularInverse(divisor, prime.getValue());
			
			return dividend.multiply(divisor).mod(prime.getValue()).mod(prime.getValue());
		}
		
		if(p1.getX().equals(p2.getX()) &&
				p1.getY().equals(p2.getY()) &&
						!p1.getY().equals(BigInteger.ZERO) && 
							!p2.getY().equals(BigInteger.ZERO)){
			final BigInteger dividend = BigInteger.valueOf(3).multiply(p1.getX().pow(2)).add(aOfEllipticCurve);
			BigInteger divisor = BigInteger.valueOf(2).multiply(p1.getY());
			
			divisor = ModArith.modularInverse(divisor, prime.getValue());
			
			return dividend.multiply(divisor).mod(prime.getValue());
		}
		
		throw new Error("Not possible!");
	}

	public EllipticCurvePoint calculateP3(final EllipticCurvePoint p1, final EllipticCurvePoint p2, final BigInteger m, final IndustrialPrime prime) {
		final BigInteger x = m.pow(2).subtract(p1.getX()).subtract(p2.getX()).mod(prime.getValue());
		final BigInteger y = m.multiply(BigInteger.valueOf(-1)).multiply(x.subtract(p1.getX())).subtract(p1.getY()).mod(prime.getValue());
		
		return new EllipticCurvePoint(x, y);
	}
}
