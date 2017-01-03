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
//		final BigInteger min = BigInteger.valueOf(5);
//		final BigInteger max = BigInteger.valueOf(99);
//		final EC_Ypower2EqualsXpower3MinusX ec = new EC_Ypower2EqualsXpower3MinusX(min, max, 0.9999);
		final EC_Ypower2EqualsXpower3MinusX ec = new EC_Ypower2EqualsXpower3MinusX(512, 0.9999);
		
		System.out.println("P: "+ec.getP().getValue());
		System.out.println("N: "+ec.getNumberOfElements());
		System.out.println("N/8 is Prime:"+TrustCenter.isPrime(0.99, ec.getNumberOfElements().divide(BigInteger.valueOf(8))));
	}
	
}
