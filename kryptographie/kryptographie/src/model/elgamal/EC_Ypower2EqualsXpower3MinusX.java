package model.elgamal;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.ModArith;
import model.TrustCenter;

public class EC_Ypower2EqualsXpower3MinusX extends EllipticCurve {

	private IndustrialPrime p;
	private final BigInteger q;
	private final BigInteger numberOfElements;
	private boolean isPrimeQ = false;
	private boolean generatingElementOfSubgroupHIsOrderOfQ = false;
	private EllipticCurvePoint generatingElementOfSubgroupH;
	
	//for y^2 = x^3-x : the a=-1, b=0
	public static final BigInteger A_OF_ELLIPTIC_CURVE = BigInteger.valueOf(-1);
	
	private Date startTimeToFindPrime;
	private Date endTimeToFindPrime;
	private BigInteger countOfTriedRandomNumbers = BigInteger.ZERO;
	private BigInteger countOfPrimesFoundWhereNDiv8WasNotAPrime = BigInteger.ZERO;
	
	private Date startTimeCalculateGeneratingElementOfSubgroupHAndOrderOfQ;
	private Date endTimeCalculateGeneratingElementOfSubgroupHAndOrderOfQ;

	/**
	 * Only for testing!
	 * @param p: The industrial Prime over which the ellipticCUrve gets generated
	 */
	public EC_Ypower2EqualsXpower3MinusX(final IndustrialPrime p) {
		this.p = p;
		this.numberOfElements = this.calculateNumberOfElements();
		this.q = this.numberOfElements.divide(BigInteger.valueOf(8));
	}

	/**
	 * Creates a EC_Ypower2EqualsXpower3MinusX object over a prime field p with
	 * binary length <binaryLength>, which divided by 8 is a prime.
	 */
	public EC_Ypower2EqualsXpower3MinusX(final int binaryLength, final double minPropability) {
		this(new BigInteger("2").pow(binaryLength - 1), new BigInteger("2").pow(binaryLength).subtract(BigInteger.ONE), minPropability);
	}

	/**
	 * Creates a EC_Ypower2EqualsXpower3MinusX object over a prime field p with 
	 * binary length <binaryLength>, which divided by 8 is a prime.
	 */
	public EC_Ypower2EqualsXpower3MinusX(final BigInteger min, final BigInteger max, final double minPropability) {
		super();
		this.startTimeToFindPrime = new Date();
		this.setPossiblePrime(min, max, minPropability);
		this.endTimeToFindPrime = new Date();
		
		this.numberOfElements = this.calculateNumberOfElements();
		this.q = this.numberOfElements.divide(BigInteger.valueOf(8));
	}

	/**
	 * Finds and sets a prime to this EllipticCurve, 
	 * where their value divided by 8 is still a prime.
	 * @param min: Minimum border of prime
	 * @param max: Maximum border of prime
	 * @param minPropability: the minimal probability that p and p/8 is prime.
	 */
//	public void setPossiblePrime(final BigInteger min, final BigInteger max, final double minPropability, final BigInteger random) {
//		this.startTimeToFindPrime = new Date();
//		final IndustrialPrime prime = new IndustrialPrime(min, max, minPropability, 5, 8, random);
//		this.countOfTriedRandomNumbers = this.countOfTriedRandomNumbers.add(prime.getCountOfTriedRandomNumbers());
//		this.setP(prime);
//		if (!TrustCenter.isPrime(minPropability, this.calculateNumberOfElements().divide(BigInteger.valueOf(8)))) {
//			this.countOfPrimesFoundWhereNDiv8WasNotAPrime = this.countOfPrimesFoundWhereNDiv8WasNotAPrime.add(BigInteger.ONE);
//			this.setPossiblePrime(min, max, minPropability, prime.getValue().add(BigInteger.valueOf(8)));
//		}
//		this.endTimePrimeFound = new Date();
//	}
	
	/**
	 * Finds and sets a prime to this EllipticCurve, 
	 * where their value divided by 8 is still a prime.
	 * @param min: Minimum border of prime
	 * @param max: Maximum border of prime
	 * @param minPropability: the minimal probability that p and p/8 is prime.
	 */
	public void setPossiblePrime(final BigInteger min, final BigInteger max, final double minPropability) {
		final IndustrialPrime prime = new IndustrialPrime(min, max, minPropability, 5, 8);
		this.countOfTriedRandomNumbers = this.countOfTriedRandomNumbers.add(prime.getCountOfTriedRandomNumbers());
		this.p=prime;
		if (!TrustCenter.isPrime(minPropability, this.calculateNumberOfElements().divide(BigInteger.valueOf(8)))) {
			this.isPrimeQ = false;
			this.countOfPrimesFoundWhereNDiv8WasNotAPrime = this.countOfPrimesFoundWhereNDiv8WasNotAPrime.add(BigInteger.ONE);
			this.setPossiblePrime(min, max, minPropability);
		}
		this.isPrimeQ = false;
	}

	/**
	 * Calculates the number of elements over the class variable p. Also known as order of p.
	 */
	protected BigInteger calculateNumberOfElements() {
		if (this.getP().getValue().mod(BigInteger.valueOf(4)).equals(BigInteger.ONE)) {
			final BigInteger result = this.getP().getValue().add(BigInteger.ONE).subtract(this.getH());
			return result;
		} else {
			throw new UnsupportedOperationException();
		}
	}

	/**
	 * A helper function for calculating the numberOfElements
	 */
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
	 * Generates a curvePoint g of this EllipticCurve with an order of N/8=q,
	 * which is a generating Element of subgroup H
	 * @return
	 */
	public EllipticCurvePoint calculateGeneratingElementOfSubgroupHAndOrderOfQ() {
		if(this.startTimeCalculateGeneratingElementOfSubgroupHAndOrderOfQ == null){
			this.startTimeCalculateGeneratingElementOfSubgroupHAndOrderOfQ = new Date();
		}
		final BigInteger p = this.getP().getValue();

		if (!p.mod(BigInteger.valueOf(8)).equals(BigInteger.valueOf(5))) {
			throw new UnsupportedOperationException(
					"Die Primzahl muss für die Berechnung eines Kurvenpunktes äquivalent zu 5 modulo 8 sein!");
		}

		final BigInteger x = TrustCenter.getRandomBetween(BigInteger.valueOf(3), p);
		final BigInteger r = ModArith.powerModulo(x, BigInteger.valueOf(3), p).subtract(x).mod(p);

		if (r.equals(BigInteger.ZERO))
			return this.calculateGeneratingElementOfSubgroupHAndOrderOfQ();

		if (this.getP().isSquareReminder(r)) {
			BigInteger exponent = (p.subtract(BigInteger.ONE)).divide(BigInteger.valueOf(4)).mod(p);

			final BigInteger t = ModArith.powerModulo(r, exponent, p).mod(p);
			BigInteger y;
			if (t.equals(BigInteger.ONE)) {
				exponent = (p.add(BigInteger.valueOf(3))).divide(BigInteger.valueOf(8));
				y = ModArith.powerModulo(r, exponent, p);
				final EllipticCurvePoint point = new EllipticCurvePoint(x, y);
				if (!this.isOrderOfQ(point)) {
					return this.calculateGeneratingElementOfSubgroupHAndOrderOfQ();
				} else {
					this.endTimeCalculateGeneratingElementOfSubgroupHAndOrderOfQ = new Date();
					this.generatingElementOfSubgroupH = point;
					this.generatingElementOfSubgroupHIsOrderOfQ = true;
					return point;
				}
			}
			if (t.equals(p.subtract(BigInteger.ONE))) {
				exponent = (p.add(BigInteger.valueOf(3))).divide(BigInteger.valueOf(8));
				y = ModArith.modularInverse(BigInteger.valueOf(2), p)
						.multiply(ModArith.powerModulo(BigInteger.valueOf(4).multiply(r), exponent, p)).mod(p);
				final EllipticCurvePoint point = new EllipticCurvePoint(x, y);
				if (!this.isOrderOfQ(point)) {
					return this.calculateGeneratingElementOfSubgroupHAndOrderOfQ();
				} else {
					this.endTimeCalculateGeneratingElementOfSubgroupHAndOrderOfQ = new Date();
					this.generatingElementOfSubgroupH = point;
					this.generatingElementOfSubgroupHIsOrderOfQ = true;
					return point;
				}
			}
			throw new Error(); // Kann mathematisch nicht auftreten.
		} else {
			return this.calculateGeneratingElementOfSubgroupHAndOrderOfQ();
		}
	}
	
	public EllipticCurvePoint calculateConjunctionPoint(final EllipticCurvePoint point1, final EllipticCurvePoint point2) throws InfinityPointAccuredException{
		final SehnenTangentenService sts = new SehnenTangentenService();
		return sts.calculateConjunctionPoint(point1, point2, A_OF_ELLIPTIC_CURVE, this.getP().getValue());
	}
	
	public static EllipticCurvePoint calculateConjunctionPoint(final EllipticCurvePoint point1, final EllipticCurvePoint point2, final BigInteger prime) throws InfinityPointAccuredException{
		final SehnenTangentenService sts = new SehnenTangentenService();
		return sts.calculateConjunctionPoint(point1, point2, A_OF_ELLIPTIC_CURVE, prime);
	}

	/**
	 * Checks if <basis> has an order of N/8 (q). If so, the method returns true,
	 * otherwise false.
	 * 
	 * @param basis: The point that should be the generating element of N/8
	 * @return: true if <basis> has an order of N/8, false otherwise.
	 */
	public boolean isOrderOfQ(final EllipticCurvePoint basis) {
		final SehnenTangentenService sts = new SehnenTangentenService();
		final BigInteger exponent = this.calculateNumberOfElements().divide(BigInteger.valueOf(8));
		String exponentBinay = exponent.toString(2);
		final int k = exponentBinay.length();
		final List<EllipticCurvePoint> results = new ArrayList<EllipticCurvePoint>();

		EllipticCurvePoint result = basis;
		results.add(basis);
		for (int i = 1; i < k; i++) {
			try {
				result = sts.calculateConjunctionPoint(result, result, A_OF_ELLIPTIC_CURVE, this.getP().getValue());
			} catch (final InfinityPointAccuredException e) {
				return false;
			}
			results.add(result);
		}

		result = null;
		for (int i = k; i > 0; i--) {
			if (exponentBinay.endsWith("1")) {
				if (result == null) {
					result = results.get(results.size() - i);
				} else {
					try {
						result = sts.calculateConjunctionPoint(result, results.get(results.size() - i),
								A_OF_ELLIPTIC_CURVE, this.getP().getValue());
					} catch (final InfinityPointAccuredException e) {
						return i == 1;
					}
				}
			}
			exponentBinay = exponentBinay.substring(0, exponentBinay.length() - 1);
		}
		return false;
	}
	
	public static EllipticCurvePoint powerFast(final EllipticCurvePoint basis_g, final BigInteger exponent, final BigInteger p)
			throws InfinityPointAccuredException {
		final SehnenTangentenService sts = new SehnenTangentenService();
		String exponentBinay = exponent.toString(2);
		final int k = exponentBinay.length();
		final List<EllipticCurvePoint> results = new ArrayList<EllipticCurvePoint>();

		EllipticCurvePoint result = basis_g;
		results.add(basis_g);
		for (int i = 1; i < k; i++) {
			result = sts.calculateConjunctionPoint(result, result, A_OF_ELLIPTIC_CURVE, p);
			results.add(result);
		}

		result = null;
		for (int i = k; i > 0; i--) {
			if (exponentBinay.endsWith("1")) {
				if (result == null) {
					result = results.get(results.size() - i);
				} else {
					result = sts.calculateConjunctionPoint(result, results.get(results.size() - i), A_OF_ELLIPTIC_CURVE,
							p);
				}
			}
			exponentBinay = exponentBinay.substring(0, exponentBinay.length() - 1);
		}
		return result;
	}

	/**
	 * Calculates the elliptic curve point <basis_g>*exponent
	 * @param basis_g: the element that gets conjuncted
	 * @param exponent: says how often basis_g will be conjuncted to itself
	 * @return: The new found elliptioc curve point <basis_g>*exponent
	 * @throws InfinityPointAccuredException: gets thrown if the infinity Point gets calculated
	 */
	public EllipticCurvePoint powerFast(final EllipticCurvePoint basis_g, final BigInteger exponent)
			throws InfinityPointAccuredException {
		final SehnenTangentenService sts = new SehnenTangentenService();
		String exponentBinay = exponent.toString(2);
		final int k = exponentBinay.length();
		final List<EllipticCurvePoint> results = new ArrayList<EllipticCurvePoint>();

		EllipticCurvePoint result = basis_g;
		results.add(basis_g);
		for (int i = 1; i < k; i++) {
			result = sts.calculateConjunctionPoint(result, result, A_OF_ELLIPTIC_CURVE, this.getP().getValue());
			results.add(result);
		}

		result = null;
		for (int i = k; i > 0; i--) {
			if (exponentBinay.endsWith("1")) {
				if (result == null) {
					result = results.get(results.size() - i);
				} else {
					result = sts.calculateConjunctionPoint(result, results.get(results.size() - i), A_OF_ELLIPTIC_CURVE,
							this.getP().getValue());
				}
			}
			exponentBinay = exponentBinay.substring(0, exponentBinay.length() - 1);
		}
		return result;
	}

	public Date getStartTimeToFindPrime() {
		return this.startTimeToFindPrime;
	}

	public Date getEndTimePrimeFound() {
		return this.endTimeToFindPrime;
	}

	public BigInteger getCountOfTriedRandomNumbers() {
		return this.countOfTriedRandomNumbers;
	}

	public BigInteger getCountOfPrimesFoundWhereNDiv8WasNotAPrime() {
		return this.countOfPrimesFoundWhereNDiv8WasNotAPrime;
	}

	public Date getStartTimeCalculateGeneratingElementOfSubgroupHAndOrderOfQ() {
		return this.startTimeCalculateGeneratingElementOfSubgroupHAndOrderOfQ;
	}

	public Date getEndTimeCalculateGeneratingElementOfSubgroupHAndOrderOfQ() {
		return this.endTimeCalculateGeneratingElementOfSubgroupHAndOrderOfQ;
	}

	public BigInteger getQ() {
		return this.q;
	}

	public BigInteger getNumberOfElements() {
		return this.numberOfElements;
	}

	public IndustrialPrime getP() {
		return this.p;
	}

	public boolean isPrimeQ() {
		return this.isPrimeQ;
	}

	public boolean isGeneratingElementOfSubgroupHIsOrderOfQ() {
		return this.generatingElementOfSubgroupHIsOrderOfQ;
	}
}
