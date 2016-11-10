package insertion;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import BufferAndLock.Buffer;
import BufferAndLock.Buffer.StoppException;
import BufferAndLock.DivideByZeroException;

public class InsertionTest {

	@Test
	public void test8Var() throws StoppException, DivideByZeroException {
		Buffer<Integer> inputBuffer = new Buffer<Integer>(20);

		inputBuffer.put(2);
		inputBuffer.put(3);

		inputBuffer.put(5);	
		inputBuffer.put(1);

		inputBuffer.put(0);
		inputBuffer.put(7);

		inputBuffer.put(9);
		inputBuffer.put(4);
		inputBuffer.stopp();

		InsertionSort<Integer> ms = new InsertionSort<>(inputBuffer);

		ms.startThread();

		assertEquals(0, ms.getResultBuffer().get().intValue());
		assertEquals(1, ms.getResultBuffer().get().intValue());
		assertEquals(2, ms.getResultBuffer().get().intValue());
		assertEquals(3, ms.getResultBuffer().get().intValue());
		assertEquals(4, ms.getResultBuffer().get().intValue());
		assertEquals(5, ms.getResultBuffer().get().intValue());
		assertEquals(7, ms.getResultBuffer().get().intValue());
		assertEquals(9, ms.getResultBuffer().get().intValue());
	}

	@Test
	public void test10Var() throws StoppException, DivideByZeroException {
		Buffer<Integer> inputBuffer = new Buffer<Integer>(20);

		inputBuffer.put(2);
		inputBuffer.put(3);
		inputBuffer.put(5);
		inputBuffer.put(1);
		inputBuffer.put(0);
		inputBuffer.put(7);
		inputBuffer.put(9);
		inputBuffer.put(4);
		inputBuffer.put(10);
		inputBuffer.put(7);
		inputBuffer.stopp();

		InsertionSort<Integer> ms = new InsertionSort<>(inputBuffer);

		ms.startThread();

		assertEquals(0, ms.getResultBuffer().get().intValue());
		assertEquals(1, ms.getResultBuffer().get().intValue());
		assertEquals(2, ms.getResultBuffer().get().intValue());
		assertEquals(3, ms.getResultBuffer().get().intValue());
		assertEquals(4, ms.getResultBuffer().get().intValue());
		assertEquals(5, ms.getResultBuffer().get().intValue());
		assertEquals(7, ms.getResultBuffer().get().intValue());
		assertEquals(7, ms.getResultBuffer().get().intValue());
		assertEquals(9, ms.getResultBuffer().get().intValue());
		assertEquals(10, ms.getResultBuffer().get().intValue());
	}

	@Test
	public void test15Var() throws StoppException, DivideByZeroException {
		Buffer<Integer> inputBuffer = new Buffer<Integer>(20);

		inputBuffer.put(2);
		inputBuffer.put(17);
		inputBuffer.put(101);
		inputBuffer.put(23);
		inputBuffer.put(2);
		inputBuffer.put(39);
		inputBuffer.put(31);
		inputBuffer.put(5);
		inputBuffer.put(18);
		inputBuffer.put(5);
		inputBuffer.put(18);
		inputBuffer.put(54);
		inputBuffer.put(81);
		inputBuffer.put(56);
		inputBuffer.put(100);
		inputBuffer.stopp();

		InsertionSort<Integer> ms = new InsertionSort<>(inputBuffer);

		ms.startThread();

		assertEquals(2, ms.getResultBuffer().get().intValue());
		assertEquals(2, ms.getResultBuffer().get().intValue());
		assertEquals(5, ms.getResultBuffer().get().intValue());
		assertEquals(5, ms.getResultBuffer().get().intValue());
		assertEquals(17, ms.getResultBuffer().get().intValue());
		assertEquals(18, ms.getResultBuffer().get().intValue());
		assertEquals(18, ms.getResultBuffer().get().intValue());
		assertEquals(23, ms.getResultBuffer().get().intValue());
		assertEquals(31, ms.getResultBuffer().get().intValue());
		assertEquals(39, ms.getResultBuffer().get().intValue());
		assertEquals(54, ms.getResultBuffer().get().intValue());
		assertEquals(56, ms.getResultBuffer().get().intValue());
		assertEquals(81, ms.getResultBuffer().get().intValue());
		assertEquals(100, ms.getResultBuffer().get().intValue());
		assertEquals(101, ms.getResultBuffer().get().intValue());
	}

	@Test
	public void test1Var() throws StoppException, DivideByZeroException {
		Buffer<Integer> inputBuffer = new Buffer<Integer>(20);

		inputBuffer.put(2);
		inputBuffer.stopp();

		InsertionSort<Integer> ms = new InsertionSort<>(inputBuffer);

		ms.startThread();

		assertEquals(2, ms.getResultBuffer().get().intValue());
	}

	@Test
	public void test2VarSorted() throws StoppException, DivideByZeroException {
		Buffer<Integer> inputBuffer = new Buffer<Integer>(20);

		inputBuffer.put(2);
		inputBuffer.put(3);
		inputBuffer.stopp();

		InsertionSort<Integer> ms = new InsertionSort<>(inputBuffer);

		ms.startThread();

		assertEquals(2, ms.getResultBuffer().get().intValue());
		assertEquals(3, ms.getResultBuffer().get().intValue());
	}

	@Test
	public void test2VarUnsorted() throws StoppException, DivideByZeroException {
		Buffer<Integer> inputBuffer = new Buffer<Integer>(20);

		inputBuffer.put(3);
		inputBuffer.put(2);
		inputBuffer.stopp();

		InsertionSort<Integer> ms = new InsertionSort<>(inputBuffer);

		ms.startThread();

		assertEquals(2, ms.getResultBuffer().get().intValue());
		assertEquals(3, ms.getResultBuffer().get().intValue());
	}

	@Test
	public void test3Var() throws StoppException, DivideByZeroException {
		Buffer<Integer> inputBuffer = new Buffer<Integer>(20);

		inputBuffer.put(3);
		inputBuffer.put(2);
		inputBuffer.put(0);
		inputBuffer.stopp();

		InsertionSort<Integer> ms = new InsertionSort<>(inputBuffer);

		ms.startThread();

		assertEquals(0, ms.getResultBuffer().get().intValue());
		assertEquals(2, ms.getResultBuffer().get().intValue());
		assertEquals(3, ms.getResultBuffer().get().intValue());
	}

	@Test
	public void test4Var() throws StoppException, DivideByZeroException {
		Buffer<Integer> inputBuffer = new Buffer<Integer>(20);

		inputBuffer.put(3);
		inputBuffer.put(2);
		inputBuffer.put(9);
		inputBuffer.put(0);
		inputBuffer.stopp();

		InsertionSort<Integer> ms = new InsertionSort<>(inputBuffer);

		ms.startThread();

		assertEquals(0, ms.getResultBuffer().get().intValue());
		assertEquals(2, ms.getResultBuffer().get().intValue());
		assertEquals(3, ms.getResultBuffer().get().intValue());
		assertEquals(9, ms.getResultBuffer().get().intValue());

	}

	@Test
	public void test5Var() throws StoppException, DivideByZeroException {
		Buffer<Integer> inputBuffer = new Buffer<Integer>(20);

		inputBuffer.put(3);
		inputBuffer.put(2);
		inputBuffer.put(9);
		inputBuffer.put(0);
		inputBuffer.put(5);
		inputBuffer.stopp();

		InsertionSort<Integer> ms = new InsertionSort<>(inputBuffer);

		ms.startThread();

		assertEquals(0, ms.getResultBuffer().get().intValue());
		assertEquals(2, ms.getResultBuffer().get().intValue());
		assertEquals(3, ms.getResultBuffer().get().intValue());
		assertEquals(5, ms.getResultBuffer().get().intValue());
		assertEquals(9, ms.getResultBuffer().get().intValue());

	}
}
