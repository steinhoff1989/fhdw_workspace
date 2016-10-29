package regExpression;

import BufferAndLock.Buffer;
import exceptions.NoMoreVariablesAvailableException;

public abstract class AETwoPartOperation extends ArithmeticExpression{

	private final ArithmeticExpression reg1;
	private final ArithmeticExpression reg2;
	private final StructureManager regExpManager;
	
	protected AETwoPartOperation(ArithmeticExpression reg1, ArithmeticExpression reg2) {
		super();
		this.reg1 = reg1;
		this.reg2 = reg2;
		this.regExpManager = StructureManager.getInstance();
	}
	
	public StructureManager getRegExpManager() {
		return regExpManager;
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
		return this.getRegExpManager().getVariable(this).getResults();
	}
	
	@Override
	public boolean accept(AEVisitor regularExpressionVisitor) {
		return regularExpressionVisitor.handle(this);
	}
	
	public abstract boolean accept(AETwoPartOperationVisitor regTwoPartOperationVisitor);
}
