package regExpression;


import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import BufferAndLock.Buffer;
import exceptions.NoMoreVariablesAvailableException;
import exceptions.VariableAlreadyDefinedException;
import exceptions.VariableNotDefinedException;
import model.Variable;

public class StructureManager {

	private Map<ArithmeticExpression, Integer> arExpParts;
	private Map<ArithmeticExpression, Variable> variables;

	private static StructureManager instance;
	public static StructureManager getInstance() {
		if (instance == null) {
			instance = new StructureManager();
		}
		return instance;
	}

	private StructureManager() {
		arExpParts = new HashMap<ArithmeticExpression, Integer>();
		variables = new HashMap<ArithmeticExpression, Variable>();
	}

	public void addAE(ArithmeticExpression arExpPart){
		if (this.arExpParts.keySet().contains(arExpPart)) {
			Integer counter = this.arExpParts.get(arExpPart);
			counter++;
			this.arExpParts.put(arExpPart, counter);
		}else{
			this.arExpParts.put(arExpPart, 1);
		}
	}
	
	public Variable getVariable(ArithmeticExpression arExpPart) throws NoMoreVariablesAvailableException{
		if(this.variables.containsKey(arExpPart)){
			return this.variables.get(arExpPart);
		}else{
			if(this.arExpParts.containsKey(arExpPart)){
				return arExpPart.getVariable(this.arExpParts.get(arExpPart));
			}
			return arExpPart.getVariable(0);
		}
	}
	
//	public boolean isVariableAvailable(ArithmeticExpression arExp){
//		return this.variables.containsKey(arExp);
//	}
	
//	public void addVariable(ArithmeticExpression arExp, Variable variable) throws VariableAlreadyDefinedException{
//		if(this.variables.containsKey(arExp)){
//			throw new VariableAlreadyDefinedException();
//		}else{
//			this.variables.put(arExp, variable);
//		}
//	}
	
//	public void add(ArithmeticExpression regExp) {
//		RegularExpressionQuantity regExpQuantity = new RegularExpressionQuantity(regExp);
//		if (this.regularExpressionsQ.keySet().contains(regExpQuantity)) {
//			Iterator<RegularExpressionQuantity> i = this.regularExpressionsQ.keySet().iterator();
//			while(i.hasNext()){
//				RegularExpressionQuantity current = i.next();
//				if(current.equals(regExpQuantity)){
//					current.increment();
//					return;
//				}
//			}
//		} else {
//			this.regularExpressionsQ.put(regExpQuantity, null);
//		}
//	}
	
//	public void add(ArithmeticExpression regExp, Variable vervielfaeltiger) {
//		RegularExpressionQuantity regExpQuantity = new RegularExpressionQuantity(regExp);
//		if (this.regularExpressionsQ.keySet().contains(regExpQuantity)) {
//			this.regularExpressionsQ.put(regExpQuantity, vervielfaeltiger);
//		} else {
//			this.regularExpressionsQ.put(regExpQuantity, null);
//		}
//	}

//	public RegularExpressionQuantity getRegularExpressionQuantity(RegularExpression regExp)
//			throws RegularExpressionQuantityNotInListException {
//		RegularExpressionQuantity regExpQuantity = new RegularExpressionQuantity(regExp);
////		if (this.regularExpressionsQ.contains(regExpQuantity)) {
////			return this.regularExpressionsQ.get(this.regularExpressionsQ.indexOf(regExpQuantity));
////		} else {
//			throw new RegularExpressionQuantityNotInListException();
////		}
//	}

//	public Variable getResultCopy(ArithmeticExpression regExp) {
//		RegularExpressionQuantity regExpQuantity = new RegularExpressionQuantity(regExp);
//		
//		Variable bufferCopy = this.regularExpressionsQ.get(regExpQuantity);
//		if(bufferCopy == null){
////			this.regularExpressionsQ.put(regExpQuantity, regExpQuantity.getCounter());
//		}
//	}
}
