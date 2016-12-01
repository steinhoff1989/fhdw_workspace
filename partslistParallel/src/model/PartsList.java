package model;
import java.util.HashMap;
import java.util.Vector;


/**
 *  Represents a hierarchical partslist as a mapping from unique part names to components.
 */
public class PartsList {
	
	private static final String DoubleDefinitionMessage = "Name bereits vorhanden!";
	private static final String UnknownComponentMessage = "Unbekannte Komponente: ";

	/**
	 * @return An empty partslist.
	 */
	public static PartsList create (){
		return new PartsList(new HashMap<String, Component>());
	}
	final private HashMap<String, Component> componentsMap;
	
	private PartsList(final HashMap<String, Component> componentsMap){
		this.componentsMap = componentsMap;
	}

	@Override
	public boolean equals(Object argument) {
		return super.equals(argument);
	}
	private HashMap<String, Component> getComponentsMap(){
		return this.componentsMap;
	}
	/**
	 * Creates a new material with the given name and price as a component of the receiver.
	 * @throws Exception If the provided name is already used for another component of the receiver.
	 */
	public void createMaterial(final String name, final int price) throws Exception {
		//TODO Handle price parameter // DONE!
		if (this.getComponentsMap().containsKey(name))
			throw new Exception(DoubleDefinitionMessage);
		final Material newMaterial = Material.create(name,price);
		this.getComponentsMap().put(name, newMaterial);
	}
	/**
	 * Creates a new product with the given name and price as a component of the receiver.
	 * @throws Exception If the provided name is already used for another component of the receiver.
	 */
	public void createProduct(final String name, final int price) throws Exception {
		//TODO Handle price parameter// DONE!
		if (this.getComponentsMap().containsKey(name)) throw new Exception(DoubleDefinitionMessage);
		final Product newProduct = Product.create(name, price);
		this.getComponentsMap().put(name, newProduct);
	}
	/**
	 * Adds amount pieces of the component part as subparts of the component whole. 
	 * @throws Exception <ol> <li> If whole or part are not contained in the partslist of the receiver.</li>
	 *                        <li> If adding part as subpart of whole violates the hierarchy contraint of the partslist represented by the receiver.</li>
	 *                   </ol>
	 */
	public void addPart(final Component whole, final ComponentCommon part, final int amount) throws Exception {
		if (!this.getComponentsMap().containsValue(whole)) throw new Exception(UnknownComponentMessage + whole.getName());
		if (!this.getComponentsMap().containsValue(part)) throw new Exception(UnknownComponentMessage + part.getName());
		whole.addPart(part,amount);
	}
	/**
	 * Returns the number of materials that are directly or indirectly parts of the given component.
	 * @throws Exception If component is not contained in the partslist of the receiver.
	 */
	public int getMaterialCount(final Component component) throws Exception {
		if (!this.getComponentsMap().containsValue(component)) throw new Exception(UnknownComponentMessage + component.getName());
		return component.getNumberOfMaterials();
	}
	public Vector<Component> getComponents() {
		return new Vector<Component>(this.getComponentsMap().values());
	}
	/**
	 * Returns the list of quantified parts that are the direct subparts of component.
	 * @throws Exception If component is not contained in the partslist of the receiver.
	 */
	public Vector<QuantifiedComponent> getParts(final Component component) throws Exception {
		if (!this.getComponentsMap().containsValue(component)) throw new Exception(UnknownComponentMessage + component.getName());
		return component.getDirectParts();
	}
	/**
	 * Returns all materials of <component> as <Vector<QuantifiedComponents>>.
	 * @param component: the component (material or product) of which the material list will be returned
	 * @return 
	 */
	public Vector<QuantifiedComponent> getMaterialList(final Component component) {
		//TODO implement getMaterialList // DONE!
		//return new Vector<QuantifiedComponent>();
		//return component.getMaterialList();
		
		return new Vector<QuantifiedComponent>(component.getMaterialList2());
	}
	/**
	 * Calculates the overallprice of a component referring to any single material and its price.
	 * @param component describes the component which overallprice gets calculated
	 * @return Overallprice for a Product or a material.
	 */
	public String getOverallPrice(final Component component) {
		//TODO implement getOverallPrice // DONE!
		return component.getOverallPrice2();
	}

	/**
	 * Changes the price of a component. This can be a product or a material.
	 * @param component describes the component of which the price get changed.
	 * @param newPrice is the new price for the component
	 */
	public void changePrice(final Component component, final int newPrice) {
		component.changePrice(newPrice);
		// TODO implement changePrice // DONE!
	}
}
