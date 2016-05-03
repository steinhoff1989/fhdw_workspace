package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MaterialList{

	Map<String, QuantifiedComponent> quantifiedComponentMap;
	
	public MaterialList(){
		quantifiedComponentMap = new HashMap<String, QuantifiedComponent>();
	}
	
	public void addMaterial(QuantifiedComponent material){
		if(quantifiedComponentMap.containsKey(material.getComponent().getName())){
			QuantifiedComponent component = quantifiedComponentMap.get(material.getComponent().getName());
			component.addQuantity(material.getQuantity());
		}else{
			quantifiedComponentMap.put(material.getComponent().getName(), material);
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
		return new ArrayList<QuantifiedComponent>(quantifiedComponentMap.values());
	}

	public void add(List<QuantifiedComponent> materialList) {
		Iterator<QuantifiedComponent> iterator = materialList.iterator();
		while(iterator.hasNext()){
			QuantifiedComponent current = iterator.next();
			this.addMaterial(current);
		}
	}

}
