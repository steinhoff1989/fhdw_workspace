package model;

import view.ConcreteObserverView;

public class ConcreteObserverParallel extends ConcreteObserver{

	public static ConcreteObserver create(ConcreteObserverView concreteObserverView) {
		return new ConcreteObserverParallel(concreteObserverView);
	}

	private Thread myUpdateThread;
	boolean updateNeeded;
	
	private ConcreteObserverParallel(ConcreteObserverViewer view) {
		super(view);
		this.updateNeeded = false;
		this.myUpdateThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					if(updateNeeded){
						updateNeeded = false;
						ConcreteObserverParallel.this.doTheUpdate();	
					}else{
						synchronized (ConcreteObserverParallel.this) {
							try {
								ConcreteObserverParallel.this.wait();
							} catch (InterruptedException e) {}
						}
					}
				}
			}
		});
		this.myUpdateThread.start();
	}

	@Override
	public void update() {
		this.updateNeeded = true;
		synchronized (this) {
			this.notify();
		}
	}
}



