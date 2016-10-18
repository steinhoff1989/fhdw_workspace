package model;

import model.AbstractBuffer.StoppException;

public class Multiply extends Process{

	public Multiply(AbstractBuffer<Integer> streamOne, AbstractBuffer<Integer> streamTwo) {
		super(streamOne, streamTwo);
		this.startThread();
	}

	@Override
	public void calculate() {
		try {
			int result = this.getStreamOne().get() * this.getStreamTwo().get();
			this.getStreamResult().put(result);
		} catch (StoppException e) {
			this.getStreamResult().stopp();
		}
	}

}
