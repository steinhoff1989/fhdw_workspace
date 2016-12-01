package model;

import java.util.List;

public class PCState extends ComponentState{

	private final String cachedOverallPrice;
	
	/**
	 * Represents a State where the overall price is already cached.
	 * @param cachedOverallPrice: The overall price that shall be cached.
	 */
	public PCState(final String cachedOverallPrice){
		this.cachedOverallPrice = cachedOverallPrice;
	}
	
	@Override
	public List<QuantifiedComponent> getMaterialList(final Component component) {
		final List<QuantifiedComponent> materialList = component.getMaterialList2();
		component.setState(new PMLCState(materialList, this.cachedOverallPrice));
		return materialList;
	}

	@Override
	public String getOverAllPrice(final Component component) {
		return this.cachedOverallPrice;
	}

	@Override
	public void priceChanged(final Component component) {
		component.setState(NCState.getInstance());
		
	}

	@Override
	public void structureChanged(final Component component) {
		component.setState(NCState.getInstance());
		
	}


}
