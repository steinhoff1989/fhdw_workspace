package model;

public class QuantifiedComponent extends AbstractObservee implements Comparable<QuantifiedComponent>, Observer{
	
	private static final String QuantityOpenBracket = "(";
	private static final String QuantityCloseBracket = ")";

	public static QuantifiedComponent createQuantifiedComponent (final int quantity, final Component component){
		return new QuantifiedComponent(quantity,component);
	}
	private int quantity;
	final private Component component;
	
	private QuantifiedComponent(final int quantity, final Component component){
		this.quantity = quantity;
		this.component = component;
		this.component.register(this);
	}

	public Component getComponent() {
		return component;
	}

	public int getQuantity() {
		return quantity;
	}
	private void setQuantity(final int quantity){
		this.quantity = quantity;
		this.notifyObserver(new StructureChangedEvent());
	}
	public void addQuantity(final int quantity) {
		this.setQuantity(this.getQuantity() + quantity);
	}
	@Override
	public boolean equals(Object argument) {
		if(argument instanceof QuantifiedComponent){
			QuantifiedComponent qc = (QuantifiedComponent) argument;
			return this.component.getName().equals(qc.getComponent().getName()) && this.quantity == qc.quantity;
		}else{
			return false;
		}
	}
	public boolean contains(final Component part) {
		return this.getComponent().contains(part);
	}
	@Override
	public String toString() {
		return this.getComponent().toString() + QuantityOpenBracket + this.getQuantity() + QuantityCloseBracket;
	}

	public int getNumberOfMaterials() {
		return this.getComponent().getNumberOfMaterials() * this.getQuantity();
	}
	
	public int getOverallPrice(){
		return this.quantity * Integer.valueOf(this.component.getOverallPrice2());
	}

	public int compareTo(QuantifiedComponent arg0) {
		return this.component.getName().compareTo(arg0.getComponent().getName());
	}

	public void update(ComponentEvent e) {
		this.notifyObserver(e);
	}

//	public List<QuantifiedComponent> getMaterialList() {
//		MaterialList result = new MaterialList();
//		
//		List<QuantifiedComponent> materialList = component.getMaterialList();
//		Iterator<QuantifiedComponent> qcIterator = materialList.iterator();
//		while(qcIterator.hasNext()){
//			QuantifiedComponent current = qcIterator.next();
//			current.addQuantity(quantity);
//		}
//		
//		result.add(materialList);
//		
//		return result.toList();
//		//List<QuantifiedComponent> result = component.getMaterialList();
//		//result.get(result.indexOf(this))
//	}
}
