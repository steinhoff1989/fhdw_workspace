package bubble;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import BufferAndLock.Buffer;
import BufferAndLock.Buffer.StoppException;

public class BubbleTest {

	@Test
	public void bubbleTest1() throws StoppException {
		final Buffer<Integer> inputBuffer = new Buffer<Integer>();
		final BubbleSort<Integer> toSort = new BubbleSort<>(inputBuffer);

		inputBuffer.put(0);
		inputBuffer.put(2);
		inputBuffer.put(5);
		inputBuffer.put(3);
		inputBuffer.put(1);
		inputBuffer.put(0);
		inputBuffer.put(4);
		inputBuffer.put(9);
		inputBuffer.put(6);
		inputBuffer.put(8);
		inputBuffer.put(7);
		inputBuffer.put(4);
		inputBuffer.stopp();

		assertEquals(Integer.valueOf(0), toSort.getNextElement());
		assertEquals(0, toSort.getNextElement().intValue());
		assertEquals(1, toSort.getNextElement().intValue());
		assertEquals(2, toSort.getNextElement().intValue());
		assertEquals(3, toSort.getNextElement().intValue());
		assertEquals(4, toSort.getNextElement().intValue());
		assertEquals(4, toSort.getNextElement().intValue());
		assertEquals(5, toSort.getNextElement().intValue());
		assertEquals(6, toSort.getNextElement().intValue());
		assertEquals(7, toSort.getNextElement().intValue());
		assertEquals(8, toSort.getNextElement().intValue());
		assertEquals(9, toSort.getNextElement().intValue());

	}

	@Test
	public void bubbleTest2() throws StoppException {
		final Buffer<Integer> inputBuffer = new Buffer<Integer>();
		final BubbleSort<Integer> toSort = new BubbleSort<Integer>(inputBuffer);

		inputBuffer.put(0);
		inputBuffer.put(2);
		inputBuffer.put(5);
		inputBuffer.stopp();

		assertEquals(0, toSort.getNextElement().intValue());
		assertEquals(2, toSort.getNextElement().intValue());
		assertEquals(5, toSort.getNextElement().intValue());
	}

	@Test
	public void bubbleTest3() throws StoppException {
		final Buffer<Integer> inputBuffer = new Buffer<Integer>();
		final BubbleSort<Integer> toSort = new BubbleSort<Integer>(inputBuffer);

		inputBuffer.put(0);
		inputBuffer.put(5);
		inputBuffer.put(2);
		inputBuffer.stopp();

		assertEquals(0, toSort.getNextElement().intValue());
		assertEquals(2, toSort.getNextElement().intValue());
		assertEquals(5, toSort.getNextElement().intValue());
	}

	@Test
	public void bubbleTest4() throws StoppException {
		final Buffer<Integer> inputBuffer = new Buffer<Integer>();
		final BubbleSort<Integer> toSort = new BubbleSort<Integer>(inputBuffer);

		inputBuffer.put(0);
		inputBuffer.put(2);
		inputBuffer.put(5);
		inputBuffer.put(3);
		inputBuffer.put(1);
		inputBuffer.put(1);
		inputBuffer.put(4);
		inputBuffer.stopp();

		assertEquals(0, toSort.getNextElement().intValue());
		assertEquals(1, toSort.getNextElement().intValue());
		assertEquals(1, toSort.getNextElement().intValue());
		assertEquals(2, toSort.getNextElement().intValue());
		assertEquals(3, toSort.getNextElement().intValue());
		assertEquals(4, toSort.getNextElement().intValue());
		assertEquals(5, toSort.getNextElement().intValue());
	}

	@Test
	public void bubbleTest5() throws StoppException {
		final Buffer<Integer> inputBuffer = new Buffer<Integer>();
		final BubbleSort<Integer> toSort = new BubbleSort<Integer>(inputBuffer);

		inputBuffer.put(0);
		inputBuffer.stopp();

		assertEquals(0, toSort.getNextElement().intValue());
	}

	@Test
	public void test15Var() throws StoppException {
		final Buffer<Integer> inputBuffer = new Buffer<Integer>();

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

		final BubbleSort<Integer> bs = new BubbleSort<>(inputBuffer);

		assertEquals(2, bs.getNextElement().intValue());
		assertEquals(2, bs.getNextElement().intValue());
		assertEquals(5, bs.getNextElement().intValue());
		assertEquals(5, bs.getNextElement().intValue());
		assertEquals(17, bs.getNextElement().intValue());
		assertEquals(18, bs.getNextElement().intValue());
		assertEquals(18, bs.getNextElement().intValue());
		assertEquals(23, bs.getNextElement().intValue());
		assertEquals(31, bs.getNextElement().intValue());
		assertEquals(39, bs.getNextElement().intValue());
		assertEquals(54, bs.getNextElement().intValue());
		assertEquals(56, bs.getNextElement().intValue());
		assertEquals(81, bs.getNextElement().intValue());
		assertEquals(100, bs.getNextElement().intValue());
		assertEquals(101, bs.getNextElement().intValue());
	}
}
