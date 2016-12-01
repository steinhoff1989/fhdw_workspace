package model;

public interface EventVisitor {
	
	public void handlePriceChangedEvent(PriceChangedEvent e);
	public void handleStructureChangedEvent(StructureChangedEvent e);

}
