package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import model.Add;
import model.Buffer;
import model.Buffer.StoppException;
import model.Constant;
import model.Divide;
import model.DivideByZeroException;
import model.Multiply;
import model.Vervielfältiger;
import model.Subtract;

public class Testcase {

	@Test
	public void testStop1() throws DivideByZeroException {

		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Buffer<Integer> streamTwo = new Buffer<Integer>(100);

		streamOne.stopp();
		streamTwo.put(9);

		Add add1 = new Add(streamOne, streamTwo);

		try {
			add1.getStreamResult().get();
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
			add1.getStreamResult().get();
		} catch (StoppException e1) {
		}
		
		try {
			add1.getStreamResult().get();
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
			add1.getStreamResult().get();
		} catch (StoppException e1) {
		}
		
		try {
			add1.getStreamResult().get();
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
			add1.getStreamResult().get();
			fail();
		} catch (StoppException e1) {
		}
	}
	
	@Test
	public void testAddSimple() throws StoppException, DivideByZeroException {

		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Buffer<Integer> streamTwo = new Buffer<Integer>(100);

		streamTwo.put(7);
		streamOne.put(5);

		Add add1 = new Add(streamOne, streamTwo);
		assertEquals((Integer) 12, add1.getStreamResult().get());
	}

	@Test
	public void testAddSimple2() throws StoppException, DivideByZeroException {

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
		Subtract sub1 = new Subtract(add1.getStreamResult(), add2.getStreamResult());

		assertEquals((Integer) 7, sub1.getStreamResult().get());
	}


	@Test
	public void testMultiplySimple() throws StoppException, DivideByZeroException {

		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Buffer<Integer> streamTwo = new Buffer<Integer>(100);

		streamTwo.put(7);
		streamOne.put(5);

		Multiply mul1 = new Multiply(streamOne, streamTwo);

		assertEquals((Integer) 35, mul1.getStreamResult().get());

	}

	@Test
	public void testDivideSimple() throws StoppException, DivideByZeroException {

		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Buffer<Integer> streamTwo = new Buffer<Integer>(100);

		streamOne.put(35);
		streamTwo.put(5);

		Divide div1 = new Divide(streamOne, streamTwo);

		assertEquals((Integer) 7, div1.getStreamResult().get());
	}

	@Test
	public void testDividedByZeroException1() throws StoppException {

		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Buffer<Integer> streamTwo = new Buffer<Integer>(100);

		streamOne.put(35);
		streamTwo.put(0);

		Divide div1 = new Divide(streamOne, streamTwo);

		try {
			assertEquals((Integer) 7, div1.getStreamResult().get());
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
		
		Divide div1 = new Divide(streamOne, sub1.getStreamResult());
		
		try {
			assertEquals((Integer) 7, div1.getStreamResult().get());
			fail();
		} catch (DivideByZeroException e) {
		}
	}
	
	@Test
	public void testSubtractSimple() throws StoppException, DivideByZeroException {

		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Buffer<Integer> streamTwo = new Buffer<Integer>(100);

		streamOne.put(35);
		streamTwo.put(34);

		Subtract sub1 = new Subtract(streamOne, streamTwo);

		assertEquals((Integer) 1, sub1.getStreamResult().get());
	}

	@Test
	public void testAddConstant1() throws StoppException, DivideByZeroException {
		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Constant constant5 = new Constant(5);

		streamOne.put(5);
		streamOne.put(7);

		Add add1 = new Add(streamOne, constant5.getStreamResult());
		assertEquals((Integer) 10, add1.getStreamResult().get());
		assertEquals((Integer) 12, add1.getStreamResult().get());
	}
	
	@Test
	public void testAddConstant2() throws StoppException, DivideByZeroException {
		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Constant constant5 = new Constant(5);

		streamOne.put(5);
		streamOne.put(7);
		streamOne.put(9);

		Add add1 = new Add(streamOne, constant5.getStreamResult());
		assertEquals((Integer) 10, add1.getStreamResult().get());
		assertEquals((Integer) 12, add1.getStreamResult().get());
		assertEquals((Integer) 14, add1.getStreamResult().get());
	}
	
//	@Test
//	public void testAddConstant3() throws StoppException, DivideByZeroException {
//		Buffer<Integer> streamOne = new Buffer<Integer>(100);
//		Buffer<Integer> streamTwo = new Buffer<Integer>(100);
//		
//		streamOne.put(4);
//		streamOne.put(5);
//		streamOne.put(6);
//		streamTwo.put(1);
//		streamTwo.put(2);
//		streamTwo.put(3);
//		
//		Multiply mul1 = new Multiply(streamOne, streamTwo);
//
//		Constant constant5 = new Constant(5);
//		SavedResult savedBuffer = new SavedResult(mul1);
//		
//		Add add1 = new Add(savedBuffer.getResultStream(), constant5);
//		Multiply mul2 = new Multiply(add1, savedBuffer);
//		
//
//
//		assertEquals((Integer) 10, add1.getStreamResult().get());
//		assertEquals((Integer) 12, add1.getStreamResult().get());
//		assertEquals((Integer) 14, add1.getStreamResult().get());
//	}
	
	@Test
	public void testSubtractConstant1() throws StoppException, DivideByZeroException {
		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Constant constant5 = new Constant(5);

		streamOne.put(5);
		streamOne.put(7);

		Subtract add1 = new Subtract(streamOne, constant5.getStreamResult());
		assertEquals((Integer) 0, add1.getStreamResult().get());
		assertEquals((Integer) 2, add1.getStreamResult().get());
	}
	
	@Test
	public void testMultiplyConstant1() throws StoppException, DivideByZeroException {
		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Constant constant5 = new Constant(5);

		streamOne.put(5);
		streamOne.put(7);

		Multiply add1 = new Multiply(streamOne, constant5.getStreamResult());
		assertEquals((Integer) 25, add1.getStreamResult().get());
		assertEquals((Integer) 35, add1.getStreamResult().get());
	}
	
	@Test
	public void testDivideConstant1() throws StoppException, DivideByZeroException {
		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Constant constant5 = new Constant(5);

		streamOne.put(5);
		streamOne.put(7);
		streamOne.put(29);

		Divide add1 = new Divide(streamOne, constant5.getStreamResult());
		assertEquals((Integer) 1, add1.getStreamResult().get());
		assertEquals((Integer) 1, add1.getStreamResult().get());
		assertEquals((Integer) 5, add1.getStreamResult().get());
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
				
		Add add1 = new Add(streamOne, constant5.getStreamResult()); //6,7,8
		Multiply mul1 = new Multiply(add1.getStreamResult(), streamTwo); //12,56,88
		Divide div1 = new Divide(mul1.getStreamResult(), constant4.getStreamResult()); //3,14,22
		assertEquals((Integer) 3, div1.getStreamResult().get());
		assertEquals((Integer) 14, div1.getStreamResult().get());
		assertEquals((Integer) 22, div1.getStreamResult().get());
	}
	
//	@Test
//	public void testParallel2() throws StoppException {
//		Buffer<Integer> streamOne = new Buffer<Integer>(100);
//		Buffer<Integer> streamTwo = new Buffer<Integer>(100);
//		Constant constant5 = new Constant(5);
//
//		streamOne.put(1);
//		streamOne.put(2);
//		streamOne.put(3);
//
//		streamTwo.put(2);
//		streamTwo.put(8);
//		streamTwo.put(11);
//				
//		Add add1 = new Add(streamOne, constant5.getStreamResult()); //6,7,8
//		Multiply mul1 = new Multiply(add1.getStreamResult(), streamTwo); //12,56,88
//		Add add2 = new Add(add1.getStreamResult(), mul1.getStreamResult()); //18,63,96
//		assertEquals((Integer) 18, add2.getStreamResult().get());
//		assertEquals((Integer) 63, add2.getStreamResult().get());
//		assertEquals((Integer) 96, add2.getStreamResult().get());
//	}
	
	@Test
	public void testParallel3() throws StoppException, DivideByZeroException {
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

		Add add1 = new Add(streamOne, constant5.getStreamResult()); //6,7,8
		Multiply mul1 = new Multiply(streamTwo, streamThree); //2,16,33
		Add add2 = new Add(add1.getStreamResult(), mul1.getStreamResult()); //8,23,41
		assertEquals((Integer) 8, add2.getStreamResult().get());
		assertEquals((Integer) 23, add2.getStreamResult().get());
		assertEquals((Integer) 41, add2.getStreamResult().get());
	}
	
	@Test
	public void testNegative1() throws StoppException, DivideByZeroException {
		Buffer<Integer> streamOne = new Buffer<Integer>(100);
		Constant constantNeg5 = new Constant(-5);

		streamOne.put(1);
		streamOne.put(2);
		streamOne.put(3);

		Add add1 = new Add(streamOne, constantNeg5.getStreamResult()); //-4,-3,-2
		assertEquals(new Integer(-4), add1.getStreamResult().get());
		assertEquals(new Integer(-3), add1.getStreamResult().get());
		assertEquals(new Integer(-2), add1.getStreamResult().get());
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

		Add add1 = new Add(streamOne, constantNeg5.getStreamResult()); //-4,-3,-2
		Multiply mul1 = new Multiply(add1.getStreamResult(), constantNeg1.getStreamResult()); //4,3,2

		assertEquals(new Integer(4), mul1.getStreamResult().get());
		assertEquals(new Integer(3), mul1.getStreamResult().get());
		assertEquals(new Integer(2), mul1.getStreamResult().get());
	}
	
	@Test
	public void zwischenergebnisTest1() throws DivideByZeroException, StoppException {
		Buffer<Integer> streamAddOne = new Buffer<Integer>(100);
		Buffer<Integer> streamAddTwo = new Buffer<Integer>(100);
		Add x = new Add(streamAddOne,streamAddTwo);
		
		Buffer<Integer> streamAddThree = new Buffer<Integer>(100);
		Buffer<Integer> streamAddFour = new Buffer<Integer>(100);
		Add y = new Add(streamAddThree,streamAddFour);
		
		Multiply m = new Multiply(x.getStreamResult(),y.getStreamResult());
		
		Vervielfältiger z = new Vervielfältiger(m.getStreamResult(), 2);
				
		streamAddOne.put(1);
		streamAddOne.put(2);
		streamAddOne.put(3);
		streamAddOne.put(4);
		streamAddOne.stopp();
		
		streamAddTwo.put(5);//6
		streamAddTwo.put(6);//8
		streamAddTwo.put(7);//10
		streamAddTwo.put(8);//12
		streamAddTwo.stopp();
		
		streamAddThree.put(9);
		streamAddThree.put(9);
		streamAddThree.put(8);
		streamAddThree.put(8);
		streamAddThree.stopp();
		
		streamAddFour.put(7);//16
		streamAddFour.put(5);//14
		streamAddFour.put(5);//13
		streamAddFour.put(7);//15
		streamAddFour.stopp();
		
		Constant fuenf = new Constant(5);
		
		Add a1 = new Add(z.getBufferCopy(0),fuenf.getStreamResult());
		Multiply m1 = new Multiply(a1.getStreamResult(),z.getBufferCopy(1));
		
		assertEquals(Integer.valueOf(9696), m1.getStreamResult().get());
		assertEquals(Integer.valueOf(13104), m1.getStreamResult().get());
		assertEquals(Integer.valueOf(17550), m1.getStreamResult().get());
		assertEquals(Integer.valueOf(33300), m1.getStreamResult().get());
	}
}
