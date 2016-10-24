package lockAndBuffer;

public class Semaphore implements AbstractSemaphore{

	private int counter;
	private Lock waiting;
	private Lock ka;
	

	/**Creates a semaphore with the internal counter set to the value
	 * of the parameter.
	 */
	public Semaphore(int count) {
		if(count<0) throw new Error();
		this.counter = count;
		this.waiting = new Lock(true);
		this.ka = new Lock(false);
	}
	@Override
	public void down() {
		this.ka.lock();
		while(this.counter == 0){
			this.ka.unlock();
			this.waiting.lock();
			this.ka.lock();
		}
		this.counter--;
		this.ka.unlock();
	}

	@Override
	public void up() {
		this.ka.lock();
		this.counter++;
		this.waiting.unlock();
		this.ka.unlock();
	}

}
