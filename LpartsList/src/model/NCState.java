package model;

import java.util.List;

public class NCState extends ComponentState{

	private static NCState instance;
	
	private NCState(){}
	
	public static NCState getInstance(){
		if(instance == null){
			return new NCState();
		}else{
			return instance;
		}
	}
	
	@Override
	public List<QuantifiedComponent> getMaterialList(Component component) {
		List<QuantifiedComponent> materialList = component.getMaterialList2();
		component.setState(new MLCState(materialList));
		return materialList;
	}

	@Override
	public String getOverAllPrice(Component component) {
		String overallPrice = component.getOverallPrice2();
		component.setState(new PCState(overallPrice));
		return overallPrice;
	}

	@Override
	public void priceChanged(Component component) {	
	}

	@Override
	public void structureChanged(Component component) {
	}

}
