package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Add;
import model.Buffer;
import model.Buffer.StoppException;

public class Testcase {

	@Test
	public void test() throws StoppException {

		Buffer<Integer> streamOne = new Buffer<Integer>();
		Buffer<Integer> streamTwo = new Buffer<Integer>();

		streamTwo.put(7);
		streamOne.put(5);

		Add add1 = new Add(streamOne, streamTwo);
		assertEquals((Integer) 12, add1.getStreamResult().get());
	}

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

}
