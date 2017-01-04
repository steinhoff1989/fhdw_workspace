package model;

import java.math.BigInteger;

public class EC_Ypower2EqualsXpower3MinusX extends EllipticCurve {

	public static final BigInteger A_OF_ELLIPTIC_CURVE = BigInteger.valueOf(-1);

	public EC_Ypower2EqualsXpower3MinusX(final IndustrialPrime p) {
		super(p);
	}

	/**
	 * Creates a EC_Ypower2EqualsXpower3MinusX object over a prime field p with
	 * binary length <binaryLength>, which divided by 8 is a prime.
	 */
	public EC_Ypower2EqualsXpower3MinusX(final int binaryLength, final double minPropability) {
		super();
		this.setPossiblePrime(new BigInteger("2").pow(binaryLength - 1),
				new BigInteger("2").pow(binaryLength).subtract(BigInteger.ONE), minPropability);
	}

	/**
	 * Creates a EC_Ypower2EqualsXpower3MinusX object over a prime p with binary
	 * length <binaryLength>, which divided by 8 is a prime.
	 */
	public EC_Ypower2EqualsXpower3MinusX(final BigInteger min, final BigInteger max, final double minPropability) {
		super();
		this.setPossiblePrime(min, max, minPropability);
	}

	public void setPossiblePrime(final BigInteger min, final BigInteger max, final double minPropability) {
		final IndustrialPrime prime = new IndustrialPrime(min, max, minPropability, 5, 8);
		this.setP(prime);
		if (!TrustCenter.isPrime(minPropability, this.getNumberOfElements().divide(BigInteger.valueOf(8)))) {
			this.setPossiblePrime(min, max, minPropability);
		}
	}

	@Override
	public BigInteger getNumberOfElements() {
		if (this.getP().getValue().mod(BigInteger.valueOf(4)).equals(BigInteger.ONE)) {
			return this.getP().getValue().add(BigInteger.ONE).subtract(this.getH());
		} else {
			throw new UnsupportedOperationException();
		}
	}

	public BigInteger getH() {
		final BigInteger xMod4 = this.getP().getxToSquare().mod(BigInteger.valueOf(4));
		final BigInteger yMod4 = this.getP().getyToSquare().mod(BigInteger.valueOf(4));

		if (xMod4.equals(BigInteger.ZERO) && yMod4.equals(BigInteger.valueOf(3))) {
			return BigInteger.valueOf(-2).multiply(this.getP().getyToSquare());
		}
		if (xMod4.equals(BigInteger.valueOf(2)) && yMod4.equals(BigInteger.valueOf(1))) {
			return BigInteger.valueOf(-2).multiply(this.getP().getyToSquare());
		}
		if (xMod4.equals(BigInteger.ZERO) && yMod4.equals(BigInteger.valueOf(1))) {
			return BigInteger.valueOf(2).multiply(this.getP().getyToSquare());
		}
		if (xMod4.equals(BigInteger.valueOf(2)) && yMod4.equals(BigInteger.valueOf(3))) {
			return BigInteger.valueOf(2).multiply(this.getP().getyToSquare());
		}
		throw new Error(); // Es muss eine der 4 obigen Möglichkeiten eintreten!
	}

	/**
	 * Generates a curvePoint of this EllipticCurve with an order of N/8
	 * 
	 * @return
	 */
	public EllipticCurvePoint calculateCurvePoint() {
		final BigInteger p = this.getP().getValue();

		if (!p.mod(BigInteger.valueOf(8)).equals(BigInteger.valueOf(5))) {
			throw new UnsupportedOperationException(
					"Die Primzahl muss für die Berechnung eines Kurvenpunktes äquivalent zu 5 modulo 8 sein!");
		}

		final BigInteger x = TrustCenter.getRandomBetween(BigInteger.valueOf(3), p);
		final BigInteger r = ModArith.powerModulo(x, BigInteger.valueOf(3), p).subtract(x).mod(p);

		if (r.equals(BigInteger.ZERO))
			return this.calculateCurvePoint();

		if (this.getP().isSquareReminder(r)) {
			BigInteger exponent = (p.subtract(BigInteger.ONE)).divide(BigInteger.valueOf(4)).mod(p);

			final BigInteger t = ModArith.powerModulo(r, exponent, p).mod(p);
			BigInteger y;
			if (t.equals(BigInteger.ONE)) {
				exponent = (p.add(BigInteger.valueOf(3))).divide(BigInteger.valueOf(8));
				y = ModArith.powerModulo(r, exponent, p);
				final EllipticCurvePoint point = new EllipticCurvePoint(x, y);
				if (!this.isOrderOfQ(point)) {
					return this.calculateCurvePoint();
				} else {
					return point;
				}
			}
			if (t.equals(p.subtract(BigInteger.ONE))) {
				exponent = (p.add(BigInteger.valueOf(3))).divide(BigInteger.valueOf(8));
				y = ModArith.modularInverse(BigInteger.valueOf(2), p)
						.multiply(ModArith.powerModulo(BigInteger.valueOf(4).multiply(r), exponent, p)).mod(p);
				final EllipticCurvePoint point = new EllipticCurvePoint(x, y);
				if (!this.isOrderOfQ(point)) {
					return this.calculateCurvePoint();
				} else {
					return point;
				}
			}
			throw new Error(); // Kann mathematisch nicht auftreten.
		} else {
			return this.calculateCurvePoint();
		}
	}

	/**
	 * Checks if <point> has a order of 2, 4 or 8. If so, the method returns
	 * true, otherwise false.
	 * 
	 * @param point
	 * @return
	 * @throws InfinityPointAccuredException
	 */
	public boolean isOrderOf2Or4Or8(final EllipticCurvePoint point) {
		final SehnenTangentenService sts = new SehnenTangentenService();
		EllipticCurvePoint nextPoint = point;

		for (int i = 2; i < 8; i++) {
			try {
				nextPoint = sts.calculateConjunctionPoint(point, nextPoint, EC_Ypower2EqualsXpower3MinusX.A_OF_ELLIPTIC_CURVE, this.getP());
			} catch (final InfinityPointAccuredException e) {
				if(BigInteger.valueOf(i).equals(this.getNumberOfElements().divide(BigInteger.valueOf(8)))){
					return false;
				}
				return true;
			}
		}
		return false;
	}
	
	public boolean isOrderOfQ(final EllipticCurvePoint point) {
		final BigInteger q = this.getNumberOfElements().divide(BigInteger.valueOf(8));
		final SehnenTangentenService sts = new SehnenTangentenService();
		EllipticCurvePoint nextPoint = point;

		for (BigInteger i = BigInteger.valueOf(2); i.compareTo(q)<=0; i=i.add(BigInteger.ONE)) {
			try {
				nextPoint = sts.calculateConjunctionPoint(point, nextPoint, EC_Ypower2EqualsXpower3MinusX.A_OF_ELLIPTIC_CURVE, this.getP());
			} catch (final InfinityPointAccuredException e) {
				return i.equals(q);
			}
		}
		return false;
	}
}
