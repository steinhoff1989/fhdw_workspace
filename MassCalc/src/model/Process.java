package model;

import model.Buffer.StoppException;

public abstract class Process{

	private final Buffer<Integer> streamOne;
	private final Buffer<Integer> streamTwo;
	private Buffer<Integer> streamResult;
	private final Thread thread;

	public Process(Buffer<Integer> streamOne, Buffer<Integer> streamTwo) {
		super();
		this.streamOne = streamOne;
		this.streamTwo = streamTwo;
		this.streamResult = new Buffer<Integer>(100);
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

	public Buffer<Integer> getStreamOne() {
		return streamOne;
	}

	public Buffer<Integer> getStreamTwo() {
		return streamTwo;
	}

	public Buffer<Integer> getStreamResult() {
		return this.streamResult;
	}
	
	public void setStreamResult(Buffer<Integer> streamResult) {
		this.streamResult = streamResult;
	}

	public abstract void calculate() throws DivideByZeroException, StoppException;
}
