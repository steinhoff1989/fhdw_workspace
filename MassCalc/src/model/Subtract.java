package model;

import model.Buffer.StoppException;

public class Subtract extends Process{

	public Subtract(Buffer<Integer> streamOne, Buffer<Integer> streamTwo) {
		super(streamOne, streamTwo);
		this.startThread();
	}

	@Override
	public void calculate() throws DivideByZeroException {
		try {
			int result = this.getStreamOne().get() - this.getStreamTwo().get();
			this.getStreamResult().put(result);
		} catch (StoppException e) {
			this.getStreamResult().stopp();
		}
	}

}
