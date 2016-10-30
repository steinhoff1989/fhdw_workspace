package regExpression;

import BufferAndLock.Buffer;
import exceptions.NoMoreVariablesAvailableException;
import model.Constant;
import model.Process;

public class AEConstant extends ArithmeticExpression {

	private final int value;

	/**
	 * Creates a constant ArithmeticExpression object, that represents <value>
	 * @param value: The value which this Constant arithmetical expression is representing
	 */
	public AEConstant(int value) {
		super();
		this.value = value;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ArithmeticExpression) {
			ArithmeticExpression regExp = (ArithmeticExpression)obj;
			
			return regExp.acceptBoolean(new AEVisitor() {

				@Override
				public Boolean handleBoolean(AEConstant regConstant) {
					return AEConstant.this.value == regConstant.getValue();
				}

				@Override
				public Boolean handleBoolean(AETwoPartOperation regTwoPartOperation) {
					return false;
				}

				@Override
				public Process handleProcess(AEConstant regConstant) throws NoMoreVariablesAvailableException {
					throw new Error();
				}

				@Override
				public Process handleProcess(AETwoPartOperation regTwoPartOperation)
						throws NoMoreVariablesAvailableException {
					throw new Error();
				}
			});
		}
		return false;
	}

	public int getValue() {
		return this.value;
	}

	@Override
	public int hashCode() {
		return this.value;
	}

	@Override
	public Boolean acceptBoolean(AEVisitor regularExpressionVisitor) {
		return regularExpressionVisitor.handleBoolean(this);
	}

	@Override
	public Process acceptProcess(AEVisitor regularExpressionVisitor) throws NoMoreVariablesAvailableException {
		return regularExpressionVisitor.handleProcess(this);
	}

	@Override
	public Buffer<Integer> calculate() throws NoMoreVariablesAvailableException {
		return this.getRegExpManager().getProcess(this).getResults();
	}

	@Override
	void analyseStructure() {
		this.getRegExpManager().addAE(this);
	}

	@Override
	Process getProcess() {
		return new Constant(value);
	}


}
