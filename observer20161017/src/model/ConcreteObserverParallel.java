package model;

import lockAndBuffer.Buffer;
import view.ConcreteObserverView;

public class ConcreteObserverParallel extends ConcreteObserver{
	
	public static ConcreteObserver create(ConcreteObserverView view) {
		return new ConcreteObserverParallel(view);
	}
	private Thread myUpdateThread;
	private Buffer<Integer> buffer;
	
	private ConcreteObserverParallel(ConcreteObserverViewer view) {
		super(view);
		this.buffer = new Buffer<Integer>(1);
		this.myUpdateThread = new Thread(new Runnable() {
			@Override
			public void run() {
				boolean running = true;
				while (running) {
					try {
						ConcreteObserverParallel.this.doTheUpdate(buffer.get());
					} catch (Buffer.StoppException e) {
						running = false;
					}
				}
			}});
		this.myUpdateThread.start();
	}
	@Override
	public void update(int value) {
		this.buffer.put(value);
	}


}










