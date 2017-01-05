package model.elgamal;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import model.ModArith;

public class ElGamalDecrypt {

	private final ElGamal_privateKey privateKey;
	private final List<Chiffrat> chiffrats;
	private final EllipticCurve ellipticCurve;
	
	public ElGamalDecrypt(final ElGamal_privateKey privateKey, final List<Chiffrat> chiffrats, final EllipticCurve ellipticCurve) {
		super();
		this.privateKey = privateKey;
		this.chiffrats = chiffrats;
		this.ellipticCurve = ellipticCurve;
	}
	
	public String decryppt(){
		String result = "";

		final List<EllipticCurvePoint> blockPoints = new ArrayList<EllipticCurvePoint>();
		final List<BigInteger> chiffreNumbers = new ArrayList<BigInteger>();
		for(int i=0;i<this.chiffrats.size();i++){
			final Chiffrat currentChiffrat = this.chiffrats.get(i);
			final EllipticCurvePoint xa = this.calculateXA(currentChiffrat);
			final BigInteger xaC1Invers = ModArith.modularInverse(xa.getX(), this.ellipticCurve.getP().getValue()); 
			final BigInteger xaC2Invers = ModArith.modularInverse(xa.getY(), this.ellipticCurve.getP().getValue()); 
			
			final BigInteger blockPointX = currentChiffrat.getB1().multiply(xaC1Invers).mod(this.ellipticCurve.getP().getValue());
			final BigInteger blockPointY = currentChiffrat.getB2().multiply(xaC2Invers).mod(this.ellipticCurve.getP().getValue());
			final EllipticCurvePoint blockPoint = new EllipticCurvePoint(blockPointX, blockPointY);
			blockPoints.add(blockPoint);
			chiffreNumbers.add(blockPointX);
			if(!blockPointY.equals(BigInteger.ZERO)){
				chiffreNumbers.add(blockPointY);
			}
		}
		
		final int maximumK =  TextToNumbers_BlockChiffre.getMaximumK(Charset.getCharsetRange(Charset.FHDW_Alphabet), this.ellipticCurve.getP().getValue());
		
		result = NumbersToText_BlockChiffre.numbersToTextBlockChiffre(chiffreNumbers, maximumK, Charset.FHDW_Alphabet);
		return result;
	}

	public EllipticCurvePoint calculateXA(final Chiffrat chiffrat){
		try {
			return this.ellipticCurve.powerFast(chiffrat.getA(), this.privateKey.getX());
		} catch (final InfinityPointAccuredException e) {
			//TODO: Is it possible?
			throw new Error();
		}
	}
	
	public ElGamal_privateKey getPrivateKey() {
		return this.privateKey;
	}

	public EllipticCurve getEllipticCurve() {
		return this.ellipticCurve;
	}
}
