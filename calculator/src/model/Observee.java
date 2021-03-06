package model;

public interface Observee {

	public void register(Observer o);
	
	public void deregister(Observer o);
	
	public void notifyObservers() throws DivisionByZeroException;
}
