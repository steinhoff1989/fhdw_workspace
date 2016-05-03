package model;

import java.util.List;

public abstract class ComponentCommon extends AbstractObservee implements Component {

	private final String name;
	private int price;
	
	protected ComponentState state;

	protected ComponentCommon(final String name, final int price) {
		this.name = name;
		this.price = price;
		this.state = NCState.getInstance();
	}

	@Override
	public String getName() {
		return this.name;
	}
	public int getPrice() {
		return this.price;
	}

	@Override
	public String toString(){
		return this.getName();
	}
	@Override
	public boolean equals(Object argument) {
		return super.equals(argument);
	}
	@Override
	public void changePrice(int newPrice) {
		this.price = newPrice;
		this.setState(NCState.getInstance()); 
		this.notifyObserver(new PriceChangedEvent());
	}
	
	public void setState(ComponentState state){
		this.state = state;
	}
	
	@Override
	public List<QuantifiedComponent> getMaterialList() {
		return state.getMaterialList(this);
	}
	@Override
	public String getOverallPrice() {
		return state.getOverAllPrice(this);
	}
	
}
