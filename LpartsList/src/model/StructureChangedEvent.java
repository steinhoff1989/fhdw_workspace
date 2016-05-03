package model;

public class StructureChangedEvent implements ComponentEvent{

	@Override
	public void accept(EventVisitor v) {
		v.handleStructureChangedEvent(this);
		
	}
	
	

}
