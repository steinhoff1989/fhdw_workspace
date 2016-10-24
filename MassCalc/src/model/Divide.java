package model;

import model.Buffer.StoppException;

public class Divide extends Process{

	public Divide(Buffer<Integer> streamOne, Buffer<Integer> streamTwo) {
		super(streamOne, streamTwo);
		this.startThread();
	}

	@Override
	public void calculate() throws DivideByZeroException {
		try {
			int divisor = this.getStreamTwo().get();
			if(divisor == 0){
				throw new DivideByZeroException();
			}
			
			int result = this.getStreamOne().get() / divisor;
			this.getStreamResult().put(result);
			
		} catch (StoppException e) {
			this.getStreamResult().stopp();
		}
	}
}
