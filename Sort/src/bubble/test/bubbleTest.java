package bubble.test;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

import bubble.BubbleSort;
import bubble.Buffer;
import bubble.Buffer.StoppException;

public class bubbleTest {

	@Test
	public void bubbleTest1() throws StoppException {
		Buffer<Integer> inputBuffer = new Buffer<Integer>();
		BubbleSort<Integer> toSort = new BubbleSort<>(inputBuffer);

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
		Buffer<Comparable> inputBuffer = new Buffer<Comparable>();
		BubbleSort toSort = new BubbleSort(inputBuffer);

		inputBuffer.put(0);
		inputBuffer.put(2);
		inputBuffer.put(5);
		inputBuffer.stopp();

		assertEquals(0, toSort.getNextElement());
		assertEquals(2, toSort.getNextElement());
		assertEquals(5, toSort.getNextElement());
	}

	@Test
	public void bubbleTest3() throws StoppException {
		Buffer<Comparable> inputBuffer = new Buffer<Comparable>();
		BubbleSort toSort = new BubbleSort(inputBuffer);

		inputBuffer.put(0);
		inputBuffer.put(5);
		inputBuffer.put(2);
		inputBuffer.stopp();

		assertEquals(0, toSort.getNextElement());
		assertEquals(2, toSort.getNextElement());
		assertEquals(5, toSort.getNextElement());
	}

	@Test
	public void bubbleTest4() throws StoppException {
		Buffer<Comparable> inputBuffer = new Buffer<Comparable>();
		BubbleSort toSort = new BubbleSort(inputBuffer);

		inputBuffer.put(0);
		inputBuffer.put(2);
		inputBuffer.put(5);
		inputBuffer.put(3);
		inputBuffer.put(1);
		inputBuffer.put(1);
		inputBuffer.put(4);
		inputBuffer.stopp();

		assertEquals(0, toSort.getNextElement());
		assertEquals(1, toSort.getNextElement());
		assertEquals(1, toSort.getNextElement());
		assertEquals(2, toSort.getNextElement());
		assertEquals(3, toSort.getNextElement());
		assertEquals(4, toSort.getNextElement());
		assertEquals(5, toSort.getNextElement());
	}
	
	@Test
	public void bubbleTest5() throws StoppException {
		Buffer<Comparable> inputBuffer = new Buffer<Comparable>();
		BubbleSort toSort = new BubbleSort(inputBuffer);

		inputBuffer.put(0);
		inputBuffer.stopp();

		assertEquals(0, toSort.getNextElement());
	}
}
