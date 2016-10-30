package regExpression;

import exceptions.NoMoreVariablesAvailableException;
import model.Process;
import model.Subtract;

public class AESubtract extends AETwoPartOperation {

	/**
	 * Creates an arithmeticExpression that represents the subtraction of <reg1> by <reg2>
	 * @param reg1: represents the minuend
	 * @param reg2: represents the subtrahend
	 */
	public AESubtract(ArithmeticExpression reg1, ArithmeticExpression reg2) {
		super(reg1, reg2);
	}
	
	@Override
	public boolean accept(AETwoPartOperationVisitor regTwoPartOperationVisitor) {
		return regTwoPartOperationVisitor.handdle(this);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ArithmeticExpression) {
			ArithmeticExpression regExp = (ArithmeticExpression) obj;
			return regExp.acceptBoolean(new AEVisitor() {

				@Override
				public Boolean handleBoolean(AEConstant regConstant) {
					return false;
				}

				@Override
				public Boolean handleBoolean(AETwoPartOperation regTwoPartOperation) {
					return regTwoPartOperation.accept(new AETwoPartOperationVisitor() {

						@Override
						public boolean handdle(AEDivide regDivide) {
							return false;
						}

						@Override
						public boolean handdle(AEMultiply regMultiply) {
							return false;
						}

						@Override
						public boolean handdle(AESubtract regSubtract) {
							return (AESubtract.this.getReg1().equals(regTwoPartOperation.getReg1())
									&& AESubtract.this.getReg2().equals(regTwoPartOperation.getReg2()));
						}

						@Override
						public boolean handdle(AEAdd regAdd) {
							return false;
						}
					});
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
	
	@Override
	Process getProcess() throws NoMoreVariablesAvailableException {
		return new Subtract(this.getRegExpManager().getProcess(this.getReg1()).getResults(), this.getRegExpManager().getProcess(this.getReg2()).getResults());
	}

	@Override
	public int hashCode() {
		return (this.getReg1().hashCode() - this.getReg2().hashCode());
	}

}
