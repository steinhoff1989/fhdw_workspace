package model;

import BufferAndLock.Buffer;
import BufferAndLock.Buffer.StoppException;
import exceptions.DivideByZeroException;

public class Multiply extends Process {

	/**
	 * Creates a multiply process that multiplies all values of streamOne by the values of streamTwo in orderly sequence. 
	 * @param streamOne: represents the first input buffer.
	 * @param streamTwo: represents the second input buffer.
	 */
	public Multiply(Buffer<Integer> streamOne, Buffer<Integer> streamTwo) {
		super(streamOne, streamTwo);
		this.startThread();
	}

	@Override
	public void calculate() throws DivideByZeroException, StoppException {
		int result = this.getStreamOne().get() * this.getStreamTwo().get();
		this.getStreamResult().put(result);
	}

	@Override
	public Buffer<Integer> getResults() {
		return this.getStreamResult();
	}

}
