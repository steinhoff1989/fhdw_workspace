package model;

import BufferAndLock.Buffer;
import BufferAndLock.Buffer.StoppException;
import exceptions.DivideByZeroException;

public class Divide extends Process {

	/**
	 * Creates a divide process that divides all values of streamOne by the values of streamTwo in orderly sequence. 
	 * @param streamOne: represents the first input buffer.
	 * @param streamTwo: represents the second input buffer.
	 */
	public Divide(Buffer<Integer> streamOne, Buffer<Integer> streamTwo) {
		super(streamOne, streamTwo);
		this.startThread();
	}

	@Override
	public void calculate() throws DivideByZeroException, StoppException {
		int divisor = this.getStreamTwo().get();
		if (divisor == 0) {
			throw new DivideByZeroException();
		}

		int result = this.getStreamOne().get() / divisor;
		this.getStreamResult().put(result);
	}

	@Override
	public Buffer<Integer> getResults() {
		return this.getStreamResult();
	}
}
