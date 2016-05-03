package model;

import java.util.List;

public class PCState extends ComponentState{

	private String cachedOverallPrice;
	
	public PCState(String cachedOverallPrice){
		this.cachedOverallPrice = cachedOverallPrice;
	}
	
	@Override
	public List<QuantifiedComponent> getMaterialList(Component component) {
		List<QuantifiedComponent> materialList = component.getMaterialList2();
		component.setState(new PMLCState(materialList, this.cachedOverallPrice));
		return materialList;
	}

	@Override
	public String getOverAllPrice(Component component) {
		return this.cachedOverallPrice;
	}

	@Override
	public void priceChanged(Component component) {
		component.setState(NCState.getInstance());
		
	}

	@Override
	public void structureChanged(Component component) {
		component.setState(NCState.getInstance());
		
	}


}
