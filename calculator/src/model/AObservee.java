package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AObservee implements Observee{

	private List<Observer> observers;
	
	public AObservee(){
		this.observers = new ArrayList<Observer>();
	}
	
	public void register(Observer o){
		observers.add(o);
	}
	
	public void deregister(Observer o){
		observers.remove(o);
	}
	
	public void notifyObservers() throws DivisionByZeroException{
		Iterator<Observer> iterator = observers.iterator();
		while(iterator.hasNext()){
			Observer current = iterator.next();
			current.update();
		}
	}
}
