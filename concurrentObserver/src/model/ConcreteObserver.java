package model;
import java.util.Random;

import application.Application;
import observer.Observer;

abstract public class ConcreteObserver implements Observer {
	
	private static final int CalculationTime = 5000;
	private static final Random random = new Random();
	
	ConcreteObserverViewer view;
	ConcreteObservee observee;
	
	protected ConcreteObserver(ConcreteObserverViewer view){
		this.view = view;
		this.observee = Application.getObservee();
	}

	/**Simulates a calculation that can last at most for the time 
	 * configured in the constant CalculationTime in milli-seconds.
	 */
	synchronized void doSomeCalculations(){
		try {
			this.wait((long) (CalculationTime * random.nextFloat()) + 1);
		} catch (InterruptedException e) {}
	} 

	abstract public void update();

	protected void doTheUpdate() {
		int currentValue = this.observee.getValue();
		this.doSomeCalculations();
		this.view.setValue(currentValue);
	}
	
}
