package regExpression;

import model.Process;
import model.Vervielfältiger;

public class RegConstant extends RegularExpression {

	private final int value;

	public RegConstant(int value) {
		super();
		this.value = value;
	}

	@Override
	public Process toProcess() {
		this.addExpressionsToRegularExpressionManager();
		return null;
	}

	@Override
	public void addExpressionsToRegularExpressionManager() {
		this.getRegExpManager().add(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof RegularExpression) {
			RegularExpression regExp = (RegularExpression)obj;
			
			return regExp.accept(new RegularExpressionVisitor() {

				@Override
				public boolean handle(RegConstant regConstant) {
					return RegConstant.this.value == regConstant.getValue();
				}

				@Override
				public boolean handle(RegTwoPartOperation regTwoPartOperation) {
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
	public boolean accept(RegularExpressionVisitor regularExpressionVisitor) {
		return regularExpressionVisitor.handle(this);
	}

	@Override
	Vervielfältiger getVervielfaeltiger() {
		// TODO Auto-generated method stub
		return null;
	}

}
