package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class subtractTest {

	@Test
	public void subtractTest1() throws FractionConstructionException {
		assertEquals(LFraction.create("3/2"),LFraction.create("2").subtract(LFraction.create("1/2")));
	}

	@Test
	public void subtractTest2() throws FractionConstructionException {
		assertEquals(LFraction.create("1/1000"),LFraction.create("401/1000").subtract(LFraction.create("2/5")));
	}

	@Test
	public void subtractTest3() throws FractionConstructionException {
			assertEquals(LFraction.create("0/1"),LFraction.create("1/30").subtract(LFraction.create("1/30")));
	}
	
	@Test
	public void subtractTest4() throws FractionConstructionException {
		assertEquals(LFraction.create("-1/2"),LFraction.create("0").subtract(LFraction.create("1/2")));
	}
	
	@Test
	public void subtractTest5() throws FractionConstructionException {
		assertEquals(LFraction.create("-1/2"),LFraction.create("1/2").subtract(LFraction.create("1")));
	}
	
	@Test
	public void subtractTest6() throws FractionConstructionException {
		assertEquals(LFraction.create("-1/2"),LFraction.create("-1").subtract(LFraction.create("-1/2")));
	}
	
	@Test
	public void subtractTest7() throws FractionConstructionException {
		assertEquals(LFraction.create("1/2"),LFraction.create("-1/2").subtract(LFraction.create("-1")));
	}
	
	@Test
	public void subtractTest8() throws FractionConstructionException {
		assertEquals(LFraction.create("1/2"),LFraction.create("1/2").subtract(LFraction.create("0")));
	}
	
	@Test
	public void subtractTest9() throws FractionConstructionException {
		assertEquals(LFraction.create("0"),LFraction.create("1/3").subtract(LFraction.create("1/3")));
	}

}
