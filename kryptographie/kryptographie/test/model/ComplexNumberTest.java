package model;

import java.math.BigInteger;

import org.junit.Test;

public class ComplexNumberTest {

	@Test
	public void complexNumberDivide() {
		try {
			final ComplexNumber c1 = new ComplexNumber(Fraction.create(BigInteger.valueOf(6), BigInteger.ONE), Fraction.create(BigInteger.valueOf(3), BigInteger.ONE));
			final ComplexNumber c2 = new ComplexNumber(Fraction.create(BigInteger.valueOf(7), BigInteger.ONE), Fraction.create(BigInteger.valueOf(-5), BigInteger.ONE));
			final ComplexNumber result = c1.divide(c2);
			System.out.println("Real: "+ result.getReal()+"\n\r Imaginary: "+ result.getImaginary());
			
			result.functionF();
			
			System.out.println("Real: "+ result.getReal()+"\n\r Imaginary: "+ result.getImaginary());
			
			
			
		} catch (final FractionConstructionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void complexNumberDivide2() {
		try {
			final ComplexNumber c1 = new ComplexNumber(Fraction.create(BigInteger.valueOf(73), BigInteger.ONE), Fraction.create(BigInteger.valueOf(0), BigInteger.ONE));
			final ComplexNumber c2 = new ComplexNumber(Fraction.create(BigInteger.valueOf(27), BigInteger.ONE), Fraction.create(BigInteger.valueOf(1), BigInteger.ONE));
			final ComplexNumber result = c1.divide(c2);
			System.out.println("Real: "+ result.getReal()+"\n\r Imaginary: "+ result.getImaginary());
			
			result.functionF();
			
			System.out.println("Real: "+ result.getReal()+"\n\r Imaginary: "+ result.getImaginary());
		} catch (final FractionConstructionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void complexNumberMultiplyTest1(){
		try {
			final ComplexNumber a = new ComplexNumber(Fraction.create(BigInteger.valueOf(3), BigInteger.ONE), Fraction.create(BigInteger.valueOf(2), BigInteger.ONE));
			final ComplexNumber b = new ComplexNumber(Fraction.create(BigInteger.valueOf(1), BigInteger.ONE), Fraction.create(BigInteger.valueOf(4), BigInteger.ONE));
			
			System.out.println(a.multiply(b).toString());
		
		} catch (final FractionConstructionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void complexNumberSubtractTest1(){
		try {
			final ComplexNumber a = new ComplexNumber(Fraction.create(BigInteger.valueOf(8), BigInteger.ONE), Fraction.create(BigInteger.valueOf(6), BigInteger.ONE));
			final ComplexNumber b = new ComplexNumber(Fraction.create(BigInteger.valueOf(5), BigInteger.ONE), Fraction.create(BigInteger.valueOf(2), BigInteger.ONE));
			
			System.out.println(a.subtract(b).toString());
		
		} catch (final FractionConstructionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}