package model;

import model.Buffer.StoppException;

public class Constant extends Process {

	private int value;

	public Constant(int value) {
		super(new Buffer<Integer>(0), new Buffer<Integer>(0));
		this.value = value;
		this.startThread();
	}

	@Override
	public void calculate() throws DivideByZeroException, StoppException {
		this.getStreamResult().put(value);
	}
}
