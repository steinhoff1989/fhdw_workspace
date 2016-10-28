package regExpression;

import model.Process;
import model.Vervielfältiger;

public class RegSubtract extends RegTwoPartOperation {

	protected RegSubtract(RegularExpression reg1, RegularExpression reg2) {
		super(reg1, reg2);
		// TODO Auto-generated constructor stub
	}

	public Process toProcess() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isCyclic() {
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
	public boolean accept(RegTwoPartOperationVisitor regTwoPartOperationVisitor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	Vervielfältiger getVervielfaeltiger() {
		// TODO Auto-generated method stub
		return null;
	}

}
