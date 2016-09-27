package model;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

public class creationTest {

	@Test
	public void createWithNormalFractionStringTest1_1() throws FractionConstructionException {
			assertEquals(LFraction.create(BigInteger.ONE, BigInteger.valueOf(2)), LFraction.create("1/2"));
	}
	
	@Test
	public void createWithNormalFractionStringTest1_0(){
		try {
			LFraction.create("1/0");
			fail();
		} catch (FractionConstructionException e) {
		}
	}

	@Test
	public void createWithNormalFractionStringTestN1_2() throws FractionConstructionException {
		assertEquals(LFraction.create(BigInteger.valueOf(-1), BigInteger.valueOf(2)), LFraction.create("-1/2"));
	}
	
	@Test
	public void createWithNormalFractionStringTest1_N2() throws FractionConstructionException {
		assertEquals(LFraction.create(BigInteger.valueOf(-1), BigInteger.valueOf(2)), LFraction.create("1/-2"));
	}
	
	@Test
	public void createWithNormalFractionStringTestN1_N2() throws FractionConstructionException {
		assertEquals(LFraction.create(BigInteger.valueOf(1), BigInteger.valueOf(2)), LFraction.create("-1/-2"));
	}
	
	@Test
	public void createWithSlashTestSlash2() throws FractionConstructionException {
			assertEquals(LFraction.create(BigInteger.ONE, BigInteger.valueOf(2)), LFraction.create("/2"));
	}
	
	@Test
	public void createWithSlashTestSlashN1() throws FractionConstructionException {
		assertEquals(LFraction.create(BigInteger.ONE, BigInteger.valueOf(-1)), LFraction.create("/-1"));
	}
	
	@Test
	public void createWithSlashTestSlash0(){
		try {
			LFraction.create("/0");
			fail();
		} catch (FractionConstructionException e) {
		}
	}

	@Test
	public void createWithWrongStringTestHugoSlashEgon() {
		try {
			LFraction.create("hugo/egon");
			fail();
		} catch (FractionConstructionException e) {
		}
	}

	@Test
	public void createWithWrongStringTestSlashEgon() {
		try {
			LFraction.create("/egon");
			fail();
		} catch (FractionConstructionException e) {
		}
	}

	@Test
	public void createWithWrongStringTestSlash() {
		try {
			LFraction.create("/");
			fail();
		} catch (FractionConstructionException e) {
		}
	}

	@Test
	public void creationWithDot0D5() throws FractionConstructionException {
			assertEquals(LFraction.create(BigInteger.valueOf(1), BigInteger.valueOf(2)), LFraction.create("0.5"));
	}

	@Test
	public void creationWithDotN0D5() throws FractionConstructionException {
		assertEquals(LFraction.create(BigInteger.valueOf(-1), BigInteger.valueOf(2)), LFraction.create("-0.5"));
	}

	@Test
	public void creationWithDotN0D50000() throws FractionConstructionException {
			assertEquals(LFraction.create(BigInteger.valueOf(-1), BigInteger.valueOf(2)), LFraction.create("-0.50000"));
	}
	
	@Test
	public void creationWithDot1D0() throws FractionConstructionException {
			assertEquals(LFraction.create(BigInteger.valueOf(1), BigInteger.valueOf(1)), LFraction.create("1.0"));
	}
	
	@Test
	public void creationWithDot1D() throws FractionConstructionException {
			assertEquals(LFraction.create(BigInteger.valueOf(1), BigInteger.valueOf(1)), LFraction.create("1."));
	}

	@Test
	public void creationWithDotD1() throws FractionConstructionException {
			assertEquals(LFraction.create(BigInteger.valueOf(1), BigInteger.valueOf(10)), LFraction.create(".1"));
	}

	
	@Test
	public void creationWithDot0D0032() throws FractionConstructionException {
			assertEquals(LFraction.create(BigInteger.valueOf(2), BigInteger.valueOf(625)), LFraction.create("0.0032"));
	}

//	@Test
//	public void creationWithDot0D0032() throws FractionConstructionException {
//			assertEquals(LFraction.create(BigInteger.valueOf(32), BigInteger.valueOf(10000)),LFraction.create("0.0032"));
//	}
	
	@Test
	public void creationWithE5EN1() throws FractionConstructionException {
			assertEquals(LFraction.create(BigInteger.valueOf(1), BigInteger.valueOf(2)), LFraction.create("5E-1"));
	}
	
	@Test
	public void creationWithE5E1() throws FractionConstructionException {
			assertEquals(LFraction.create(BigInteger.valueOf(100), BigInteger.valueOf(2)), LFraction.create("5E+1"));
	}

	@Test
	public void creationWithE5D1E1() throws FractionConstructionException {
			assertEquals(LFraction.create(BigInteger.valueOf(53), BigInteger.valueOf(1)), LFraction.create("5.3E+1"));
	}
	
	@Test
	public void creationWithEN5E1() throws FractionConstructionException {
			assertEquals(LFraction.create(BigInteger.valueOf(-50), BigInteger.valueOf(1)), LFraction.create("-5E+1"));
	}
	
	@Test
	public void creationWithEN5EN1() throws FractionConstructionException {
			assertEquals(LFraction.create(BigInteger.valueOf(-1), BigInteger.valueOf(2)), LFraction.create("-5E-1"));
	}
}


