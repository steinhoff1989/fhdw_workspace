package model;

import model.Buffer.StoppException;

public class Multiply extends Process{

	public Multiply(Buffer<Integer> streamOne, Buffer<Integer> streamTwo) {
		super(streamOne, streamTwo);
		this.startThread();
	}

	@Override
	public void calculate() throws DivideByZeroException {
		try {
			int result = this.getStreamOne().get() * this.getStreamTwo().get();
			this.getStreamResult().put(result);
		} catch (StoppException e) {
			this.getStreamResult().stopp();
		}
	}

}
