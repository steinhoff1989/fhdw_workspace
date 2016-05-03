package model;

import java.util.List;

public class PMLCState extends ComponentState{

	private List<QuantifiedComponent> cachedMaterialList;
	private String cachedOverallPrice;
	
	public PMLCState(List<QuantifiedComponent> cachedMaterialList, String cachedOverallPrice){
		this.cachedMaterialList = cachedMaterialList;
		this.cachedOverallPrice = cachedOverallPrice;
	}
	
	@Override
	public List<QuantifiedComponent> getMaterialList(Component component) {
		return this.cachedMaterialList;
	}

	@Override
	public String getOverAllPrice(Component component) {
		return this.cachedOverallPrice;
	}

	@Override
	public void priceChanged(Component component) {
		component.setState(new MLCState(cachedMaterialList));
	}

	@Override
	public void structureChanged(Component component) {
		component.setState(NCState.getInstance());
		
	}
}
