package model;

import BufferAndLock.Buffer;
import BufferAndLock.Buffer.StoppException;
import exceptions.DivideByZeroException;

public final class Add extends Process {

	/**
	 * Creates an add process that adds all values of streamOne with the values of streamTwo in orderly sequence. 
	 * @param streamOne: represents the first input buffer.
	 * @param streamTwo: represents the second input buffer.
	 */
	public Add(Buffer<Integer> streamOne, Buffer<Integer> streamTwo) {
		super(streamOne, streamTwo);
		this.startThread();
	}

	@Override
	public void calculate() throws StoppException, DivideByZeroException {
		int result = this.getStreamOne().get() + this.getStreamTwo().get();
		this.getStreamResult().put(result);
	}

	@Override
	public Buffer<Integer> getResults() {
		return this.getStreamResult();
	}

}
