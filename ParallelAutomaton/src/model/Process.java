package model;

public abstract class Process {

	private final Thread thread;
	private boolean running = true;


	synchronized public boolean isRunning() {
		return this.running;
	}

	public Process() {
		this.thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (Process.this.isRunning()) {
					Process.this.run();
				}
			}
		});
	}

	public abstract void run();

	synchronized public void stopThread() {
		this.running = false;
	}

	public void startThread() {
		this.thread.start();
	}
	
	

}
