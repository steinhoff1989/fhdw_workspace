package regExpression;

import BufferAndLock.Buffer;
import exceptions.NoMoreVariablesAvailableException;
import model.Multiply;
import model.Process;
import model.Subtract;
import model.Variable;

public class AESubtract extends AETwoPartOperation {

	protected AESubtract(ArithmeticExpression reg1, ArithmeticExpression reg2) {
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
							return (AESubtract.this.getReg1().equals(regTwoPartOperation.getReg1())
									&& AESubtract.this.getReg2().equals(regTwoPartOperation.getReg2()));
						}

						@Override
						public boolean handdle(AEAdd regAdd) {
							return false;
						}
					});
				}
			});
		}
		return false;
	}
	
	@Override
	Process getProcess() throws NoMoreVariablesAvailableException {
		return new Subtract(this.getReg1().getProcess().getResults(), this.getReg2().getProcess().getResults());
	}

	@Override
	public Variable getVariable(int i) throws NoMoreVariablesAvailableException {
		return new Variable(this.getProcess().getResults(), i);
	}


}
