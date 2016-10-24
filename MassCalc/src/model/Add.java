package model;

import model.Buffer.StoppException;

public final class Add extends Process {

	public Add(Buffer<Integer> streamOne, Buffer<Integer> streamTwo) {
		super(streamOne, streamTwo);
		this.startThread();
	}

	@Override
	public void calculate() throws StoppException, DivideByZeroException {
		int result = this.getStreamOne().get() + this.getStreamTwo().get();
		this.getStreamResult().put(result);
	}

}
