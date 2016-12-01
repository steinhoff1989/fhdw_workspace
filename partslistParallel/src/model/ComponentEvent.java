package model;

public interface ComponentEvent {
	
	public void accept(EventVisitor v);

}
