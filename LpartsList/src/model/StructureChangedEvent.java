package model;

public class StructureChangedEvent implements ComponentEvent{

	public void accept(EventVisitor v) {
		v.handleStructureChangedEvent(this);
	}
}
