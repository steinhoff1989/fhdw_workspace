package Lock;

public class Lock implements AbstractLock {

	private boolean locked;

	/**
	 * Creates a new Lock with the lock-flag set to <locked>
	 * 
	 * @param locked
	 */
	public Lock(boolean locked) {
		this.locked = locked;
	}

	@Override
	synchronized public void lock() {
		while(this.locked){
			try {
				this.wait(); //wait() verlässt den Kritischen Abschnitt!
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.locked = true;
	}

	@Override
	synchronized public void unlock() {
		this.locked = false; //Reihenfolge in diesem Kontext nicht wichtig, da lock in gleichem KA!
		this.notify();
	}

}
