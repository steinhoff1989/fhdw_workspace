package model;

import model.AbstractBuffer.StoppException;

public class Subtract extends Process{

	public Subtract(AbstractBuffer<Integer> streamOne, AbstractBuffer<Integer> streamTwo) {
		super(streamOne, streamTwo);
		this.startThread();
	}

	@Override
	public void calculate() {
		try {
			int result = this.getStreamOne().get() - this.getStreamTwo().get();
			this.getStreamResult().put(result);
		} catch (StoppException e) {
			this.getStreamResult().stopp();
		}
	}

}
