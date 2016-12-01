package model;

import java.util.List;

public class NCState extends ComponentState{

	private static NCState instance;
	
	private NCState(){}
	
	/**
	 * Represents the state where 
	 * @return
	 */
	public static NCState getInstance(){
		if(instance == null){
			return new NCState();
		}else{
			return instance;
		}
	}
	
	@Override
	public List<QuantifiedComponent> getMaterialList(final Component component) {
		final List<QuantifiedComponent> materialList = component.getMaterialList2();
		component.setState(new MLCState(materialList));
		return materialList;
	}

	@Override
	public String getOverAllPrice(final Component component) {
		final String overallPrice = component.getOverallPrice2();
		component.setState(new PCState(overallPrice));
		return overallPrice;
	}

	@Override
	public void priceChanged(final Component component) {	
	}

	@Override
	public void structureChanged(final Component component) {
	}

}
