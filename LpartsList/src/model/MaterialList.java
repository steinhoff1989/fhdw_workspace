package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MaterialList{

	Map<String, QuantifiedComponent> quantifiedComponentMap;
	
	public MaterialList(){
		this.quantifiedComponentMap = new HashMap<String, QuantifiedComponent>();
	}
	
	public void addMaterial(final QuantifiedComponent material){
		if(this.quantifiedComponentMap.containsKey(material.getComponent().getName())){
			final QuantifiedComponent component = this.quantifiedComponentMap.get(material.getComponent().getName());
			component.addQuantity(material.getQuantity());
		}else{
			this.quantifiedComponentMap.put(material.getComponent().getName(),
					QuantifiedComponent.createQuantifiedComponent(material.getQuantity(), material.getComponent()));
		}
	}
	
//	public Vector<QuantifiedComponent> toVector(){
//		Vector<QuantifiedComponent> vector = new Vector<QuantifiedComponent>();
//		vector.addAll(quantifiedComponentMap.values());
//		return vector;
//	}
//	
//	public void addVector(Vector<QuantifiedComponent> vector){
//		Iterator<QuantifiedComponent> iterator = vector.iterator();
//		while(iterator.hasNext()){
//			QuantifiedComponent current = iterator.next();
//			this.addMaterial(current);
//		}
//	}

	public List<QuantifiedComponent> toList() {
		return new ArrayList<QuantifiedComponent>(this.quantifiedComponentMap.values());
	}

	public void add(final List<QuantifiedComponent> materialList) {
		final Iterator<QuantifiedComponent> iterator = materialList.iterator();
		while(iterator.hasNext()){
			final QuantifiedComponent current = iterator.next();
			this.addMaterial(current);
		}
	}

}
