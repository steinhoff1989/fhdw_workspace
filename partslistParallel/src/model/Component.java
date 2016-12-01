package model;

import java.util.List;
import java.util.Vector;

public interface Component extends Observee{

	/**
	 * Adds amount pieces of the component part as subparts of the receiver. 
	 * @throws Exception If adding part as subpart of whole violates the hierarchy contraint of the partslist represented by the receiver.
	 */
	public void addPart(ComponentCommon part, int amount) throws Exception;
	/**
	 * Returns true if and only if the receiver directly or indirectly contains the given component.
	 */
	public boolean contains(Component component);
	/**
	 * Returns the list of all quantified parts that are direct sub-parts of the receiver.
	 */
	public Vector<QuantifiedComponent> getDirectParts();
	/**
	 * Returns the total number of all materials that are directly or indirectly parts of the receiver.
	 */
	public int getNumberOfMaterials();
	
	public String getName();
	
	public int getPrice();
	
	public void changePrice(int newPrice);
	public List<QuantifiedComponent> getMaterialList2();
	public String getOverallPrice2();
	public List<QuantifiedComponent> getMaterialList();
	public String getOverallPrice();
	public void setState(ComponentState state);
	
}
