package model;

public class PriceChangedEvent implements ComponentEvent {

	public void accept(EventVisitor v) {
		v.handlePriceChangedEvent(this);
		
	}
}
