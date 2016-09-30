
package observer;

abstract public class Observee {
	 		
	protected Observee(){

	}
	/**Attaches an observer to this observee. 
	 * The same observer can only be registered once.
	 * @param observer receives update notifications 
	 * until deregister is called for this observer.
	 */
	public void register(Observer observer){
		//TODO Implement 'register'! Observers shall be registered at most once.
	}
	/**Detaches an observer from this observee. 
	 * @param observer does no longer get update notifications
	 * until deregister is called for this observer.
	 */
	public void deregister(Observer observer){
		//TODO Implement 'deregister'!
	}
	/**Sends update notifications to all registered observers
	 */
	protected void notifyObservers(){
		//TODO Implement 'notifyObersers!
	}
}
