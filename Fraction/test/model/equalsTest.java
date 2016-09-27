package model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class equalsTest {

	@Test
	public void equalsTest1() throws FractionConstructionException{
		assertEquals(false, LFraction.create("1/-2").equals("HUSO"));
	}

	@Test
	public void equalsTest2() throws FractionConstructionException{
		assertEquals(true, LFraction.create("1/-2").equals(LFraction.create("-2/4")));
	}
	@Test
	public void equalsTest3() throws FractionConstructionException{
		assertEquals(true, LFraction.create("-1/2").equals(LFraction.create("-2/4")));
	}
	@Test
	public void equalsTest4() throws FractionConstructionException{
		assertEquals(true, LFraction.create("1/2").equals(LFraction.create("-2/-4")));
	}
	@Test
	public void equalsTest5() throws FractionConstructionException{
		assertEquals(true, LFraction.create("1").equals(LFraction.create("2/2")));
	}
	@Test
	public void equalsTest6() throws FractionConstructionException{
		assertEquals(true, LFraction.create("2").equals(LFraction.create("4/2")));
	}
	@Test
	public void equalsTest7() throws FractionConstructionException{
		assertEquals(true, LFraction.create("1").equals(LFraction.create("10E-1")));
	}

}
