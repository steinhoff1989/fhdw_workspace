package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Add;
import model.Buffer;
import model.Buffer.StoppException;
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
		streamTwo.put(7);
		streamOne.put(5);
		
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
}
