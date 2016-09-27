package model;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

public class powerTest {

	@Test
	public void powerTest0p1() throws ZeroPowerZeroException, FractionConstructionException {
		assertEquals(LFraction.ZERO, LFraction.ZERO.power(BigInteger.TEN));
	}

	@Test
	public void powerTest0p2() throws ZeroPowerZeroException, FractionConstructionException {
		assertEquals(LFraction.ZERO, LFraction.ZERO.power(BigInteger.valueOf(2)));
	}

	@Test
	public void powerTest0pN2() throws ZeroPowerZeroException, FractionConstructionException {
		assertEquals(LFraction.ZERO, LFraction.ZERO.power(BigInteger.valueOf(-2)));
	}

	@Test
	public void powerTest10p0() throws ZeroPowerZeroException, FractionConstructionException {
		assertEquals(LFraction.ONE, LFraction.TEN.power(BigInteger.ZERO));
	}

	@Test
	public void powerTestN10p0() throws ZeroPowerZeroException, FractionConstructionException {
		assertEquals(LFraction.ONE, LFraction.create("-10").power(BigInteger.ZERO));
	}

	@Test
	public void powerTest0p0() throws ZeroPowerZeroException, FractionConstructionException {
		try {
			LFraction.ZERO.power(BigInteger.ZERO);
			fail("0  hoch 0 sollte nicht gehen.");
		} catch (ZeroPowerZeroException e) {
		}
	}

	@Test
	public void powerTest10p10() throws ZeroPowerZeroException, FractionConstructionException {
		assertEquals(LFraction.create("10000000000"), LFraction.TEN.power(BigInteger.TEN));
	}
	
	@Test
	public void powerTest2p2() throws ZeroPowerZeroException, FractionConstructionException {
		assertEquals(LFraction.create("4"), LFraction.create("2").power(BigInteger.valueOf(2)));
	}

	@Test
	public void powerTest100pN1() throws ZeroPowerZeroException, FractionConstructionException {
		assertEquals(LFraction.create("1/100"), LFraction.create("100").power(BigInteger.valueOf(-1)));
	}

	@Test
	public void powerTestN10p2() throws ZeroPowerZeroException, FractionConstructionException {
		assertEquals(LFraction.create("100"), LFraction.create("-10").power(BigInteger.valueOf(2)));
	}

	@Test
	public void powerTestN10p3() throws ZeroPowerZeroException, FractionConstructionException {
		assertEquals(LFraction.create("-1000"), LFraction.create("-10").power(BigInteger.valueOf(3)));
	}
	
	@Test
	public void powerTestN10pN3() throws ZeroPowerZeroException, FractionConstructionException {
		assertEquals(LFraction.create("-1/1000"), LFraction.create("-10").power(BigInteger.valueOf(-3)));
		}
	}

