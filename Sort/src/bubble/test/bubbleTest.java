package bubble.test;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.Test;

import bubble.BubbleSort;
import bubble.Buffer;
import bubble.Buffer.StoppException;

public class bubbleTest {

	@Test
	public void bubbleTest1(){
		
		Buffer<Comparable> inputBuffer = new Buffer<Comparable>();
		
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
		
		BubbleSort toSort = new BubbleSort(inputBuffer);
		toSort.sort();
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			assertEquals(0,toSort.getOutputBuffer().get());
			assertEquals(0,toSort.getOutputBuffer().get());
			assertEquals(1,toSort.getOutputBuffer().get());
			assertEquals(2,toSort.getOutputBuffer().get());
			assertEquals(3,toSort.getOutputBuffer().get());
			assertEquals(4,toSort.getOutputBuffer().get());
			assertEquals(4,toSort.getOutputBuffer().get());
			assertEquals(5,toSort.getOutputBuffer().get());
			assertEquals(6,toSort.getOutputBuffer().get());
			assertEquals(7,toSort.getOutputBuffer().get());
			assertEquals(8,toSort.getOutputBuffer().get());
			assertEquals(9,toSort.getOutputBuffer().get());
		} catch (StoppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void bubbleTest2(){
		
		Buffer<Comparable> inputBuffer = new Buffer<Comparable>();
		BubbleSort toSort = new BubbleSort(inputBuffer);
		
		inputBuffer.put(0);
		inputBuffer.put(2);
		inputBuffer.put(5);
		inputBuffer.stopp();
		
		toSort.sort();
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			assertEquals(0,toSort.getNextElement());
			assertEquals(2,toSort.getNextElement());
			assertEquals(5,toSort.getNextElement());
		} catch (StoppException e) {
			System.out.println("stopped");
		}
	}
	
	@Test
	public void bubbleTest3(){
		Buffer<Comparable> inputBuffer = new Buffer<Comparable>();
		
		inputBuffer.put(0);
		inputBuffer.put(5);
		inputBuffer.put(2);
		inputBuffer.stopp();
		
		BubbleSort toSort = new BubbleSort(inputBuffer);
		toSort.sort();
		
		try {
			assertEquals(0,toSort.getOutputBuffer().get());
			assertEquals(2,toSort.getOutputBuffer().get());
			assertEquals(5,toSort.getOutputBuffer().get());
		} catch (StoppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void bubbleTest4(){
		Buffer<Comparable> inputBuffer = new Buffer<Comparable>();
		BubbleSort toSort = new BubbleSort(inputBuffer);
		
		inputBuffer.put(0);
		inputBuffer.put(2);
		inputBuffer.put(5);
		inputBuffer.put(3);
		inputBuffer.put(1);
		inputBuffer.put(4);
		inputBuffer.stopp();
		
		toSort.sort();
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			assertEquals(0,toSort.getOutputBuffer().get());
			assertEquals(1,toSort.getOutputBuffer().get());
			assertEquals(2,toSort.getOutputBuffer().get());
			assertEquals(3,toSort.getOutputBuffer().get());
			assertEquals(4,toSort.getOutputBuffer().get());
			assertEquals(5,toSort.getOutputBuffer().get());
		} catch (StoppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
}
