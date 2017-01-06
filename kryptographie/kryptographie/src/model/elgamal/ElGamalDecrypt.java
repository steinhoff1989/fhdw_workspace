package model.elgamal;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import model.ModArith;

public class ElGamalDecrypt {

	private final ElGamal_privateKey privateKey;
	private final ElGamal_publicKey publicKey;
	private final CipherList chiffrats;

	public ElGamalDecrypt(final ElGamal_privateKey privateKey, final ElGamal_publicKey publicKey,
			final CipherList chiffrats) {
		super();
		this.privateKey = privateKey;
		this.publicKey = publicKey;
		this.chiffrats = chiffrats;
	}

	public String decrypt() {
		String result = "";

		final List<EllipticCurvePoint> blockPoints = new ArrayList<EllipticCurvePoint>();
		final List<BigInteger> chiffreNumbers = new ArrayList<BigInteger>();
		for (int i = 0; i < this.chiffrats.getChiffrats().size(); i++) {
			final Cipher currentChiffrat = this.chiffrats.getChiffrats().get(i);
			final EllipticCurvePoint xa = this.calculateXA(currentChiffrat);
			final BigInteger xaC1Invers = ModArith.modularInverse(xa.getX(), this.publicKey.getP());
			final BigInteger xaC2Invers = ModArith.modularInverse(xa.getY(), this.publicKey.getP());

			final BigInteger blockPointX = currentChiffrat.getB1().multiply(xaC1Invers)
					.mod(this.publicKey.getP());
			final BigInteger blockPointY = currentChiffrat.getB2().multiply(xaC2Invers)
					.mod(this.publicKey.getP());
			final EllipticCurvePoint blockPoint = new EllipticCurvePoint(blockPointX, blockPointY);
			blockPoints.add(blockPoint);
			chiffreNumbers.add(blockPointX);
			if (!blockPointY.equals(BigInteger.ZERO)) {
				chiffreNumbers.add(blockPointY);
			}
		}

		final int maximumK = TextToNumbers_BlockChiffre.getMaximumK(Charset.getCharsetRange(Charset.FHDW_Alphabet),
				this.publicKey.getP());

		result = NumbersToText_BlockChiffre.numbersToTextBlockChiffre(chiffreNumbers, maximumK, Charset.FHDW_Alphabet);
		return result;
	}

	public EllipticCurvePoint calculateXA(final Cipher chiffrat) {
		try {
			return EC_Ypower2EqualsXpower3MinusX.powerFast(chiffrat.getA(), this.privateKey.getX(), this.publicKey.getP());
		} catch (final InfinityPointAccuredException e) {
			// TODO: Is it possible?
			throw new Error();
		}
	}

	public ElGamal_privateKey getPrivateKey() {
		return this.privateKey;
	}
}