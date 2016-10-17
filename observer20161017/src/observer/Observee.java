
package observer;

import java.util.HashSet;
import java.util.Set;

abstract public class Observee {
	 		
	private Set<Observer> currentObservers;
	
	protected Observee(){
		this.currentObservers = new HashSet<Observer>();
	}
	/**Attaches an observer to this observee. 
	 * The same observer can only be registered once.
	 * @param observer receives update notifications 
	 * until deregister is called for this observer.
	 */
	public void register(Observer observer){
		this.currentObservers.add(observer);
	}
	/**Detaches an observer from this observee. 
	 * @param observer does no longer get update notifications
	 * until register is called for this observer.
	 */
	public void deregister(Observer observer){
		this.currentObservers.remove(observer);
	}
	/**Sends update notifications to all registered observers
	 */
	protected void notifyObservers(int value){
		for (Observer current : currentObservers) {
			current.update(value);
		}
	}
}
