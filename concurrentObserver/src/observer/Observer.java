package observer;


public interface Observer {
	
	/**Operation that each observer needs to implement in 
	 * order to receive notifications from observee.
	 */
	public void update();
}
