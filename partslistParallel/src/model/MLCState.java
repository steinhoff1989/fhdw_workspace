package model;

import java.util.List;

public class MLCState extends ComponentState{

	private List<QuantifiedComponent> cachedMaterialList;
	
	protected MLCState(List<QuantifiedComponent> cachedMaterialList) {
		this.cachedMaterialList = cachedMaterialList;
	}

	@Override
	public List<QuantifiedComponent> getMaterialList(Component component) {
		return this.cachedMaterialList;
	}

	@Override
	public String getOverAllPrice(Component component) {
		String overallPrice = component.getOverallPrice2();
		component.setState(new PMLCState(cachedMaterialList, overallPrice));
		return overallPrice;
	}

	@Override
	public void priceChanged(Component component) {
	}

	@Override
	public void structureChanged(Component component) {
		component.setState(NCState.getInstance());
	}


}
