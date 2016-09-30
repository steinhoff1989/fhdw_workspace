package model;

public class ConcreteObserverSequential extends ConcreteObserver {

	public static ConcreteObserver create(ConcreteObserverViewer view) {
		return new ConcreteObserverSequential(view);
	}

	protected ConcreteObserverSequential(ConcreteObserverViewer view) {
		super(view);
	}

	@Override
	public void update() {
		doTheUpdate();
	}

}
