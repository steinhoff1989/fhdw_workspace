package model;

import model.AbstractBuffer.StoppException;

public abstract class Process {

	private final AbstractBuffer<Integer> streamOne;
	private final AbstractBuffer<Integer> streamTwo;
	private AbstractBuffer<Integer> streamResult;
	private final Thread thread;

	public Process(AbstractBuffer<Integer> streamOne, AbstractBuffer<Integer> streamTwo) {
		super();
		this.streamOne = streamOne;
		this.streamTwo = streamTwo;
		this.streamResult = new Buffer<Integer>();
		this.thread = new Thread(new Runnable() {

			@Override
			public void run() {
				boolean running = true;
				while (running) {
					try {
						Process.this.calculate();
					} catch (StoppException e) {
						running = false;
						Process.this.streamResult.stopp();
					} catch (DivideByZeroException e) {
						Process.this.streamResult.dividedByZero();
					}
			}
			}
		});
	}

	protected void startThread() {
		this.thread.start();
	}

	public AbstractBuffer<Integer> getStreamOne() {
		return streamOne;
	}

	public AbstractBuffer<Integer> getStreamTwo() {
		return streamTwo;
	}

	public AbstractBuffer<Integer> getStreamResult() {
		return this.streamResult;
	}
	
	public void setStreamResult(AbstractBuffer<Integer> streamResult) {
		this.streamResult = streamResult;
	}

	public abstract void calculate() throws DivideByZeroException, StoppException;
}
