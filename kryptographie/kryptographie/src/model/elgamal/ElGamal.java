package model.elgamal;

import java.math.BigInteger;

import model.TrustCenter;

public class ElGamal {

	private final int binaryLength;
	private final double minProbabilit;
	private ElGamal_privateKey privateKey;
	private ElGamal_publicKey publicKey;
	EllipticCurve ellipticCurve;

	public ElGamal(final int binaryLength, final double minProbability) {
		this.binaryLength = binaryLength;
		this.minProbabilit = minProbability;
		this.generateKeys();
	}

	private void generateKeys(){
		final EllipticCurve ellipticCurve = new EC_Ypower2EqualsXpower3MinusX(this.binaryLength, this.minProbabilit);
		this.ellipticCurve = ellipticCurve;
		final EllipticCurvePoint generatingElementOfH = ellipticCurve.calculateGeneratingElementOfSubgroupH();
		
		final BigInteger orderOfG = ellipticCurve.calculateNumberOfElements().divide(BigInteger.valueOf(8));
		final BigInteger randomExponent = TrustCenter.getRandomBetween(BigInteger.valueOf(3), orderOfG);
		try {
			final EllipticCurvePoint randomElementOfH = ellipticCurve.powerFast(generatingElementOfH, randomExponent);
			this.privateKey = new ElGamal_privateKey(randomExponent);
			this.publicKey = new ElGamal_publicKey(ellipticCurve.getP().getValue(), generatingElementOfH, randomElementOfH);
		} catch (final InfinityPointAccuredException e) {
			throw new Error("Mathematisch nicht möglich.");
		}
	}
	
	public int getBinaryLength() {
		return this.binaryLength;
	}

	public double getMinProbabilit() {
		return this.minProbabilit;
	}

	public ElGamal_privateKey getPrivateKey() {
		return this.privateKey;
	}

	public ElGamal_publicKey getPublicKey() {
		return this.publicKey;
	}

	public EllipticCurve getEllipticCurve() {
		return this.ellipticCurve;
	}

}
