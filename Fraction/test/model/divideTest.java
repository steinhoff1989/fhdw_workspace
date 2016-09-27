package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class divideTest {


	@Test
	public void divideTest1() throws FractionConstructionException {
		assertEquals(LFraction.create("3/2"),LFraction.create("3/4").divide(LFraction.create("1/2")));
	}

	@Test
	public void divideTest2() throws FractionConstructionException {
		assertEquals(LFraction.create("1/1000"),LFraction.create("2/5000").divide(LFraction.create("2/5")));
	}

	@Test
	public void divideTest3() throws FractionConstructionException {
			assertEquals(LFraction.create("0"),LFraction.create("0").divide(LFraction.create("1/30")));
	}
	
	@Test
	public void divideTest4() throws FractionConstructionException {
		assertEquals(LFraction.create("-1/2"),LFraction.create("-1/4").divide(LFraction.create("1/2")));
	}
	
	@Test
	public void divideTest5() throws FractionConstructionException {
		assertEquals(LFraction.create("-1/2"),LFraction.create("-1/2").divide(LFraction.create("1")));
	}
	
	@Test
	public void divideTest6() throws FractionConstructionException {
		assertEquals(LFraction.create("-1/2"),LFraction.create("1/4").divide(LFraction.create("-1/2")));
	}
	
	@Test
	public void divideTest7() throws FractionConstructionException {
		assertEquals(LFraction.create("1/2"),LFraction.create("-1/2").divide(LFraction.create("-1")));
	}
	
	@Test
	public void divideTest8(){
		try {
			assertEquals(LFraction.create("1/2"),LFraction.create("0").divide(LFraction.create("0")));
			fail("Division durch 0 ist nicht erlaubt");
		} catch (FractionConstructionException e) {
		}
	}
	
	@Test
	public void divideTest9() throws FractionConstructionException {
		assertEquals(LFraction.create("0"),LFraction.create("0").divide(LFraction.create("1/3")));
	}

}
