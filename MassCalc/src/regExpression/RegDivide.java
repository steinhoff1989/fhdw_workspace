package regExpression;

import model.Process;
import model.Vervielfältiger;

public class RegDivide extends RegTwoPartOperation{

	protected RegDivide(RegularExpression reg1, RegularExpression reg2) {
		super(reg1, reg2);
	}

	@Override
	public Process toProcess() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean accept(RegTwoPartOperationVisitor regTwoPartOperationVisitor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addExpressionsToRegularExpressionManager() {
		// TODO Auto-generated method stub
		
	}

	@Override
	Vervielfältiger getVervielfaeltiger() {
		// TODO Auto-generated method stub
		return null;
	}

}
