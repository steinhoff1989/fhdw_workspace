package merge;

import static org.junit.Assert.*;

import org.junit.Test;

import merge.Buffer;
import merge.Buffer.StoppException;
import merge.Sort;

public class MergeTest {

	@Test
	public void test8Var() throws StoppException {
		Buffer<Integer> inputBuffer = new Buffer<Integer>();
		
		inputBuffer.put(2);
		inputBuffer.put(3);
		inputBuffer.put(5);
		inputBuffer.put(1);
		inputBuffer.put(0);
		inputBuffer.put(7);
		inputBuffer.put(9);
		inputBuffer.put(4);
		inputBuffer.stopp();
		
		Sort<Integer> ms = new Sort<>(inputBuffer);
		
		ms.calculate();
		
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
	public void test1Var() throws StoppException {
		Buffer<Integer> inputBuffer = new Buffer<Integer>();
		
		inputBuffer.put(2);
		inputBuffer.stopp();
		
		Sort<Integer> ms = new Sort<>(inputBuffer);
		
		ms.calculate();
		
		assertEquals(2, ms.getResultBuffer().get().intValue());
	}
	
	@Test
	public void test2VarSorted() throws StoppException {
		Buffer<Integer> inputBuffer = new Buffer<Integer>();
		
		inputBuffer.put(2);
		inputBuffer.put(3);
		inputBuffer.stopp();
		
		Sort<Integer> ms = new Sort<>(inputBuffer);
		
		ms.calculate();
		
		assertEquals(2, ms.getResultBuffer().get().intValue());
		assertEquals(3, ms.getResultBuffer().get().intValue());
	}
	
	@Test
	public void test2VarUnsorted() throws StoppException {
		Buffer<Integer> inputBuffer = new Buffer<Integer>();
		
		inputBuffer.put(3);
		inputBuffer.put(2);
		inputBuffer.stopp();
		
		Sort<Integer> ms = new Sort<>(inputBuffer);
		
		ms.calculate();
		
		assertEquals(2, ms.getResultBuffer().get().intValue());
		assertEquals(3, ms.getResultBuffer().get().intValue());
	}
	@Test
	public void test3Var() throws StoppException {
		Buffer<Integer> inputBuffer = new Buffer<Integer>();
		
		inputBuffer.put(3);
		inputBuffer.put(2);
		inputBuffer.put(0);
		inputBuffer.stopp();
		
		Sort<Integer> ms = new Sort<>(inputBuffer);
		
		ms.calculate();
		
		assertEquals(0, ms.getResultBuffer().get().intValue());
		assertEquals(2, ms.getResultBuffer().get().intValue());
		assertEquals(3, ms.getResultBuffer().get().intValue());
	}
	@Test
	public void test4Var() throws StoppException {
		Buffer<Integer> inputBuffer = new Buffer<Integer>();
		
		inputBuffer.put(3);
		inputBuffer.put(2);
		inputBuffer.put(9);
		inputBuffer.put(0);
		inputBuffer.stopp();
		
		Sort<Integer> ms = new Sort<>(inputBuffer);
		
		ms.calculate();
		
		assertEquals(0, ms.getResultBuffer().get().intValue());
		assertEquals(2, ms.getResultBuffer().get().intValue());
		assertEquals(3, ms.getResultBuffer().get().intValue());
		assertEquals(9, ms.getResultBuffer().get().intValue());

	}
	@Test
	public void test5Var() throws StoppException {
		Buffer<Integer> inputBuffer = new Buffer<Integer>();
		
		inputBuffer.put(3);
		inputBuffer.put(2);
		inputBuffer.put(9);
		inputBuffer.put(0);
		inputBuffer.put(5);
		inputBuffer.stopp();
		
		Sort<Integer> ms = new Sort<>(inputBuffer);
		
		ms.calculate();
		
		assertEquals(0, ms.getResultBuffer().get().intValue());
		assertEquals(2, ms.getResultBuffer().get().intValue());
		assertEquals(3, ms.getResultBuffer().get().intValue());
		assertEquals(5, ms.getResultBuffer().get().intValue());
		assertEquals(9, ms.getResultBuffer().get().intValue());

	}
}
