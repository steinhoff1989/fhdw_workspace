package model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class normalizeTest {

	@Test
	public void normalizeTest1() throws FractionConstructionException{
		assertEquals(LFraction.create("1/2"),LFraction.create("2/4"));
	}

	@Test
	public void normalizeTest2() throws FractionConstructionException{
		assertEquals(LFraction.create("1/2"),LFraction.create("-2/-4"));
	}

	@Test
	public void normalizeTest3() throws FractionConstructionException{
		assertEquals(LFraction.create("-1/2"),LFraction.create("-2/4"));
	}
	
	@Test
	public void normalizeTest4() throws FractionConstructionException{
		assertEquals(LFraction.create("0"),LFraction.create("0/-4"));
	}
	
	@Test
	public void normalizeTest5() throws FractionConstructionException{
		assertEquals(LFraction.create("-1/2"),LFraction.create("2/-4"));
	}
	
	@Test
	public void normalizeTest6() throws FractionConstructionException{
		assertEquals(LFraction.create("-1"),LFraction.create("-256/256"));
	}
}
