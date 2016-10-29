package model;

import BufferAndLock.Buffer;
import BufferAndLock.Buffer.StoppException;
import exceptions.DivideByZeroException;

public class Constant extends Process {

	private int value;

	/**
	 * Creates a constant process that saves a constant value to be reused.
	 * @param value: represents the integer value of this constant.
	 */
	public Constant(int value) {
		super(new Buffer<Integer>(0), new Buffer<Integer>(0));
		this.value = value;
		this.startThread();
	}

	@Override
	public void calculate() throws DivideByZeroException, StoppException {
		this.getStreamResult().put(value);
	}

	@Override
	public Buffer<Integer> getResults() {
		return this.getStreamResult();
	}
}
