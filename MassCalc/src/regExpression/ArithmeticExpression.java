package regExpression;

import BufferAndLock.Buffer;
import exceptions.NoMoreVariablesAvailableException;
import model.Process;
import model.Variable;

public abstract class ArithmeticExpression {

	final private StructureManager regExpManager;
	
	public ArithmeticExpression() {
		super();
		this.regExpManager = StructureManager.getInstance();
	}
	
	public abstract Buffer<Integer> calculate() throws NoMoreVariablesAvailableException;
	abstract void analyseStructure();
	abstract Process getProcess() throws NoMoreVariablesAvailableException;
	public abstract boolean equals(Object obj);
	public abstract boolean accept(AEVisitor regularExpressionVisitor);
	
	public StructureManager getRegExpManager() {
		return regExpManager;
	}

	public abstract Variable getVariable(int i) throws NoMoreVariablesAvailableException;
}
