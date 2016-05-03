package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractObservee implements Observee {

	private List<Observer> observer;
	
	protected AbstractObservee(){
			this.observer = new ArrayList<Observer>();
		}
		
		public void register(Observer o){
			this.observer.add(o);	}
		
		public void deregister(Observer o){
			this.observer.remove(o);
		}
		
		public void notifyObserver(ComponentEvent e){
			Iterator<Observer> componentObserverIterator = observer.iterator();
			while(componentObserverIterator.hasNext()){
				Observer current = componentObserverIterator.next();
				current.update(e);
			}
		}
}
