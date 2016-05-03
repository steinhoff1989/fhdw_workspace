package model;

import java.util.List;

public abstract class ComponentState {

	abstract public List<QuantifiedComponent> getMaterialList(Component component);
	abstract public String getOverAllPrice(Component component);
	abstract public void priceChanged(Component component);
	abstract public void structureChanged(Component component);
	
}

