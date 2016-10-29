package regExpression;

import BufferAndLock.Buffer;
import exceptions.NoMoreVariablesAvailableException;
import model.Add;
import model.Process;
import model.Variable;

public class AEAdd extends AETwoPartOperation {

	public AEAdd(ArithmeticExpression reg1, ArithmeticExpression reg2) {
		super(reg1, reg2);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ArithmeticExpression) {
			ArithmeticExpression regExp = (ArithmeticExpression) obj;
			return regExp.accept(new AEVisitor() {

				@Override
				public boolean handle(AEConstant regConstant) {
					return false;
				}

				@Override
				public boolean handle(AETwoPartOperation regTwoPartOperation) {
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
		return new Add(this.getReg1().getProcess().getResults(), this.getReg2().getProcess().getResults());
	}

	@Override
	public Variable getVariable(int i) throws NoMoreVariablesAvailableException {
		return new Variable(this.getProcess().getResults(), i);
	}

//	@Override
//	public Variable getVervielfaeltiger() throws NoMoreVariablesAvailableException {
//		ArithmeticExpression regExpQuantity;
//		
//		Variable resultCopy = this.getRegExpManager().getResultCopy(this);
//		
//		if(resultCopy == null){
//			Add add = new Add(this.getReg1().getVervielfaeltiger().getNextBufferCopy(), this.getReg2().getVervielfaeltiger().getNextBufferCopy());
//			this.getRegExpManager().add(add, variable);
//			Variable bufferCopy = new Variable(add, numberOfCopies)
//			this.getRegExpManager().add(add);
//			
//		}
//
//		//TODO: add multiAdd in ListManager
////		Add add = new Add(this.getReg1().getVervielfaeltiger().getNextBufferCopy(), this.getReg2().getVervielfaeltiger().getNextBufferCopy());
////		Variable multiAdd = new Variable(add.getStreamResult(), regExpQuantity.getCounter());
//		
//		this.getRegExpManager().getResultCopy(this);
//		
//		return resultCopy.;
//	}

}
