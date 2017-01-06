package model.elgamal;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import model.TrustCenter;

public class ElGamalEncrypt {

	private final ElGamal_publicKey publicKey;
	private final String text;
	
	public ElGamalEncrypt(final String text, final BigInteger p, final EllipticCurvePoint g, final EllipticCurvePoint y){
		this.text = text;
		this.publicKey = new ElGamal_publicKey(p, this.publicKey.getQ() , g, y);
	}
	
	public ElGamalEncrypt(final String text, final ElGamal_publicKey publicKey){
		this.publicKey = publicKey;
		this.text = text;
	}
	
	public CipherList encrypt(){
		final List<Cipher> chiffrats = new ArrayList<Cipher>();
		final List<EllipticCurvePoint> ellipticCurvePoints = this.encryptGenerateEllipticCurveBlocks();
		
		final BigInteger orderOfG = this.publicKey.getQ();
		BigInteger randomK = TrustCenter.getRandomBetween(BigInteger.valueOf(3), orderOfG);
		EllipticCurvePoint ky = this.calculateKY(randomK);
		
		while(ky.getX().equals(BigInteger.ZERO)|| ky.getY().equals(BigInteger.ZERO)){
			randomK = TrustCenter.getRandomBetween(BigInteger.valueOf(3), orderOfG);
			ky = this.calculateKY(randomK);
		}
		
		final EllipticCurvePoint a = this.calculateA(randomK);
		
		for(int i=0;i<ellipticCurvePoints.size();i++){
			final BigInteger b1 = ky.getX().multiply(ellipticCurvePoints.get(i).getX()).mod(this.publicKey.getP());
			final BigInteger b2 = ky.getY().multiply(ellipticCurvePoints.get(i).getY()).mod(this.publicKey.getP());
			chiffrats.add(new Cipher(a, b1, b2));
		}
		
		final CipherList chiffratsList = new CipherList(chiffrats);
		return chiffratsList;
	}
	
	public EllipticCurvePoint calculateKY(final BigInteger k){
		try {
			return EC_Ypower2EqualsXpower3MinusX.powerFast(this.publicKey.getY(), k, this.publicKey.getP());
		} catch (final InfinityPointAccuredException e) {
			return this.calculateKY(k);
		}
	}
	
	public EllipticCurvePoint calculateA(final BigInteger k){
		try {
			return EC_Ypower2EqualsXpower3MinusX.powerFast(this.publicKey.getG(), k, this.publicKey.getP());
		} catch (final InfinityPointAccuredException e) {
			return this.calculateKY(k);
		}
	}
	
	public List<EllipticCurvePoint> encryptGenerateEllipticCurveBlocks(){
		final List<EllipticCurvePoint> ellipticCurvePoints = new ArrayList<EllipticCurvePoint>();
		
		final int maximumK =  TextToNumbers_BlockChiffre.getMaximumK(Charset.getCharsetRange(Charset.FHDW_Alphabet), this.publicKey.getP());
		final List<BigInteger> numberBlocks = TextToNumbers_BlockChiffre.textToNumbersBlockChiffre(this.text, maximumK, Charset.FHDW_Alphabet);
		
		for(int i=0;i<numberBlocks.size();i=i+2){
			if(i+1 < numberBlocks.size()){
				ellipticCurvePoints.add(new EllipticCurvePoint(numberBlocks.get(i), numberBlocks.get(i+1)));
			}else{
				ellipticCurvePoints.add(new EllipticCurvePoint(numberBlocks.get(i), BigInteger.ZERO));
			}
		}
		return ellipticCurvePoints;
	}
}
