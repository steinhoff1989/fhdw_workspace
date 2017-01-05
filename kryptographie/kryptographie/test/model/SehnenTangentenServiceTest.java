package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.math.BigInteger;

import org.junit.Test;

import model.elgamal.EllipticCurvePoint;
import model.elgamal.IndustrialPrime;
import model.elgamal.InfinityPointAccuredException;
import model.elgamal.SehnenTangentenService;

public class SehnenTangentenServiceTest {

	@Test
	public void calculateMTest1() throws InfinityPointAccuredException {
		final SehnenTangentenService sts = new SehnenTangentenService();
		
		final IndustrialPrime prime = new IndustrialPrime();
		prime.setValue(BigInteger.valueOf(11));
		final EllipticCurvePoint p1 = new EllipticCurvePoint(BigInteger.valueOf(2), BigInteger.ONE);
		final EllipticCurvePoint p2 = new EllipticCurvePoint(BigInteger.valueOf(2), BigInteger.ONE);
		
		final BigInteger m = sts.calculateM(p1, p2, BigInteger.valueOf(3), prime);
		
		assertEquals(BigInteger.valueOf(2), m);
	}

	@Test
	public void calculateMTest2() throws InfinityPointAccuredException {
		final SehnenTangentenService sts = new SehnenTangentenService();
		
		final IndustrialPrime prime = new IndustrialPrime();
		prime.setValue(BigInteger.valueOf(11));
		final EllipticCurvePoint p1 = new EllipticCurvePoint(BigInteger.valueOf(2), BigInteger.ONE);
		final EllipticCurvePoint p2 = new EllipticCurvePoint(BigInteger.valueOf(0), BigInteger.valueOf(3));
		
		final BigInteger m = sts.calculateM(p1, p2, BigInteger.valueOf(3), prime);
		
		assertEquals(BigInteger.valueOf(10), m);
	}

	@Test
	public void calculateP3Test1() throws InfinityPointAccuredException {
		final SehnenTangentenService sts = new SehnenTangentenService();
		
		final IndustrialPrime prime = new IndustrialPrime();
		prime.setValue(BigInteger.valueOf(11));
		final EllipticCurvePoint p1 = new EllipticCurvePoint(BigInteger.valueOf(2), BigInteger.ONE);
		final EllipticCurvePoint p2 = new EllipticCurvePoint(BigInteger.valueOf(2), BigInteger.valueOf(1));
		final BigInteger m = sts.calculateM(p1, p2, BigInteger.valueOf(3), prime);
		
		final EllipticCurvePoint p3 = sts.calculateP3(p1, p2, m, prime);
		
		assertEquals(BigInteger.valueOf(0), p3.getX());
		assertEquals(BigInteger.valueOf(3), p3.getY());
	}

	@Test
	public void calculateP3Test2() throws InfinityPointAccuredException {
		final SehnenTangentenService sts = new SehnenTangentenService();
		
		final IndustrialPrime prime = new IndustrialPrime();
		prime.setValue(BigInteger.valueOf(11));
		final EllipticCurvePoint p1 = new EllipticCurvePoint(BigInteger.valueOf(2), BigInteger.ONE);
		final EllipticCurvePoint p2 = new EllipticCurvePoint(BigInteger.valueOf(0), BigInteger.valueOf(3));
		final BigInteger m = sts.calculateM(p1, p2, BigInteger.valueOf(3), prime);
		
		final EllipticCurvePoint p3 = sts.calculateP3(p1, p2, m, prime);
		
		assertEquals(BigInteger.valueOf(10), p3.getX());
		assertEquals(BigInteger.valueOf(7), p3.getY());
	}

	@Test
	public void calculateConjunctionPointTest1() throws InfinityPointAccuredException {
		final SehnenTangentenService sts = new SehnenTangentenService();
		
		final IndustrialPrime prime = new IndustrialPrime();
		prime.setValue(BigInteger.valueOf(11));
		final EllipticCurvePoint p1 = new EllipticCurvePoint(BigInteger.valueOf(2), BigInteger.ONE);
		EllipticCurvePoint newPoint = new EllipticCurvePoint(BigInteger.valueOf(2), BigInteger.ONE);

		newPoint = sts.calculateConjunctionPoint(p1, newPoint, BigInteger.valueOf(3), prime);
		assertEquals(BigInteger.valueOf(0), newPoint.getX());
		assertEquals(BigInteger.valueOf(3), newPoint.getY());
		
		newPoint = sts.calculateConjunctionPoint(p1, newPoint, BigInteger.valueOf(3), prime);
		assertEquals(BigInteger.valueOf(10), newPoint.getX());
		assertEquals(BigInteger.valueOf(7), newPoint.getY());
		
		newPoint = sts.calculateConjunctionPoint(p1, newPoint, BigInteger.valueOf(3), prime);
		assertEquals(BigInteger.valueOf(3), newPoint.getX());
		assertEquals(BigInteger.valueOf(1), newPoint.getY());
		
		newPoint = sts.calculateConjunctionPoint(p1, newPoint, BigInteger.valueOf(3), prime);
		assertEquals(BigInteger.valueOf(6), newPoint.getX());
		assertEquals(BigInteger.valueOf(10), newPoint.getY());
		
		newPoint = sts.calculateConjunctionPoint(p1, newPoint, BigInteger.valueOf(3), prime);
		assertEquals(BigInteger.valueOf(6), newPoint.getX());
		assertEquals(BigInteger.valueOf(1), newPoint.getY());
		
		newPoint = sts.calculateConjunctionPoint(p1, newPoint, BigInteger.valueOf(3), prime);
		assertEquals(BigInteger.valueOf(3), newPoint.getX());
		assertEquals(BigInteger.valueOf(10), newPoint.getY());
		
		newPoint = sts.calculateConjunctionPoint(p1, newPoint, BigInteger.valueOf(3), prime);
		assertEquals(BigInteger.valueOf(10), newPoint.getX());
		assertEquals(BigInteger.valueOf(4), newPoint.getY());
		
		newPoint = sts.calculateConjunctionPoint(p1, newPoint, BigInteger.valueOf(3), prime);
		assertEquals(BigInteger.valueOf(0), newPoint.getX());
		assertEquals(BigInteger.valueOf(8), newPoint.getY());
		
		newPoint = sts.calculateConjunctionPoint(p1, newPoint, BigInteger.valueOf(3), prime);
		assertEquals(BigInteger.valueOf(2), newPoint.getX());
		assertEquals(BigInteger.valueOf(10), newPoint.getY());
		
		try{
		newPoint = sts.calculateConjunctionPoint(p1, newPoint, BigInteger.valueOf(3), prime);
		assertEquals(BigInteger.valueOf(10), newPoint.getX());
		assertEquals(BigInteger.valueOf(7), newPoint.getY());
		fail();
		}catch(final InfinityPointAccuredException ex){
			
		}
	}

}
