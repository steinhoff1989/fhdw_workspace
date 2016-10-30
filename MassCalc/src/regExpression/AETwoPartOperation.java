package regExpression;

import BufferAndLock.Buffer;
import exceptions.NoMoreVariablesAvailableException;
import model.Process;

public abstract class AETwoPartOperation extends ArithmeticExpression{

	private final ArithmeticExpression reg1;
	private final ArithmeticExpression reg2;
	
	protected AETwoPartOperation(ArithmeticExpression reg1, ArithmeticExpression reg2) {
		super();
		this.reg1 = reg1;
		this.reg2 = reg2;
	}

	public ArithmeticExpression getReg1() {
		return reg1;
	}

	public ArithmeticExpression getReg2() {
		return reg2;
	}

	@Override
	void analyseStructure() {
		this.getReg1().analyseStructure();
		this.getReg2().analyseStructure();
		
		this.getRegExpManager().addAE(this);
	}
	
	@Override
	public Buffer<Integer> calculate() throws NoMoreVariablesAvailableException {
		this.analyseStructure();
		Buffer<Integer> result = this.getRegExpManager().getProcess(this).getResults();
		return result;
	}
	
	@Override
	public Boolean acceptBoolean(AEVisitor regularExpressionVisitor) {
		return regularExpressionVisitor.handleBoolean(this);
	}
	
	@Override
	public Process acceptProcess(AEVisitor regularExpressionVisitor) throws NoMoreVariablesAvailableException {
		return regularExpressionVisitor.handleProcess(this);
	}

	/**
	 * Returns the result of the passed regTwoPartOperationVisitor handle method for <this> two part operation arithmeticExpresion
	 * @param regTwoPartOperationVisitor: The visitor object who knows how <this> two part operation 
	 * 		arithmetical expression has to be treated.
	 */
	public abstract boolean accept(AETwoPartOperationVisitor regTwoPartOperationVisitor);
}
