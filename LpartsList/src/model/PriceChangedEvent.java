package model;

public class PriceChangedEvent implements ComponentEvent {

	@Override
	public void accept(EventVisitor v) {
		v.handlePriceChangedEvent(this);
		
	}

}
