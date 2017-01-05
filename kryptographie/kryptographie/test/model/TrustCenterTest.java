package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TrustCenterTest {
	@Test
	public void getKeysTest() throws UnsupportedEncodingException {
		final List<BigInteger> keys = TrustCenter.getKeys(512, 0.99);

		System.out.println("e: " + keys.get(0));
		System.out.println("d: " + keys.get(1));
		System.out.println("n: " + keys.get(2));
	}
	// @Test
	// public void getRandom3Mod4_1(){
	// for(int i=3; i<1000; i+=3 ){
	// for(int j = 1; j<10; j++){
	// BigInteger random = Functions.getRandom3Mod4(i);
	// BigInteger maxValue = new
	// BigInteger("2").pow(i).subtract(BigInteger.ONE);
	// if(random.mod(new BigInteger("4")).equals(new BigInteger("3"))
	// && random.compareTo(maxValue) <= 0){
	// continue;
	// }
	// else{
	// fail();
	// }
	// }
	// }
	// }

	// @Test
	// public void getRandom3Mod4_2(){
	// TrustCenter.getRandom3Mod4(512);
	// }
	//
	// @Test
	// public void getRandom3Mod4_3(){
	// TrustCenter.getRandom3Mod4(1024);
	// }
	//
	// @Test
	// public void getRandom3Mod4_4(){
	// TrustCenter.getRandom3Mod4(2048);
	// }
	//
	// @Test
	// public void getRandom3Mod4_5(){
	// for(int i= 0;i<20;i++){
	// BigInteger random = TrustCenter.getRandom3Mod4(10);
	// System.out.println(random.toString());
	// }
	//
	// }
	//
	// @Test
	// public void getRandom_2(){
	// int bits = 512;
	// BigInteger random = TrustCenter.getRandom(bits);
	// System.out.println("Random "+bits+"Bit: "+random.toString());
	// }
	//
	// @Test
	// public void getRandomTest(){
	// int bits = 10;
	// for(int i= 0;i<20;i++){
	// BigInteger random = TrustCenter.getRandom(10);
	// System.out.println("Random "+bits+"Bit: "+random.toString());
	// }
	// }

	@Test
	public void getIndustrialPrime() {
		final BigInteger potentialPrime = TrustCenter.getIndustrialPrime(5, 0.99);
		System.out.println("Prime 5Bit 99%: " + potentialPrime.toString());
	}

	@Test
	public void getIndustrialPrime2() {
		for (int i = 0; i < 1; i++) {
			final BigInteger potentialPrime = TrustCenter.getIndustrialPrime(630, 0.2);
			System.out.println("Prime 630Bit 20%: " + potentialPrime.toString());
		}
	}

	@Test
	public void getIndustrialPrime3() {
		final BigInteger potentialPrime = TrustCenter.getIndustrialPrime(2048, 0.99);
		System.out.println("Prime 2048Bit 99%: " + potentialPrime.toString());
	}

	@Test
	public void getIndustrialPrime4() {
		for (int i = 0; i < 20; i++) {
			final BigInteger potentialPrime = TrustCenter.getIndustrialPrime(10, 0.2);
			System.out.println(potentialPrime.toString());
		}
	}

	@Test
	public void getIndustrialPrime5() {
		final BigInteger potentialPrime = TrustCenter.getIndustrialPrime(512, 0.9999);
		System.out.println(potentialPrime.toString());
	}
	
	@Test
	public void getIndustrialPrime6() {
		final BigInteger potentialPrime = TrustCenter.getIndustrialPrime(1024, 0.9999);
		System.out.println(potentialPrime.toString());
	}

	@Test
	public void testNumberTest1() {
		for (int i = 0; i < 20; i++) {
			final Boolean result = TrustCenter.testNumber(new BigInteger("571"));
			if (result) {
				continue;
			} else {
				fail();
			}
		}
	}

	@Test
	public void getRandomBetween1() {
		final BigInteger random = TrustCenter.getRandomBetween(new BigInteger("123456789"),
				new BigInteger("987654321"));
		if (!(random.compareTo(new BigInteger("123456789")) >= 0)
				|| !(random.compareTo(new BigInteger("987654321")) <= 0)) {
			Assert.fail();
		}
	}

	@Test
	public void getRandomBetween2() {
		final BigInteger random = TrustCenter.getRandomBetween(new BigInteger("1793"), new BigInteger("99855632"));
		if (!(random.compareTo(new BigInteger("1793")) >= 0) || !(random.compareTo(new BigInteger("99855632")) <= 0)) {
			Assert.fail();
		}
	}

	// @Test
	// public void getRandomBinaryLength1(){
	// BigInteger random = TrustCenter.getRandom(11);
	// if(!(random.compareTo(new BigInteger("0"))>=0) || !(random.compareTo(new
	// BigInteger("2048"))<0)){
	// Assert.fail();
	// }
	// }

	@Test
	public void isPrimeTest1() {
		assertFalse(TrustCenter.isPrime(0.99, BigInteger.valueOf(15)));
	}

	@Test
	public void isPrimeTest2() {
		assertFalse(TrustCenter.isPrime(0.99, BigInteger.valueOf(72)));
	}

	@Test
	public void isPrimeTest3() {
		assertTrue(TrustCenter.isPrime(0.99, BigInteger.valueOf(73)));
	}

	@Test
	public void isPrimeTest4() {
		assertTrue(TrustCenter.isPrime(0.99, BigInteger.valueOf(5)));
	}

	@Test
	public void isPrimeTest5() {
		assertFalse(TrustCenter.isPrime(0.99, BigInteger.valueOf(63)));
	}

	@Test
	public void isPrimeTest6() {
		assertTrue(TrustCenter.isPrime(0.99, BigInteger.valueOf(101)));
	}
}
