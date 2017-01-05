package model;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import model.elgamal.EC_Ypower2EqualsXpower3MinusX;
import model.elgamal.EllipticCurvePoint;
import model.elgamal.IndustrialPrime;
import model.elgamal.InfinityPointAccuredException;
import model.elgamal.SehnenTangentenService;

public class EC_Ypower2EqualsXpower3MinusXTest {

	@Test
	public void numberOfEleemntsTest1() {
		final BigInteger min = BigInteger.valueOf(4);
		final BigInteger max = BigInteger.valueOf(6);
		final IndustrialPrime prime = new IndustrialPrime(min, max, 0.99, 1, 4);
		final EC_Ypower2EqualsXpower3MinusX ec = new EC_Ypower2EqualsXpower3MinusX(prime);
		assertEquals(BigInteger.valueOf(8), ec.getNumberOfElements());
	}
	
	@Test
	public void numberOfEleemntsTest2() {
		final BigInteger min = BigInteger.valueOf(12);
		final BigInteger max = BigInteger.valueOf(14);
		final IndustrialPrime prime = new IndustrialPrime(min, max, 0.99, 1, 4);
		final EC_Ypower2EqualsXpower3MinusX ec = new EC_Ypower2EqualsXpower3MinusX(prime);
		assertEquals(BigInteger.valueOf(8), ec.getNumberOfElements());
	}
	
	@Test
	public void numberOfEleemntsTest3() {
		final BigInteger min = BigInteger.valueOf(16);
		final BigInteger max = BigInteger.valueOf(18);
		final IndustrialPrime prime = new IndustrialPrime(min, max, 0.99, 1, 4);
		final EC_Ypower2EqualsXpower3MinusX ec = new EC_Ypower2EqualsXpower3MinusX(prime);
		assertEquals(BigInteger.valueOf(16), ec.getNumberOfElements());
	}
	
	@Test
	public void numberOfEleemntsTest4() {
		final BigInteger min = BigInteger.valueOf(72);
		final BigInteger max = BigInteger.valueOf(74);
		final IndustrialPrime prime = new IndustrialPrime(min, max, 0.99, 1, 4);
		final EC_Ypower2EqualsXpower3MinusX ec = new EC_Ypower2EqualsXpower3MinusX(prime);
		assertEquals(BigInteger.valueOf(80), ec.getNumberOfElements());
	}

	@Test
	public void getHTest1() {
		final BigInteger min = BigInteger.valueOf(4);
		final BigInteger max = BigInteger.valueOf(6);
		final IndustrialPrime prime = new IndustrialPrime(min, max, 0.99, 1, 4);
		final EC_Ypower2EqualsXpower3MinusX ec = new EC_Ypower2EqualsXpower3MinusX(prime);
		assertEquals(BigInteger.valueOf(-2), ec.getH());
	}

	@Test
	public void getHTest2() {
		final BigInteger min = BigInteger.valueOf(12);
		final BigInteger max = BigInteger.valueOf(14);
		final IndustrialPrime prime = new IndustrialPrime(min, max, 0.99, 1, 4);
		final EC_Ypower2EqualsXpower3MinusX ec = new EC_Ypower2EqualsXpower3MinusX(prime);
		assertEquals(BigInteger.valueOf(6), ec.getH());
	}
	
	@Test
	public void getHTest3() {
		final BigInteger min = BigInteger.valueOf(16);
		final BigInteger max = BigInteger.valueOf(18);
		final IndustrialPrime prime = new IndustrialPrime(min, max, 0.99, 1, 4);
		final EC_Ypower2EqualsXpower3MinusX ec = new EC_Ypower2EqualsXpower3MinusX(prime);
		assertEquals(BigInteger.valueOf(2), ec.getH());
	}
	
	@Test
	public void getHTest4() {
		final BigInteger min = BigInteger.valueOf(72);
		final BigInteger max = BigInteger.valueOf(74);
		final IndustrialPrime prime = new IndustrialPrime(min, max, 0.99, 1, 4);
		final EC_Ypower2EqualsXpower3MinusX ec = new EC_Ypower2EqualsXpower3MinusX(prime);
		assertEquals(BigInteger.valueOf(-6), ec.getH());
	}

	@Test
	public void calculateGeneratingElementOfSubgroupHTest1() {
		final EC_Ypower2EqualsXpower3MinusX ec = new EC_Ypower2EqualsXpower3MinusX(5, 0.9999);
		
		System.out.println("P: "+ec.getP().getValue());
		final BigInteger N = ec.getNumberOfElements();
		System.out.println("N: "+N);
		System.out.println("N/8: "+ec.getNumberOfElements().divide(BigInteger.valueOf(8)));
		System.out.println("N/8 is Prime:"+TrustCenter.isPrime(0.99, ec.getNumberOfElements().divide(BigInteger.valueOf(8))));
		final EllipticCurvePoint point = ec.calculateGeneratingElementOfSubgroupH();
		System.out.println("One Curvepoint: "+ point);
		final BigInteger y2 = ModArith.powerModulo(point.getY(), BigInteger.valueOf(2), ec.getP().getValue());
		final BigInteger x3MinusX = ModArith.powerModulo(point.getX(), BigInteger.valueOf(3), ec.getP().getValue()).subtract(point.getX()).mod(ec.getP().getValue());
		System.out.println("y^2: " + y2);
		System.out.println("x^3-x: " + x3MinusX);
		System.out.println("y^2 = x^3-x: "+ y2.equals(x3MinusX));
		
		final SehnenTangentenService sts = new SehnenTangentenService();
		System.out.println("1g: " + point.toString());
		BigInteger temp = BigInteger.ZERO;
		EllipticCurvePoint nextPoint = point;
		try {
			for(BigInteger i=BigInteger.valueOf(2);i.compareTo(N) <= 0;i=i.add(BigInteger.ONE)){
				temp = i;
				nextPoint = sts.calculateConjunctionPoint(point, nextPoint, EC_Ypower2EqualsXpower3MinusX.A_OF_ELLIPTIC_CURVE, ec.getP());
				System.out.println(i+"g: "+nextPoint.toString());
			}
		} catch (final InfinityPointAccuredException e) {
			System.out.println(temp+"g: infinity");
		}
	}

//	@Test
//	public void calculateGeneratingElementOfSubgroupHTest2() {
//		final EC_Ypower2EqualsXpower3MinusX ec = new EC_Ypower2EqualsXpower3MinusX(BigInteger.valueOf(12), BigInteger.valueOf(14), 0.9999);
//		
//		System.out.println("P: "+ec.getP().getValue());
//		System.out.println("N: "+ec.getNumberOfElements());
//		System.out.println("N/8: "+ec.getNumberOfElements().divide(BigInteger.valueOf(8)));
//		System.out.println("N/8 is Prime:"+TrustCenter.isPrime(0.99, ec.getNumberOfElements().divide(BigInteger.valueOf(8))));
//		System.out.println("One Curvepoint: "+ ec.calculateGeneratingElementOfSubgroupH());
//	}

	@Test
	public void calculateGeneratingElementOfSubgroupHTest3() {
		final EC_Ypower2EqualsXpower3MinusX ec = new EC_Ypower2EqualsXpower3MinusX(64, 0.9999);
		
		System.out.println("P: "+ec.getP().getValue());
		System.out.println("N: "+ec.getNumberOfElements());
		System.out.println("N/8: "+ec.getNumberOfElements().divide(BigInteger.valueOf(8)));
		System.out.println("N/8 is Prime:"+TrustCenter.isPrime(0.99, ec.getNumberOfElements().divide(BigInteger.valueOf(8))));
		System.out.println("One Curvepoint: "+ ec.calculateGeneratingElementOfSubgroupH());
		
		
	}

	@Test
	public void calculateGeneratingElementOfSubgroupHTest4() {
		final Date date1 =  new Date();
		System.out.println(date1);
		final EC_Ypower2EqualsXpower3MinusX ec = new EC_Ypower2EqualsXpower3MinusX(512, 0.9999);
		
		System.out.println("P: "+ec.getP().getValue());
		System.out.println("N: "+ec.getNumberOfElements());
		System.out.println("N/8 is Prime:"+TrustCenter.isPrime(0.99, ec.getNumberOfElements().divide(BigInteger.valueOf(8))));
		
		final Date date2 =  new Date();
		System.err.println(date2);
		long differenceInSeconds = TimeUnit.MILLISECONDS.toSeconds(date2.getTime() - date1.getTime());
		System.out.println("Seconds needed to generate prime: " + differenceInSeconds);
		
		System.out.println("One Curvepoint: "+ ec.calculateGeneratingElementOfSubgroupH());
		
		final Date date3 =  new Date();
		System.err.println(date3);
		differenceInSeconds = TimeUnit.MILLISECONDS.toSeconds(date3.getTime() - date2.getTime());
		System.out.println("Seconds needed to calculate Point: " + differenceInSeconds);
		
		differenceInSeconds = TimeUnit.MILLISECONDS.toSeconds(date3.getTime() - date1.getTime());
		System.out.println("Seconds needed complete: " + differenceInSeconds);
	}
	
	@Test
	public void calculateGeneratingElementOfSubgroupHTest5() {
		final EC_Ypower2EqualsXpower3MinusX ec = new EC_Ypower2EqualsXpower3MinusX(12, 0.9999);
		
		System.out.println("P: "+ec.getP().getValue());
		final BigInteger N = ec.getNumberOfElements();
		System.out.println("N: "+N);
		System.out.println("N/8: "+ec.getNumberOfElements().divide(BigInteger.valueOf(8)));
		System.out.println("N/8 is Prime:"+TrustCenter.isPrime(0.99, ec.getNumberOfElements().divide(BigInteger.valueOf(8))));
		final EllipticCurvePoint point = ec.calculateGeneratingElementOfSubgroupH();
		System.out.println("One Curvepoint: "+ point);
		final BigInteger y2 = ModArith.powerModulo(point.getY(), BigInteger.valueOf(2), ec.getP().getValue());
		final BigInteger x3MinusX = ModArith.powerModulo(point.getX(), BigInteger.valueOf(3), ec.getP().getValue()).subtract(point.getX()).mod(ec.getP().getValue());
		System.out.println("y^2: " + y2);
		System.out.println("x^3-x: " + x3MinusX);
		System.out.println("y^2 = x^3-x: "+ y2.equals(x3MinusX));
		
		final SehnenTangentenService sts = new SehnenTangentenService();
		System.out.println("1g: " + point.toString());
		BigInteger temp = BigInteger.ZERO;
		EllipticCurvePoint nextPoint = point;
		try {
			for(BigInteger i=BigInteger.valueOf(2);i.compareTo(N) <= 0;i=i.add(BigInteger.ONE)){
				temp = i;
				nextPoint = sts.calculateConjunctionPoint(point, nextPoint, EC_Ypower2EqualsXpower3MinusX.A_OF_ELLIPTIC_CURVE, ec.getP());
				System.out.println(i+"g: "+nextPoint.toString());
			}
		} catch (final InfinityPointAccuredException e) {
			System.out.println(temp+"g: infinity");
		}
	}
	
	@Test
	public void calculateGeneratingElementOfSubgroupHTest6() {
		final Date date1 =  new Date();
		System.out.println(date1);
		final EC_Ypower2EqualsXpower3MinusX ec = new EC_Ypower2EqualsXpower3MinusX(1024, 0.99);
		
		System.out.println("P: "+ec.getP().getValue());
		final BigInteger N = ec.getNumberOfElements();
		System.out.println("N: "+N);
		System.out.println("N/8: "+ec.getNumberOfElements().divide(BigInteger.valueOf(8)));
		System.out.println("N/8 is Prime:"+TrustCenter.isPrime(0.99, ec.getNumberOfElements().divide(BigInteger.valueOf(8))));
		
		final Date date2 =  new Date();
		System.err.println(date2);
		long differenceInSeconds = TimeUnit.MILLISECONDS.toSeconds(date2.getTime() - date1.getTime());
		System.out.println("Seconds needed to generate prime: " + differenceInSeconds);
		
		final EllipticCurvePoint point = ec.calculateGeneratingElementOfSubgroupH();
		System.out.println("One Curvepoint: "+ point);
		final BigInteger y2 = ModArith.powerModulo(point.getY(), BigInteger.valueOf(2), ec.getP().getValue());
		final BigInteger x3MinusX = ModArith.powerModulo(point.getX(), BigInteger.valueOf(3), ec.getP().getValue()).subtract(point.getX()).mod(ec.getP().getValue());
		System.out.println("y^2: " + y2);
		System.out.println("x^3-x: " + x3MinusX);
		System.out.println("y^2 = x^3-x: "+ y2.equals(x3MinusX));
		
		final Date date3 =  new Date();
		System.err.println(date3);
		differenceInSeconds = TimeUnit.MILLISECONDS.toSeconds(date3.getTime() - date2.getTime());
		System.out.println("Seconds needed to calculate Point: " + differenceInSeconds);
		
		differenceInSeconds = TimeUnit.MILLISECONDS.toSeconds(date3.getTime() - date1.getTime());
		System.out.println("Seconds needed complete: " + differenceInSeconds);
	}
	
}
