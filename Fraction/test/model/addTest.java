package model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class addTest {

	@Test
	public void addTest1() throws FractionConstructionException {
		assertEquals(LFraction.create("6/3"),LFraction.create("3/2").add(LFraction.create("1/2")));
	}

	@Test
	public void addTest2() throws FractionConstructionException {
		assertEquals(LFraction.create("401/1000"),LFraction.create("1/1000").add(LFraction.create("2/5")));
	}

	@Test
	public void addTest3() throws FractionConstructionException {
			assertEquals(LFraction.create("1/30"),LFraction.create("0/1").add(LFraction.create("1/30")));
	}
	
	@Test
	public void addTest4() throws FractionConstructionException {
		assertEquals(LFraction.create("0"),LFraction.create("-1/2").add(LFraction.create("1/2")));
	}
	
	@Test
	public void addTest5() throws FractionConstructionException {
		assertEquals(LFraction.create("1/2"),LFraction.create("-1/2").add(LFraction.create("1")));
	}
	
	@Test
	public void addTest6() throws FractionConstructionException {
		assertEquals(LFraction.create("-1"),LFraction.create("-1/2").add(LFraction.create("-1/2")));
	}
	
	@Test
	public void addTest7() throws FractionConstructionException {
		assertEquals(LFraction.create("-1/2"),LFraction.create("1/2").add(LFraction.create("-1")));
	}
	
	@Test
	public void addTest8() throws FractionConstructionException {
		assertEquals(LFraction.create("1/2"),LFraction.create("1/2").add(LFraction.create("0")));
	}
	
	@Test
	public void addTest9() throws FractionConstructionException {
		assertEquals(LFraction.create("1/3"),LFraction.create("0").add(LFraction.create("1/3")));
	}
	
}
