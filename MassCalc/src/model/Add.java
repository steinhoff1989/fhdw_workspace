package model;

import model.AbstractBuffer.StoppException;

public final class Add extends Process {

	public Add(AbstractBuffer<Integer> streamOne, AbstractBuffer<Integer> streamTwo) {
		super(streamOne, streamTwo);
		this.startThread();
	}

	@Override
	public void calculate() throws StoppException, DivideByZeroException {
		int result = this.getStreamOne().get() + this.getStreamTwo().get();
		this.getStreamResult().put(result);
	}

}
