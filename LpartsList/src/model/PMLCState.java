package model;

import java.util.List;

public class PMLCState extends ComponentState{

	private final List<QuantifiedComponent> cachedMaterialList;
	private final String cachedOverallPrice;

	/**
	 * Represents the state where the price and the material list is already cached.
	 * @param cachedMaterialList: The material list that shall be cached
	 * @param cachedOverallPrice: The overall price that shall be cached
	 */
	public PMLCState(final List<QuantifiedComponent> cachedMaterialList, final String cachedOverallPrice){
		this.cachedMaterialList = cachedMaterialList;
		this.cachedOverallPrice = cachedOverallPrice;
	}
	
	@Override
	public List<QuantifiedComponent> getMaterialList(final Component component) {
		return this.cachedMaterialList;
	}

	@Override
	public String getOverAllPrice(final Component component) {
		return this.cachedOverallPrice;
	}

	@Override
	public void priceChanged(final Component component) {
		component.setState(new MLCState(this.cachedMaterialList));
	}

	@Override
	public void structureChanged(final Component component) {
		component.setState(NCState.getInstance());
		
	}
}
