package regExpression;

import exceptions.NoMoreVariablesAvailableException;
import model.Add;
import model.Process;

public class AEAdd extends AETwoPartOperation {

	/**
	 * Creates an arithmeticExpression that represents the addition of <reg1> and <reg2>
	 * @param reg1: represents the first summand
	 * @param reg2: represents the second summand
	 */
	public AEAdd(ArithmeticExpression reg1, ArithmeticExpression reg2) {
		super(reg1, reg2);
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
							return false;
						}

						@Override
						public boolean handdle(AEAdd regAdd) {
							return (AEAdd.this.getReg1().equals(regTwoPartOperation.getReg1())
									&& AEAdd.this.getReg2().equals(regTwoPartOperation.getReg2()));
						}
					});
				}

				@Override
				public Process handleProcess(AEConstant regConstant)
						throws NoMoreVariablesAvailableException {
					return null;
				}

				@Override
				public Process handleProcess(AETwoPartOperation regTwoPartOperation)
						throws NoMoreVariablesAvailableException {
					// TODO Auto-generated method stub
					return null;
				}
			});
		}
		return false;
	}

	@Override
	public boolean accept(AETwoPartOperationVisitor regTwoPartOperationVisitor) {
		return regTwoPartOperationVisitor.handdle(this);
	}

	@Override
	Process getProcess() throws NoMoreVariablesAvailableException {
		return new Add(this.getRegExpManager().getProcess(this.getReg1()).getResults(),
				this.getRegExpManager().getProcess(this.getReg2()).getResults());
	}

	// @Override
	// public Variable getVariable(int i) throws
	// NoMoreVariablesAvailableException {
	// return new Variable(this.getProcess().getResults(), i);
	// }

	@Override
	public int hashCode() {
		return (this.getReg1().hashCode() + this.getReg2().hashCode());
	}

	// @Override
	// public Variable getVervielfaeltiger() throws
	// NoMoreVariablesAvailableException {
	// ArithmeticExpression regExpQuantity;
	//
	// Variable resultCopy = this.getRegExpManager().getResultCopy(this);
	//
	// if(resultCopy == null){
	// Add add = new
	// Add(this.getReg1().getVervielfaeltiger().getNextBufferCopy(),
	// this.getReg2().getVervielfaeltiger().getNextBufferCopy());
	// this.getRegExpManager().add(add, variable);
	// Variable bufferCopy = new Variable(add, numberOfCopies)
	// this.getRegExpManager().add(add);
	//
	// }
	//
	// //TODO: add multiAdd in ListManager
	//// Add add = new
	// Add(this.getReg1().getVervielfaeltiger().getNextBufferCopy(),
	// this.getReg2().getVervielfaeltiger().getNextBufferCopy());
	//// Variable multiAdd = new Variable(add.getStreamResult(),
	// regExpQuantity.getCounter());
	//
	// this.getRegExpManager().getResultCopy(this);
	//
	// return resultCopy.;
	// }

}
