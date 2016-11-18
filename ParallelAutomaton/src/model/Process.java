package model;

public abstract class Process {

	private final Thread thread;
	private boolean running = true;


	/**
	 * Creates a Process object that starts a new Thread that calls run() continiously
	 */
	public Process() {
		this.thread = new Thread(new Runnable() {
			public void run() {
				while (Process.this.isRunning()) {
					Process.this.run();
				}
			}
		});
	}

	/**
	 * Will be called by the thread object at every tact, while the field <running> is set to true.
	 */
	public abstract void run();

	/**
	 * Returns true, if the field <running> is true
	 */
	synchronized public boolean isRunning() {
		return this.running;
	}

	/**
	 * Securely stops the running thread
	 */
	synchronized public void stopThread() {
		this.running = false;
	}

	/**
	 * Starts the thread.
	 */
	public void startThread() {
		this.thread.start();
	}
	
	

}
