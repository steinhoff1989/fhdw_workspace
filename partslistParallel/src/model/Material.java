package model;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class Material extends ComponentCommon {
	private static final String UnstructuredMaterialMessage = "Materialien haben kein Struktur!";
	public static Material create(final String name, final int price) {
		return new Material(name,price);
	}
	public Material(final String name, final int price) {
		super(name,price);
	}

	public void addPart(final ComponentCommon part, final int amount) throws Exception {
		throw new Exception(UnstructuredMaterialMessage);
	}
	
	public boolean contains(final Component component) {
		return this.equals(component);
	}

	public Vector<QuantifiedComponent> getDirectParts() {
		return new Vector<QuantifiedComponent>();
	}

	public int getNumberOfMaterials() {
		return 1;
	}

	public List<QuantifiedComponent> getMaterialList2() {
		QuantifiedComponent result = QuantifiedComponent.createQuantifiedComponent(this.getNumberOfMaterials(), this);
		List<QuantifiedComponent> resultList = new ArrayList<QuantifiedComponent>();
		resultList.add(result);
		return resultList;
	}

	public String getOverallPrice2() {
		return Integer.toString(this.getPrice());
	}
	

}
