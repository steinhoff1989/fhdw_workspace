
package observer;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

abstract public class Observee {
	 		
	private Set<Observer> observer;
	
	protected Observee(){
		observer = new HashSet<Observer>();
	}
	/**Attaches an observer to this observee. 
	 * The same observer can only be registered once.
	 * @param observer receives update notifications 
	 * until deregister is called for this observer.
	 */
	public void deregister(Observer observer){
		this.observer.remove(observer);
	}
	/**Detaches an observer from this observee. 
	 * @param observer does no longer get update notifications
	 * until deregister is called for this observer.
	 */
	public void register(Observer observer){
		this.observer.add(observer);
	}
	/**Sends update notifications to all registered observers
	 */
	protected void notifyObservers(){
		Iterator<Observer> i = observer.iterator();
		while(i.hasNext()){
			Observer current = i.next();
			current.update();
		}
	}
}
