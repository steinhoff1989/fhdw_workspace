package model;

import static org.junit.Assert.assertEquals;

import java.math.BigInteger;

import org.junit.Test;

public class ModArithTest {
	@Test
	public void euklid1() {
		assertEquals(BigInteger.ONE, ModArith.euklid(new BigInteger("7"), new BigInteger("2")));
	}

	@Test
	public void euklidExt1() {
	BigInteger[] result = ModArith.euklidExt(new BigInteger("7"), new BigInteger("3"));
		System.out.println(result[0]);
		System.out.println(result[1]);
		System.out.println(result[2]);
	}
	
	@Test
	public void euklidExt2() {
	BigInteger[] result = ModArith.euklidExt(new BigInteger("10"), new BigInteger("21"));
		System.out.println(result[0]);
		System.out.println(result[1]);
		System.out.println(result[2]);
	}
	
	@Test
	public void euklidExt3() {
	BigInteger[] result = ModArith.euklidExt(new BigInteger("199"), new BigInteger("324"));
		System.out.println(result[0]);
		System.out.println(result[1]);
		System.out.println(result[2]);
	}
	
	@Test
	public void euklidExt4() {
		//e=43;d=7;n=121
	BigInteger[] result = ModArith.euklidExt(new BigInteger("43"), new BigInteger("100"));
		System.out.println(result[0]);
		System.out.println(result[1]);
		System.out.println(result[2]);
	}
	
	@Test
	public void powerModTest1() {
		assertEquals(new BigInteger("3"),
				ModArith.powerModulo(new BigInteger("15"), new BigInteger("55"), new BigInteger("12")));
	}

	@Test
	public void powerModTest2() {
		assertEquals(new BigInteger("100"),
				ModArith.powerModulo(new BigInteger("19"), new BigInteger("1024"), new BigInteger("123")));
	}
}
