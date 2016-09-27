package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class multiplyTest {

	@Test
	public void multiplyTest1() throws FractionConstructionException {
		assertEquals(LFraction.create("3/4"),LFraction.create("3/2").multiply(LFraction.create("1/2")));
	}

	@Test
	public void multiplyTest2() throws FractionConstructionException {
		assertEquals(LFraction.create("2/5000"),LFraction.create("1/1000").multiply(LFraction.create("2/5")));
	}

	@Test
	public void multiplyTest3() throws FractionConstructionException {
			assertEquals(LFraction.create("0"),LFraction.create("0/1").multiply(LFraction.create("1/30")));
	}
	
	@Test
	public void multiplyTest4() throws FractionConstructionException {
		assertEquals(LFraction.create("-1/4"),LFraction.create("-1/2").multiply(LFraction.create("1/2")));
	}
	
	@Test
	public void multiplyTest5() throws FractionConstructionException {
		assertEquals(LFraction.create("-1/2"),LFraction.create("-1/2").multiply(LFraction.create("1")));
	}
	
	@Test
	public void multiplyTest6() throws FractionConstructionException {
		assertEquals(LFraction.create("1/4"),LFraction.create("-1/2").multiply(LFraction.create("-1/2")));
	}
	
	@Test
	public void multiplyTest7() throws FractionConstructionException {
		assertEquals(LFraction.create("-1/2"),LFraction.create("1/2").multiply(LFraction.create("-1")));
	}
	
	@Test
	public void multiplyTest8() throws FractionConstructionException {
		assertEquals(LFraction.create("0"),LFraction.create("1/2").multiply(LFraction.create("0")));
	}
	
	@Test
	public void multiplyTest9() throws FractionConstructionException {
		assertEquals(LFraction.create("0"),LFraction.create("0").multiply(LFraction.create("1/3")));
	}

}
