package model;


import view.ConcreteObserverView;

public class ConcreteObserverParallel extends ConcreteObserver {

	public static ConcreteObserver create(ConcreteObserverView concreteObserverView) {
		return new ConcreteObserverParallel(concreteObserverView);
	}

//	final private Object criticalSection = new Object();

	private Thread myUpdateThread;
//	boolean updateNeeded;

	private Buffer buffer;

	private ConcreteObserverParallel(ConcreteObserverViewer view) {
		super(view);
		this.buffer = new Buffer();
//		this.updateNeeded = false;
		this.myUpdateThread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
//					synchronized (criticalSection) {
//						if (!updateNeeded) {
//							try {
//								criticalSection.wait();
//							} catch (InterruptedException e) {}
//						}
//						updateNeeded = false;
//					}
					ConcreteObserverParallel.this.doTheUpdate(buffer.get()); // TODO
				}
			}
		});
		this.myUpdateThread.start();
	}

	@Override
	public void update(int value) {
//		synchronized (criticalSection) {
//			this.updateNeeded = true;
			this.buffer.put(value);
//			criticalSection.notify();
//		}
	}
}
