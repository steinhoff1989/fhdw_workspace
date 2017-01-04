package model;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.Test;

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
	public void calculateCurvePointTest1() {
		final EC_Ypower2EqualsXpower3MinusX ec = new EC_Ypower2EqualsXpower3MinusX(5, 0.9999);
		
		System.out.println("P: "+ec.getP().getValue());
		final BigInteger N = ec.getNumberOfElements();
		System.out.println("N: "+N);
		System.out.println("N/8: "+ec.getNumberOfElements().divide(BigInteger.valueOf(8)));
		System.out.println("N/8 is Prime:"+TrustCenter.isPrime(0.99, ec.getNumberOfElements().divide(BigInteger.valueOf(8))));
		final EllipticCurvePoint point = ec.calculateCurvePoint();
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
//	public void calculateCurvePointTest2() {
//		final EC_Ypower2EqualsXpower3MinusX ec = new EC_Ypower2EqualsXpower3MinusX(BigInteger.valueOf(12), BigInteger.valueOf(14), 0.9999);
//		
//		System.out.println("P: "+ec.getP().getValue());
//		System.out.println("N: "+ec.getNumberOfElements());
//		System.out.println("N/8: "+ec.getNumberOfElements().divide(BigInteger.valueOf(8)));
//		System.out.println("N/8 is Prime:"+TrustCenter.isPrime(0.99, ec.getNumberOfElements().divide(BigInteger.valueOf(8))));
//		System.out.println("One Curvepoint: "+ ec.calculateCurvePoint());
//	}

	@Test
	public void calculateCurvePointTest3() {
		final EC_Ypower2EqualsXpower3MinusX ec = new EC_Ypower2EqualsXpower3MinusX(64, 0.9999);
		
		System.out.println("P: "+ec.getP().getValue());
		System.out.println("N: "+ec.getNumberOfElements());
		System.out.println("N/8: "+ec.getNumberOfElements().divide(BigInteger.valueOf(8)));
		System.out.println("N/8 is Prime:"+TrustCenter.isPrime(0.99, ec.getNumberOfElements().divide(BigInteger.valueOf(8))));
		System.out.println("One Curvepoint: "+ ec.calculateCurvePoint());
	}

	@Test
	public void calculateCurvePointTest4() {
		final EC_Ypower2EqualsXpower3MinusX ec = new EC_Ypower2EqualsXpower3MinusX(512, 0.9999);
		
		System.out.println("P: "+ec.getP().getValue());
		System.out.println("N: "+ec.getNumberOfElements());
		System.out.println("N/8 is Prime:"+TrustCenter.isPrime(0.99, ec.getNumberOfElements().divide(BigInteger.valueOf(8))));
		System.out.println("One Curvepoint: "+ ec.calculateCurvePoint());
	}
	
	@Test
	public void calculateCurvePointTest5() {
		final EC_Ypower2EqualsXpower3MinusX ec = new EC_Ypower2EqualsXpower3MinusX(12, 0.9999);
		
		System.out.println("P: "+ec.getP().getValue());
		final BigInteger N = ec.getNumberOfElements();
		System.out.println("N: "+N);
		System.out.println("N/8: "+ec.getNumberOfElements().divide(BigInteger.valueOf(8)));
		System.out.println("N/8 is Prime:"+TrustCenter.isPrime(0.99, ec.getNumberOfElements().divide(BigInteger.valueOf(8))));
		final EllipticCurvePoint point = ec.calculateCurvePoint();
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
	
}
