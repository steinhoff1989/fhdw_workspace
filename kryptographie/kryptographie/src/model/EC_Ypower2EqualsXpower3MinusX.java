package model;

import java.math.BigInteger;

public class EC_Ypower2EqualsXpower3MinusX extends EllipticCurve{

	public EC_Ypower2EqualsXpower3MinusX(final IndustrialPrime p) {
		super(p);
	}

	/**
	 * Creates a EC_Ypower2EqualsXpower3MinusX object over a prime p with binary length <binaryLength>, which divided by 8 is a prime.
	 */
	public EC_Ypower2EqualsXpower3MinusX(final int binaryLength, final double minPropability) {
		super();
		this.setPossiblePrime(new BigInteger("2").pow(binaryLength - 1), new BigInteger("2").pow(binaryLength).subtract(BigInteger.ONE), minPropability);
	}
	
	/**
	 * Creates a EC_Ypower2EqualsXpower3MinusX object over a prime p with binary length <binaryLength>, which divided by 8 is a prime.
	 */
	public EC_Ypower2EqualsXpower3MinusX(final BigInteger min, final BigInteger max, final double minPropability) {
		super();
		this.setPossiblePrime(min, max, minPropability);
	}
	
	public void setPossiblePrime(final BigInteger min, final BigInteger max, final double minPropability){
		final IndustrialPrime prime = new IndustrialPrime(min, max, minPropability, 1, 4);
		this.setP(prime);
		if(!TrustCenter.isPrime(minPropability, this.getNumberOfElements().divide(BigInteger.valueOf(8)))){
			this.setPossiblePrime(min, max, minPropability);
		}
	}
	
	@Override
	public BigInteger getNumberOfElements() {
		if(this.getP().getValue().mod(BigInteger.valueOf(4)).equals(BigInteger.ONE)){
			return this.getP().getValue().add(BigInteger.ONE).subtract(this.getH());
		}else{
			throw new UnsupportedOperationException();
		}
	}

	public BigInteger getH() {
		final BigInteger xMod4 = this.getP().getxToSquare().mod(BigInteger.valueOf(4));
		final BigInteger yMod4 = this.getP().getyToSquare().mod(BigInteger.valueOf(4));
		
		if(xMod4.equals(BigInteger.ZERO) && yMod4.equals(BigInteger.valueOf(3))){
			return BigInteger.valueOf(-2).multiply(this.getP().getyToSquare());
		}
		if(xMod4.equals(BigInteger.valueOf(2)) && yMod4.equals(BigInteger.valueOf(1))){
			return BigInteger.valueOf(-2).multiply(this.getP().getyToSquare());
		}
		if(xMod4.equals(BigInteger.ZERO) && yMod4.equals(BigInteger.valueOf(1))){
			return BigInteger.valueOf(2).multiply(this.getP().getyToSquare());
		}
		if(xMod4.equals(BigInteger.valueOf(2)) && yMod4.equals(BigInteger.valueOf(3))){
			return BigInteger.valueOf(2).multiply(this.getP().getyToSquare());
		}
		throw new Error(); //Es muss eine der 4 obigen Möglichkeiten eintreten!
	}
	
	public EllipticCurvePoint calculateCurvePoint(){
		final BigInteger x = TrustCenter.getRandomBetween(BigInteger.valueOf(3), this.getP().getValue());
		final BigInteger r = ModArith.powerModulo(x, BigInteger.valueOf(3), this.getP().getValue());
		
		if(this.getP().isSquareReminder(r)){
			final BigInteger p = this.getP().getValue();
			BigInteger exponent = (p.subtract(BigInteger.ONE)).divide(BigInteger.valueOf(4)).mod(p);
			
			final BigInteger t = ModArith.powerModulo(r, exponent, p);
			BigInteger y;
			if(t.equals(BigInteger.ONE)){
				exponent = (p.add(BigInteger.valueOf(3))).divide(BigInteger.valueOf(8));
				y = ModArith.powerModulo(r, exponent, p);
				return new EllipticCurvePoint(x, y);
			}
			if(t.equals(BigInteger.valueOf(-1))){
				exponent = (p.add(BigInteger.valueOf(3))).divide(BigInteger.valueOf(8));
				y = BigInteger.valueOf(2).pow(-1).multiply(ModArith.powerModulo(BigInteger.valueOf(4).multiply(r), exponent, p));
				return new EllipticCurvePoint(x, y);
			}
			throw new Error(); //Kann mathematisch nicht auftreten.
		}else{
			return this.calculateCurvePoint();
		}
	}	

}
