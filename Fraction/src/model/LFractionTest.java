package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigInteger;

import org.junit.Test;

public class LFractionTest {

	

	@Test
	public void equals() {
		try {
			assertEquals(false, LFraction.create(BigInteger.valueOf(1), BigInteger.valueOf(-2)).equals("HUSO"));
			assertEquals(true, LFraction.create(BigInteger.valueOf(1), BigInteger.valueOf(-2))
					.equals(LFraction.create(BigInteger.valueOf(-2), BigInteger.valueOf(4))));
		} catch (FractionConstructionException e) {
			fail();
		}
	}
}


