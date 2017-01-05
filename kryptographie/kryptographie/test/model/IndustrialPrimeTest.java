package model;

import java.math.BigInteger;

import org.junit.Test;

import model.elgamal.IndustrialPrime;

public class IndustrialPrimeTest {

	@Test
	public void testIndustrialPrime_3Mod4_1() {
		final BigInteger min = BigInteger.valueOf(1);
		final BigInteger max = BigInteger.valueOf(99);
		System.out.println("Primzahl zwischen " + min + " und " + max + " äquivalent 3 modulo 4");
		for (int i = 0; i < 10; i++) {
			final IndustrialPrime prime = new IndustrialPrime(min, max, 0.99, 3, 4);
			System.out.println(prime.getValue());
		}
	}

	@Test
	public void testIndustrialPrime_1Mod4_1() {
		System.out.println("Primzahl mit 512 bit äquivalent 1 modulo 4");
		for (int i = 0; i < 10; i++) {
			final IndustrialPrime prime = new IndustrialPrime(512, 0.2, 1,
					4);
			System.out.println(prime.getValue());
		}
	}

	@Test
	public void testIndustrialPrime_1Mod4_2() {
		final BigInteger min = BigInteger.valueOf(3);
		final BigInteger max = BigInteger.valueOf(99);
		System.out.println("Primzahl zwischen " + min + " und " + max + " äquivalent 1 modulo 4");
		for (int i = 0; i < 10; i++) {
			final IndustrialPrime prime = new IndustrialPrime(min, max, 0.99, 1,
					4);
			System.out.println(prime.getValue());
		}
	}

	@Test
	public void testIndustrialPrime_1Mod4_3() {
		final int bit = 1024;
		System.out.println("Primzahl zwischen min " + bit + " Bitstellen äquivalent 1 modulo 4:");
			final IndustrialPrime prime = new IndustrialPrime(bit, 0.99, 1,
					4);
			System.out.println(prime.getValue());
	}

	@Test
	public void testIndustrialPrime_1Mod4_4() {
		final int bit = 2048;
		System.out.println("Primzahl zwischen min " + bit + " Bitstellen äquivalent 1 modulo 4:");
		final IndustrialPrime prime = new IndustrialPrime(bit, 0.10, 1,
				4);
		System.out.println(prime.getValue());
	}

	@Test
	public void testIndustrialPrime_1Mod4_testSquares_1() {
		final BigInteger min = BigInteger.valueOf(72);
		final BigInteger max = BigInteger.valueOf(74);
		final IndustrialPrime prime = new IndustrialPrime(min, max, 0.99, 1, 4);
		System.out.println("Primzahl zwischen " + min + " und " + max + " äquivalent 1 modulo 4: " + prime.getValue());
		System.out.println("\n\r");
		System.out.println("X: "+prime.getxToSquare());
		System.out.println("\n\rY: "+prime.getyToSquare());
	}
	
	@Test
	public void testIndustrialPrime_1Mod4_testSquares_2() {
		final BigInteger min = BigInteger.valueOf(5);
		final BigInteger max = BigInteger.valueOf(99);
		final IndustrialPrime prime = new IndustrialPrime(min, max, 0.99, 1, 4);
		System.out.println("Primzahl zwischen " + min + " und " + max + " äquivalent 1 modulo 4: " + prime.getValue());
		System.out.println("\n\r");
		System.out.println("X: "+prime.getxToSquare());
		System.out.println("\n\rY: "+prime.getyToSquare());
	}

	@Test
	public void testIndustrialPrime_1Mod4_square_reminder_1() {
		final BigInteger min = BigInteger.valueOf(12);
		final BigInteger max = BigInteger.valueOf(15);
		final IndustrialPrime prime = new IndustrialPrime(min, max, 0.99, 1, 4);
		System.out.println(prime.isSquareReminder(BigInteger.valueOf(7)));
	}

	@Test
	public void testIndustrialPrime_1Mod4_square_reminder_2() {
		final BigInteger min = BigInteger.valueOf(72);
		final BigInteger max = BigInteger.valueOf(74);
		final IndustrialPrime prime = new IndustrialPrime(min, max, 0.99, 1, 4);
		System.out.println(prime.isSquareReminder(BigInteger.valueOf(5)));
	}

	// @Test
	// public void getRandomBetweenTest1(){
	// final IndustrialPrime p = new IndustrialPrime();
	// System.out.println(p.getRandom(BigInteger.valueOf(1),
	// BigInteger.valueOf(30), 1, 4));
	// }
	//
	// @Test
	// public void getRandomHelperTest1(){
	// final IndustrialPrime p = new IndustrialPrime();
	// System.out.println(p.getIndustrialPrimeHelper(1, 0.99,
	// BigInteger.valueOf(1), BigInteger.valueOf(30), BigInteger.valueOf(28), 1,
	// 4));
	// }

}
