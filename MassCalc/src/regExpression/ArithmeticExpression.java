package regExpression;

import BufferAndLock.Buffer;
import exceptions.NoMoreVariablesAvailableException;
import model.Process;

public abstract class ArithmeticExpression {

	final private StructureManager structureManager;
	
	/**
	 * Creates an ArithmeticExpression object and saves a singleton-reference to a manager object
	 */
	public ArithmeticExpression() {
		super();
		this.structureManager = StructureManager.getInstance();
	}
	
	/**
	 * Calculates the result of the represented ArithmeticExpression object.
	 * @return returns a buffer that hold all Integer results
	 * @throws NoMoreVariablesAvailableException if there is no more variable available to calculate the inner
	 * 			structure of this arithmetic expression.
	 *			 	
	 */
	public abstract Buffer<Integer> calculate() throws NoMoreVariablesAvailableException;
	
	/**
	 * analyzes the structure of <this> arithmetic expression and announce it to the manager.
	 */
	abstract void analyseStructure();

	/**
	 * Returns the representing process of <this> arithmetic expression
	 * @throws NoMoreVariablesAvailableException if there is no variable available to calculate the child
	 * 			structure of this arithmetic expression.
	 */
	abstract Process getProcess() throws NoMoreVariablesAvailableException;
	
	public abstract boolean equals(Object obj);
	//public abstract <T> T accept(AEVisitor<T> regularExpressionVisitor) throws NoMoreVariablesAvailableException;

	/**
	 * Returns the result of the passed regularExpressionVisitors handle method for <this> arithmeticExpresion
	 * @param regularExpressionVisitor: The visitor object who knows how <this> arithmetical expression has to
	 * 		be treated.
	 * @throws NoMoreVariablesAvailableException if there is no variable available to calculate the child
	 * 			structure of this arithmetic expression.
	 */
	public abstract Process acceptProcess(AEVisitor regularExpressionVisitor) throws NoMoreVariablesAvailableException;
	
	/**
	 * Returns the result of the passed regularExpressionVisitors handle method for <this> arithmeticExpresion
	 * @param regularExpressionVisitor: The visitor object who knows how <this> arithmetical expression has to
	 * 		be treated.
	 */
	public abstract Boolean acceptBoolean(AEVisitor regularExpressionVisitor);
	
	/**
	 * Calculates a unique hashCode for this type of arithmetic expression and returns the result.
	 */
	public abstract int hashCode();
	
	/**
	 * Returns the scrutureManager of this arithmetic expression 
	 */
	public StructureManager getRegExpManager() {
		return structureManager;
	}
}