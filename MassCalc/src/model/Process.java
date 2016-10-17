package model;

public abstract class Process {

	private final Buffer<Integer> streamOne;
	private final Buffer<Integer> streamTwo;
	private final Buffer<Integer> streamResult;
	private final Thread thread;

	public Process(Buffer<Integer> streamOne, Buffer<Integer> streamTwo) {
		super();
		this.streamOne = streamOne;
		this.streamTwo = streamTwo;
		this.streamResult = new Buffer<Integer>();
		this.thread = new Thread(new Runnable() {

			@Override
			public void run() {
				// while(true){
				Process.this.calculate();
				// }
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

	public abstract void calculate();
}
