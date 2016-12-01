package model;

import java.util.List;

public class MLCState extends ComponentState{

	private final List<QuantifiedComponent> cachedMaterialList;
	
	/**
	 * Represents the state where the material list is already cached
	 * @param cachedMaterialList: The material list that shall be cached.
	 */
	protected MLCState(final List<QuantifiedComponent> cachedMaterialList) {
		this.cachedMaterialList = cachedMaterialList;
	}

	@Override
	public List<QuantifiedComponent> getMaterialList(final Component component) {
		return this.cachedMaterialList;
	}

	@Override
	public String getOverAllPrice(final Component component) {
		final String overallPrice = component.getOverallPrice2();
		component.setState(new PMLCState(this.cachedMaterialList, overallPrice));
		return overallPrice;
	}

	@Override
	public void priceChanged(final Component component) {
	}

	@Override
	public void structureChanged(final Component component) {
		component.setState(NCState.getInstance());
	}


}
