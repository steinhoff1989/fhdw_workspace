package regExpression;

import java.util.HashMap;
import java.util.Map;

import exceptions.NoMoreVariablesAvailableException;
import model.Process;
import model.Variable;

public class StructureManager {

	private Map<ArithmeticExpression, Integer> arExpParts;
	private Map<ArithmeticExpression, Process> processes;

	private static StructureManager instance;

	public static StructureManager getInstance() {
		if (instance == null) {
			instance = new StructureManager();
		}
		return instance;
	}

	private StructureManager() {
		arExpParts = new HashMap<ArithmeticExpression, Integer>();
		processes = new HashMap<ArithmeticExpression, Process>();
	}

	public void addAE(ArithmeticExpression arExpPart) {
		if (this.arExpParts.keySet().contains(arExpPart)) {
			Integer counter = this.arExpParts.get(arExpPart);
			counter++;
			this.arExpParts.put(arExpPart, counter);
		} else {
			this.arExpParts.put(arExpPart, 1);
		}
	}

	public Process getProcess(ArithmeticExpression arExpPart) throws NoMoreVariablesAvailableException {
		if (this.processes.containsKey(arExpPart)) {
			return this.processes.get(arExpPart);
		} else {
			Process p = arExpPart.getProcess();
			Process result = arExpPart.acceptProcess(new AEVisitor() {

				@Override
				public Process handleProcess(AETwoPartOperation regTwoPartOperation) throws NoMoreVariablesAvailableException {
					Variable v;
					v = new Variable(p.getResults(), StructureManager.this.arExpParts.get(arExpPart));
					StructureManager.this.processes.put(arExpPart, v);
					return StructureManager.this.getProcess(arExpPart);
				}

				@Override
				public Process handleProcess(AEConstant regConstant) throws NoMoreVariablesAvailableException {
					StructureManager.this.processes.put(arExpPart, arExpPart.getProcess());
					return p;
				}

				@Override
				public Boolean handleBoolean(AEConstant regConstant) {
					throw new Error();
				}

				@Override
				public Boolean handleBoolean(AETwoPartOperation regTwoPartOperation) {
					throw new Error();
				}
			});
			return result;
		}
	}

	public int countVariables() {
		return this.processes.size();
	}

	public int countArithmeticExpressions() {
		return this.arExpParts.size();
	}

	public void clear() {
		this.arExpParts.clear();
		this.processes.clear();
	}

	// public boolean isVariableAvailable(ArithmeticExpression arExp){
	// return this.variables.containsKey(arExp);
	// }

	// public void addVariable(ArithmeticExpression arExp, Variable variable)
	// throws VariableAlreadyDefinedException{
	// if(this.variables.containsKey(arExp)){
	// throw new VariableAlreadyDefinedException();
	// }else{
	// this.variables.put(arExp, variable);
	// }
	// }

	// public void add(ArithmeticExpression regExp) {
	// RegularExpressionQuantity regExpQuantity = new
	// RegularExpressionQuantity(regExp);
	// if (this.regularExpressionsQ.keySet().contains(regExpQuantity)) {
	// Iterator<RegularExpressionQuantity> i =
	// this.regularExpressionsQ.keySet().iterator();
	// while(i.hasNext()){
	// RegularExpressionQuantity current = i.next();
	// if(current.equals(regExpQuantity)){
	// current.increment();
	// return;
	// }
	// }
	// } else {
	// this.regularExpressionsQ.put(regExpQuantity, null);
	// }
	// }

	// public void add(ArithmeticExpression regExp, Variable vervielfaeltiger) {
	// RegularExpressionQuantity regExpQuantity = new
	// RegularExpressionQuantity(regExp);
	// if (this.regularExpressionsQ.keySet().contains(regExpQuantity)) {
	// this.regularExpressionsQ.put(regExpQuantity, vervielfaeltiger);
	// } else {
	// this.regularExpressionsQ.put(regExpQuantity, null);
	// }
	// }

	// public RegularExpressionQuantity
	// getRegularExpressionQuantity(RegularExpression regExp)
	// throws RegularExpressionQuantityNotInListException {
	// RegularExpressionQuantity regExpQuantity = new
	// RegularExpressionQuantity(regExp);
	//// if (this.regularExpressionsQ.contains(regExpQuantity)) {
	//// return
	// this.regularExpressionsQ.get(this.regularExpressionsQ.indexOf(regExpQuantity));
	//// } else {
	// throw new RegularExpressionQuantityNotInListException();
	//// }
	// }

	// public Variable getResultCopy(ArithmeticExpression regExp) {
	// RegularExpressionQuantity regExpQuantity = new
	// RegularExpressionQuantity(regExp);
	//
	// Variable bufferCopy = this.regularExpressionsQ.get(regExpQuantity);
	// if(bufferCopy == null){
	//// this.regularExpressionsQ.put(regExpQuantity,
	// regExpQuantity.getCounter());
	// }
	// }
}
