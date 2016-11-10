package insertion;

public abstract class Process<T extends Comparable<T>> {

	private Thread thread;
	private boolean running = true;

	public boolean isRunning() {
		return running;
	}

	public Process() {
		this.thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (Process.this.isRunning()) {
					Process.this.calculate();
				}
			}
		});
	}

	public abstract void calculate();

	public void stopThread() {
		this.running = false;
	}

	public void startThread() {
		this.thread.start();
	}

}
