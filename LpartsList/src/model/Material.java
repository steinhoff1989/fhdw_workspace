package model;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class Material extends ComponentCommon {
	//TEST

	private static final String UnstructuredMaterialMessage = "Materialien haben kein Struktur!";
	public static Material create(final String name, final int price) {
		return new Material(name,price);
	}
	public Material(final String name, final int price) {
		super(name,price);
	}
	@Override
	public void addPart(final ComponentCommon part, final int amount) throws Exception {
		throw new Exception(UnstructuredMaterialMessage);
	}
	@Override
	public boolean contains(final Component component) {
		return this.equals(component);
	}
	@Override
	public Vector<QuantifiedComponent> getDirectParts() {
		return new Vector<QuantifiedComponent>();
	}
	@Override
	public int getNumberOfMaterials() {
		return 1;
	}
	@Override
	public List<QuantifiedComponent> getMaterialList2() {
		QuantifiedComponent result = QuantifiedComponent.createQuantifiedComponent(this.getNumberOfMaterials(), this);
		List<QuantifiedComponent> resultList = new ArrayList<QuantifiedComponent>();
		resultList.add(result);
		return resultList;
	}
	@Override
	public String getOverallPrice2() {
		return Integer.toString(this.getPrice());
	}
	

}
