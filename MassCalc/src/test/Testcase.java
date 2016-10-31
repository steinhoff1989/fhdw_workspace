package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import BufferAndLock.Buffer;
import BufferAndLock.Buffer.StoppException;
import exceptions.DivideByZeroException;
import exceptions.NoMoreVariablesAvailableException;
import model.Add;
import model.Constant;
import model.Divide;
import model.Multiply;
import model.Variable;
import model.Subtract;

public class Testcase {

	

	@Test
	public void testAdd1() throws StoppException, DivideByZeroException {

		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Buffer<Integer> streamTwo = new Buffer<Integer>(100);

		streamTwo.put(7);
		streamOne.put(5);

		Add add1 = new Add(streamOne, streamTwo);
		assertEquals((Integer) 12, add1.getResults().get());
	}
	
	@Test
	public void testAdd2() throws StoppException, DivideByZeroException {

		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Buffer<Integer> streamTwo = new Buffer<Integer>(100);

		streamTwo.put(0);
		streamOne.put(0);

		Add add1 = new Add(streamOne, streamTwo);

		assertEquals((Integer) 0, add1.getResults().get());
	}
	
	@Test
	public void testAdd3() throws StoppException, DivideByZeroException {

		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Buffer<Integer> streamTwo = new Buffer<Integer>(100);

		streamTwo.put(15);
		streamOne.put(0);

		Add add1 = new Add(streamOne, streamTwo);

		assertEquals(15, add1.getResults().get().intValue());
	}
	
	@Test
	public void testAdd4() throws StoppException, DivideByZeroException {

		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Buffer<Integer> streamTwo = new Buffer<Integer>(100);

		streamTwo.put(0);
		streamOne.put(-5);

		Add add1 = new Add(streamOne, streamTwo);

		assertEquals(-5, add1.getResults().get().intValue());
	}
	
	@Test
	public void testAdd5() throws StoppException, DivideByZeroException {

		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Buffer<Integer> streamTwo = new Buffer<Integer>(100);

		streamTwo.put(-6);
		streamOne.put(-5);

		Add add1 = new Add(streamOne, streamTwo);

		assertEquals(-11, add1.getResults().get().intValue());
	}
	
	@Test
	public void testAdd6() throws StoppException, DivideByZeroException {

		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Buffer<Integer> streamTwo = new Buffer<Integer>(100);

		streamTwo.put(-5);
		streamOne.put(6);

		Add add1 = new Add(streamOne, streamTwo);

		assertEquals(1, add1.getResults().get().intValue());
	}
	
	@Test
	public void testCombined1() throws StoppException, DivideByZeroException {

		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Buffer<Integer> streamTwo = new Buffer<Integer>(100);
		Buffer<Integer> streamThree = new Buffer<Integer>(100);
		Buffer<Integer> streamFour = new Buffer<Integer>(100);

		streamTwo.put(7);
		streamOne.put(5);
		streamThree.put(2);
		streamFour.put(3);

		Add add1 = new Add(streamOne, streamTwo);
		Add add2 = new Add(streamThree, streamFour);
		Subtract sub1 = new Subtract(add1.getResults(), add2.getResults());

		assertEquals(7, sub1.getResults().get().intValue());
	}
	
	@Test
	public void testCombined2() throws DivideByZeroException, StoppException {

		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Buffer<Integer> streamTwo = new Buffer<Integer>(100);

		streamOne.put(5);
		streamOne.put(7);
		streamOne.stopp();
		
		streamTwo.put(7);
		streamTwo.put(5);
		streamOne.stopp();

		Multiply mul1 = new Multiply(streamOne, streamTwo); //35
		
		Constant const2 = new Constant(2);
		
		Add add = new Add(const2.getResults(), mul1.getResults()); //37
		
		Subtract sub1 = new Subtract(add.getResults(), const2.getResults()); //35
		
		Constant const5 = new Constant(5);
		
		Divide div1 = new Divide(sub1.getResults(), const5.getResults()); //7

		assertEquals(7, div1.getResults().get().intValue());
		assertEquals(7, div1.getResults().get().intValue());
		try {
			assertEquals(7, div1.getResults().get().intValue());
			fail();
		} catch (StoppException e) {
		}
	}
	
	@Test
	public void testConstant1() throws StoppException, DivideByZeroException {
		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Constant constant5 = new Constant(5);

		streamOne.put(5);
		streamOne.put(7);

		Add add1 = new Add(streamOne, constant5.getResults());
		assertEquals(10, add1.getResults().get().intValue());
		assertEquals(12, add1.getResults().get().intValue());
	}
	
	@Test
	public void testConstant2() throws StoppException, DivideByZeroException {
		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Constant constant5 = new Constant(5);

		streamOne.put(5);
		streamOne.put(7);
		streamOne.put(9);

		Add add1 = new Add(streamOne, constant5.getResults());
		assertEquals(10, add1.getResults().get().intValue());
		assertEquals(12, add1.getResults().get().intValue());
		assertEquals(14, add1.getResults().get().intValue());
	}
	
	@Test
	public void testDivide1() throws StoppException, DivideByZeroException {
		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Constant constant5 = new Constant(5);

		streamOne.put(5);
		streamOne.put(7);
		streamOne.put(29);

		Divide div1 = new Divide(streamOne, constant5.getResults());
		assertEquals(1, div1.getResults().get().intValue());
		assertEquals(1, div1.getResults().get().intValue());
		assertEquals(5, div1.getResults().get().intValue());
	}
	
	@Test
	public void testDivide2() throws StoppException, DivideByZeroException {
		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Constant constant5Neg = new Constant(-5);

		streamOne.put(5);
		streamOne.put(-7);
		streamOne.put(32);

		Divide div1 = new Divide(streamOne, constant5Neg.getResults());
		assertEquals(-1, div1.getResults().get().intValue());
		assertEquals(1, div1.getResults().get().intValue());
		assertEquals(-6, div1.getResults().get().intValue());
	}
	
	@Test
	public void testDivide3() throws StoppException, DivideByZeroException {

		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Buffer<Integer> streamTwo = new Buffer<Integer>(100);

		streamOne.put(35);
		streamTwo.put(5);

		Divide div1 = new Divide(streamOne, streamTwo);

		assertEquals(7, div1.getResults().get().intValue());
	}

	@Test
	public void testDivide4() throws StoppException, DivideByZeroException {
		
		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Buffer<Integer> streamTwo = new Buffer<Integer>(100);
		
		streamOne.put(35);
		streamTwo.put(-5);
		
		Divide div1 = new Divide(streamOne, streamTwo);
		
		assertEquals(-7, div1.getResults().get().intValue());
	}
	
	@Test
	public void testDividedByZeroException1() throws StoppException {

		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Buffer<Integer> streamTwo = new Buffer<Integer>(100);

		streamOne.put(35);
		streamTwo.put(0);

		Divide div1 = new Divide(streamOne, streamTwo);

		try {
			assertEquals((Integer) 7, div1.getResults().get());
			fail();
		} catch (DivideByZeroException e) {
		}
	}

	@Test
	public void testDividedByZeroException2() throws StoppException {
		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Buffer<Integer> streamTwo = new Buffer<Integer>(100);
		Buffer<Integer> streamThree = new Buffer<Integer>(100);

		streamOne.put(35);
		streamTwo.put(35);
		streamThree.put(16);

		Subtract sub1 = new Subtract(streamOne, streamTwo);

		Divide div1 = new Divide(streamOne, sub1.getResults());

		try {
			assertEquals((Integer) 7, div1.getResults().get());
			fail();
		} catch (DivideByZeroException e) {
		}
	}

	@Test
	public void testMultiply1() throws StoppException, DivideByZeroException {

		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Buffer<Integer> streamTwo = new Buffer<Integer>(100);

		streamTwo.put(7);
		streamOne.put(5);

		Multiply mul1 = new Multiply(streamOne, streamTwo);

		assertEquals(35, mul1.getResults().get().intValue());
	}

	@Test
	public void testMultiply2() throws StoppException, DivideByZeroException {
		
		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Buffer<Integer> streamTwo = new Buffer<Integer>(100);
		
		streamTwo.put(1);
		streamOne.put(0);
		
		Multiply mul1 = new Multiply(streamOne, streamTwo);
		
		assertEquals(0, mul1.getResults().get().intValue());
	}
	
	
	
	@Test
	public void testMultiply3() throws StoppException, DivideByZeroException {
		
		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Buffer<Integer> streamTwo = new Buffer<Integer>(100);
		
		streamTwo.put(1);
		streamOne.put(35);
		
		Multiply mul1 = new Multiply(streamOne, streamTwo);
		
		assertEquals(35, mul1.getResults().get().intValue());
	}
	
	@Test
	public void testMultiply4() throws StoppException, DivideByZeroException {
		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Constant constant5 = new Constant(5);

		streamOne.put(5);
		streamOne.put(7);

		Multiply add1 = new Multiply(streamOne, constant5.getResults());
		assertEquals(25, add1.getResults().get().intValue());
		assertEquals(35, add1.getResults().get().intValue());
	}
	
	@Test
	public void testMultiplyNeg1() throws StoppException, DivideByZeroException {
		
		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Buffer<Integer> streamTwo = new Buffer<Integer>(100);
		
		streamTwo.put(-2);
		streamOne.put(35);
		
		Multiply mul1 = new Multiply(streamOne, streamTwo);
		
		assertEquals(-70, mul1.getResults().get().intValue());
	}
	
	@Test
	public void testMultiplyNeg2() throws StoppException, DivideByZeroException {
		
		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Buffer<Integer> streamTwo = new Buffer<Integer>(100);
		
		streamTwo.put(-1);
		streamOne.put(35);
		
		Multiply mul1 = new Multiply(streamOne, streamTwo);
		
		assertEquals(-35, mul1.getResults().get().intValue());
	}
	
	@Test
	public void testMultiplyNeg3() throws StoppException, DivideByZeroException {
		
		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Buffer<Integer> streamTwo = new Buffer<Integer>(100);
		
		streamTwo.put(1);
		streamOne.put(-35);
		
		Multiply mul1 = new Multiply(streamOne, streamTwo);
		
		assertEquals(-35, mul1.getResults().get().intValue());
	}
	
	@Test
	public void testMultiplyNeg4() throws StoppException, DivideByZeroException {
		
		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Buffer<Integer> streamTwo = new Buffer<Integer>(100);
		
		streamTwo.put(-1);
		streamOne.put(-35);
		
		Multiply mul1 = new Multiply(streamOne, streamTwo);
		
		assertEquals(35, mul1.getResults().get().intValue());
	}
	
	@Test
	public void testNegative1() throws StoppException, DivideByZeroException {
		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Constant constantNeg5 = new Constant(-5);

		streamOne.put(1);
		streamOne.put(2);
		streamOne.put(3);

		Add add1 = new Add(streamOne, constantNeg5.getResults()); // -4,-3,-2
		assertEquals(new Integer(-4), add1.getResults().get());
		assertEquals(new Integer(-3), add1.getResults().get());
		assertEquals(new Integer(-2), add1.getResults().get());
	}

	@Test
	public void testNegative2() throws StoppException, DivideByZeroException {
		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Constant constantNeg5 = new Constant(-5);
		Constant constantNeg1 = new Constant(-1);

		streamOne.put(1);
		streamOne.put(2);
		streamOne.put(3);
		streamOne.put(4);

		Add add1 = new Add(streamOne, constantNeg5.getResults()); // -4,-3,-2
		Multiply mul1 = new Multiply(add1.getResults(), constantNeg1.getResults()); // 4,3,2

		assertEquals(new Integer(4), mul1.getResults().get());
		assertEquals(new Integer(3), mul1.getResults().get());
		assertEquals(new Integer(2), mul1.getResults().get());
	}
	
	@Test
	public void testNoMoreVariablesAvailableException1() throws DivideByZeroException, StoppException {
		Buffer<Integer> streamAddOne = new Buffer<Integer>(100);
		Buffer<Integer> streamAddTwo = new Buffer<Integer>(100);
		Add x = new Add(streamAddOne, streamAddTwo);

		Buffer<Integer> streamAddThree = new Buffer<Integer>(100);
		Buffer<Integer> streamAddFour = new Buffer<Integer>(100);
		Add y = new Add(streamAddThree, streamAddFour);

		Multiply m = new Multiply(x.getResults(), y.getResults());

		Variable z = new Variable(m.getResults(), 2);

		streamAddOne.put(1);
		streamAddOne.put(2);
		streamAddOne.put(3);
		streamAddOne.put(4);
		streamAddOne.stopp();

		streamAddTwo.put(5);// 6
		streamAddTwo.put(6);// 8
		streamAddTwo.put(7);// 10
		streamAddTwo.put(8);// 12
		streamAddTwo.stopp();

		streamAddThree.put(9);
		streamAddThree.put(9);
		streamAddThree.put(8);
		streamAddThree.put(8);
		streamAddThree.stopp();

		streamAddFour.put(7);// 16
		streamAddFour.put(5);// 14
		streamAddFour.put(5);// 13
		streamAddFour.put(7);// 15
		streamAddFour.stopp();

		try {
			z.getResults();
			z.getResults();
		} catch (NoMoreVariablesAvailableException e) {
			fail();
		}

		try {
			z.getResults();
			fail();
		} catch (NoMoreVariablesAvailableException e) {
		}
	}
	
	
	
	@Test
	public void testParallel1() throws StoppException, DivideByZeroException {
		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Buffer<Integer> streamTwo = new Buffer<Integer>(100);
		Constant constant5 = new Constant(5);
		Constant constant4 = new Constant(4);

		streamOne.put(1);
		streamOne.put(2);
		streamOne.put(3);

		streamTwo.put(2);
		streamTwo.put(8);
		streamTwo.put(11);

		Add add1 = new Add(streamOne, constant5.getResults()); // 6,7,8
		Multiply mul1 = new Multiply(add1.getResults(), streamTwo); // 12,56,88
		Divide div1 = new Divide(mul1.getResults(), constant4.getResults()); // 3,14,22

		assertEquals((Integer) 3, div1.getResults().get());
		assertEquals((Integer) 14, div1.getResults().get());
		assertEquals((Integer) 22, div1.getResults().get());
	}

	@Test
	public void testParallel2() throws StoppException, DivideByZeroException {
		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Buffer<Integer> streamTwo = new Buffer<Integer>(100);
		Buffer<Integer> streamThree = new Buffer<Integer>(100);
		Constant constant5 = new Constant(5);

		streamOne.put(1);
		streamOne.put(2);
		streamOne.put(3);

		streamTwo.put(2);
		streamTwo.put(8);
		streamTwo.put(11);

		streamThree.put(1);
		streamThree.put(2);
		streamThree.put(3);

		Add add1 = new Add(streamOne, constant5.getResults()); // 6,7,8
		Multiply mul1 = new Multiply(streamTwo, streamThree); // 2,16,33
		Add add2 = new Add(add1.getResults(), mul1.getResults()); // 8,23,41
		assertEquals((Integer) 8, add2.getResults().get());
		assertEquals((Integer) 23, add2.getResults().get());
		assertEquals((Integer) 41, add2.getResults().get());
	}
	
	@Test
	public void testStop1() throws DivideByZeroException {

		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Buffer<Integer> streamTwo = new Buffer<Integer>(100);

		streamOne.stopp();
		streamTwo.put(9);

		Add add1 = new Add(streamOne, streamTwo);

		try {
			add1.getResults().get();
			fail();
		} catch (StoppException e1) {
		}
	}

	@Test
	public void testStop2() throws DivideByZeroException {

		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Buffer<Integer> streamTwo = new Buffer<Integer>(100);

		streamOne.put(1);
		streamOne.put(2);
		streamTwo.put(9);
		streamTwo.stopp();

		Add add1 = new Add(streamOne, streamTwo);

		try {
			add1.getResults().get();
		} catch (StoppException e1) {
		}

		try {
			add1.getResults().get();
			fail();
		} catch (StoppException e1) {
		}

	}

	@Test
	public void testStop3() throws DivideByZeroException {

		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Buffer<Integer> streamTwo = new Buffer<Integer>(100);

		streamOne.put(1);
		streamOne.stopp();
		streamTwo.put(9);
		streamTwo.stopp();

		Add add1 = new Add(streamOne, streamTwo);

		try {
			add1.getResults().get();
		} catch (StoppException e1) {
		}

		try {
			add1.getResults().get();
			fail();
		} catch (StoppException e1) {
		}
	}

	@Test
	public void testStop4() throws DivideByZeroException {

		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Buffer<Integer> streamTwo = new Buffer<Integer>(100);

		streamOne.stopp();
		streamOne.put(2);
		streamTwo.put(9);

		Add add1 = new Add(streamOne, streamTwo);

		try {
			add1.getResults().get();
			fail();
		} catch (StoppException e1) {
		}
	}

	@Test
	public void testSubtract1() throws StoppException, DivideByZeroException {
		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Constant constant5 = new Constant(5);

		streamOne.put(5);
		streamOne.put(7);

		Subtract add1 = new Subtract(streamOne, constant5.getResults());
		assertEquals((Integer) 0, add1.getResults().get());
		assertEquals((Integer) 2, add1.getResults().get());
	}
	
	@Test
	public void testSubtract2() throws StoppException, DivideByZeroException {

		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Buffer<Integer> streamTwo = new Buffer<Integer>(100);

		streamOne.put(35);
		streamTwo.put(34);

		Subtract sub1 = new Subtract(streamOne, streamTwo);

		assertEquals((Integer) 1, sub1.getResults().get());
	}
	
	@Test
	public void testSubtract3() throws StoppException, DivideByZeroException {

		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Buffer<Integer> streamTwo = new Buffer<Integer>(100);

		streamOne.put(34);
		streamTwo.put(35);

		Subtract sub1 = new Subtract(streamOne, streamTwo);

		assertEquals(-1, sub1.getResults().get().intValue());
	}
	
	@Test
	public void testSubtract4() throws StoppException, DivideByZeroException {

		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Buffer<Integer> streamTwo = new Buffer<Integer>(100);

		streamOne.put(34);
		streamTwo.put(0);

		Subtract sub1 = new Subtract(streamOne, streamTwo);

		assertEquals(34, sub1.getResults().get().intValue());
	}
	
	@Test
	public void testSubtract6() throws StoppException, DivideByZeroException {

		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Buffer<Integer> streamTwo = new Buffer<Integer>(100);

		streamOne.put(34);
		streamTwo.put(-6);

		Subtract sub1 = new Subtract(streamOne, streamTwo);

		assertEquals(40, sub1.getResults().get().intValue());
	}
	
	@Test
	public void testSubtract7() throws StoppException, DivideByZeroException {

		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Buffer<Integer> streamTwo = new Buffer<Integer>(100);

		streamOne.put(-6);
		streamTwo.put(6);

		Subtract sub1 = new Subtract(streamOne, streamTwo);

		assertEquals(-12, sub1.getResults().get().intValue());
	}
	
	@Test
	public void testSubtract8() throws StoppException, DivideByZeroException {

		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Buffer<Integer> streamTwo = new Buffer<Integer>(100);

		streamOne.put(-6);
		streamTwo.put(-6);

		Subtract sub1 = new Subtract(streamOne, streamTwo);

		assertEquals(0, sub1.getResults().get().intValue());
	}
	
	@Test
	public void testSubtract5() throws StoppException, DivideByZeroException {

		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Buffer<Integer> streamTwo = new Buffer<Integer>(100);

		streamOne.put(0);
		streamTwo.put(34);

		Subtract sub1 = new Subtract(streamOne, streamTwo);

		assertEquals(-34, sub1.getResults().get().intValue());
	}
	
	@Test
	public void variablenTest1() throws DivideByZeroException, StoppException, NoMoreVariablesAvailableException {
		Buffer<Integer> streamAddOne = new Buffer<Integer>(100);
		Buffer<Integer> streamAddTwo = new Buffer<Integer>(100);
		Add x = new Add(streamAddOne, streamAddTwo);

		Buffer<Integer> streamAddThree = new Buffer<Integer>(100);
		Buffer<Integer> streamAddFour = new Buffer<Integer>(100);
		Add y = new Add(streamAddThree, streamAddFour);

		Multiply m = new Multiply(x.getResults(), y.getResults());

		Variable z = new Variable(m.getResults(), 2);

		streamAddOne.put(1);
		streamAddOne.put(2);
		streamAddOne.put(3);
		streamAddOne.put(4);
		streamAddOne.stopp();

		streamAddTwo.put(5);// 6
		streamAddTwo.put(6);// 8
		streamAddTwo.put(7);// 10
		streamAddTwo.put(8);// 12
		streamAddTwo.stopp();

		streamAddThree.put(9);
		streamAddThree.put(9);
		streamAddThree.put(8);
		streamAddThree.put(8);
		streamAddThree.stopp();

		streamAddFour.put(7);// 16
		streamAddFour.put(5);// 14
		streamAddFour.put(5);// 13
		streamAddFour.put(7);// 15
		streamAddFour.stopp();

		Constant fuenf = new Constant(5);

		Add a1 = new Add(z.getResults(), fuenf.getResults());
		Multiply m1 = new Multiply(a1.getResults(), z.getResults());

		assertEquals(Integer.valueOf(9696), m1.getResults().get());
		assertEquals(Integer.valueOf(13104), m1.getResults().get());
		assertEquals(Integer.valueOf(17550), m1.getResults().get());
		assertEquals(Integer.valueOf(33300), m1.getResults().get());
	}

	@Test
	public void variablenTest2() throws DivideByZeroException, StoppException, NoMoreVariablesAvailableException {
		Buffer<Integer> streamAddOne = new Buffer<Integer>(100);
		Buffer<Integer> streamAddTwo = new Buffer<Integer>(100);
		
		streamAddOne.put(1);
		streamAddOne.put(2);
		streamAddOne.put(3);
		streamAddOne.put(4);
		streamAddOne.stopp();
		
		streamAddTwo.put(5);// 6
		streamAddTwo.put(6);// 8
		streamAddTwo.put(7);// 10
		streamAddTwo.put(8);// 12
		streamAddTwo.stopp();

		Add add1 = new Add(streamAddOne, streamAddTwo);

		Variable x = new Variable(add1.getResults(), 4);

		for (int i = 0; i < 4; i++) {
			Buffer<Integer> variableBuffer = x.getResults();
			assertEquals(6, variableBuffer.get().intValue());
			assertEquals(8, variableBuffer.get().intValue());
			assertEquals(10, variableBuffer.get().intValue());
			assertEquals(12, variableBuffer.get().intValue());
		}
		
		try {
			x.getResults();
			fail();
		} catch (NoMoreVariablesAvailableException e) {
		}
	}
	
	@Test
	public void variablenTest3() throws DivideByZeroException, NoMoreVariablesAvailableException, StoppException {
		Buffer<Integer> streamAddOne = new Buffer<Integer>(100);
		Buffer<Integer> streamAddTwo = new Buffer<Integer>(100);
		
		streamAddOne.put(1);
		streamAddOne.put(2);
		streamAddOne.put(3);
		streamAddOne.put(4);
		streamAddOne.stopp();
		
		streamAddTwo.put(5);// 6
		streamAddTwo.put(6);// 8
		streamAddTwo.put(7);// 10
		streamAddTwo.put(8);// 12
		streamAddTwo.stopp();

		Add add1 = new Add(streamAddOne, streamAddTwo);

		Variable x = new Variable(add1.getResults(), 4);

		for (int i = 0; i < 4; i++) {
			Buffer<Integer> variableBuffer = x.getResults();
			assertEquals(6, variableBuffer.get().intValue());
			assertEquals(8, variableBuffer.get().intValue());
			assertEquals(10, variableBuffer.get().intValue());
			assertEquals(12, variableBuffer.get().intValue());
			try {
				variableBuffer.get();
				fail();
			} catch (StoppException e) {
			}
		}
		
		try {
			x.getResults();
			fail();
		} catch (NoMoreVariablesAvailableException e) {
		}
	}
	
}
