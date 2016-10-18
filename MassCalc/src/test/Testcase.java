package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import model.AbstractBuffer.StoppException;
import model.Add;
import model.Buffer;
import model.BufferConstant;
import model.Constant;
import model.Divide;
import model.Multiply;
import model.Subtract;

public class Testcase {

	@Test
	public void testStop() {

		Buffer<Integer> streamOne = new Buffer<Integer>();
		Buffer<Integer> streamTwo = new Buffer<Integer>();

		streamOne.stopp();
		streamTwo.put(9);
		// streamTwo.put(7);
		// streamOne.put(5);

		Add add1 = new Add(streamOne, streamTwo);

		try {
			add1.getStreamResult().get();
			fail();
		} catch (StoppException e1) {
		}

	}

	@Test
	public void testAddSimple() throws StoppException {

		Buffer<Integer> streamOne = new Buffer<Integer>();
		Buffer<Integer> streamTwo = new Buffer<Integer>();

		streamTwo.put(7);
		streamOne.put(5);

		Add add1 = new Add(streamOne, streamTwo);
		assertEquals((Integer) 12, add1.getStreamResult().get());
	}

	@Test
	public void testAddSimple2() throws StoppException {

		Buffer<Integer> streamOne = new Buffer<Integer>();
		Buffer<Integer> streamTwo = new Buffer<Integer>();
		Buffer<Integer> streamThree = new Buffer<Integer>();
		Buffer<Integer> streamFour = new Buffer<Integer>();

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
	public void testMultiplySimple() throws StoppException {

		Buffer<Integer> streamOne = new Buffer<Integer>();
		Buffer<Integer> streamTwo = new Buffer<Integer>();

		streamTwo.put(7);
		streamOne.put(5);

		Multiply mul1 = new Multiply(streamOne, streamTwo);

		assertEquals((Integer) 35, mul1.getStreamResult().get());

	}

	@Test
	public void testDivideSimple() throws StoppException {

		Buffer<Integer> streamOne = new Buffer<Integer>();
		Buffer<Integer> streamTwo = new Buffer<Integer>();

		streamOne.put(35);
		streamTwo.put(5);

		Divide div1 = new Divide(streamOne, streamTwo);

		assertEquals((Integer) 7, div1.getStreamResult().get());

	}

	@Test
	public void testSubtractSimple() throws StoppException {

		Buffer<Integer> streamOne = new Buffer<Integer>();
		Buffer<Integer> streamTwo = new Buffer<Integer>();

		streamOne.put(35);
		streamTwo.put(34);

		Subtract sub1 = new Subtract(streamOne, streamTwo);

		assertEquals((Integer) 1, sub1.getStreamResult().get());
	}

	@Test
	public void testAddConstant1() throws StoppException {
		Buffer<Integer> streamOne = new Buffer<Integer>();
		Constant constant5 = new Constant(5);

		streamOne.put(5);
		streamOne.put(7);

		Add add1 = new Add(streamOne, constant5.getStreamResult());
		assertEquals((Integer) 10, add1.getStreamResult().get());
		assertEquals((Integer) 12, add1.getStreamResult().get());
	}
}
