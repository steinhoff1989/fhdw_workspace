package regExpression;

import BufferAndLock.Buffer;
import exceptions.NoMoreVariablesAvailableException;
import model.Constant;
import model.Process;
import model.Variable;

public class AEConstant extends ArithmeticExpression {

	private final int value;

	public AEConstant(int value) {
		super();
		this.value = value;
	}

//	@Override
//	public Process toProcess() {
//		this.addExpressionsToRegularExpressionManager();
//		return null;
//	}

//	@Override
//	public void addExpressionsToRegularExpressionManager() {
//		this.getRegExpManager().add(this);
//	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ArithmeticExpression) {
			ArithmeticExpression regExp = (ArithmeticExpression)obj;
			
			return regExp.accept(new AEVisitor() {

				@Override
				public boolean handle(AEConstant regConstant) {
					return AEConstant.this.value == regConstant.getValue();
				}

				@Override
				public boolean handle(AETwoPartOperation regTwoPartOperation) {
					return false;
				}
			});
		}
		return false;
	}

	public int getValue() {
		return this.value;
	}

	@Override
	public boolean accept(AEVisitor regularExpressionVisitor) {
		return regularExpressionVisitor.handle(this);
	}

	@Override
	public Buffer<Integer> calculate() throws NoMoreVariablesAvailableException {
		return this.getRegExpManager().getVariable(this).getResults();
	}

	@Override
	void analyseStructure() {
		this.getRegExpManager().addAE(this);
	}

	@Override
	Process getProcess() {
		return new Constant(value);
	}

	@Override
	public Variable getVariable(int i) throws NoMoreVariablesAvailableException {
		return new Variable(this.getProcess().getResults(), i);
	}
}
