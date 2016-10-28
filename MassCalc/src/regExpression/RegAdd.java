package regExpression;

import model.Add;
import model.NoMoreBufferCopyAvailableException;
import model.Process;
import model.Vervielfältiger;

public class RegAdd extends RegTwoPartOperation {

	public RegAdd(RegularExpression reg1, RegularExpression reg2) {
		super(reg1, reg2);
	}

	@Override
	public Process toProcess() throws NoMoreBufferCopyAvailableException {
//		if(this.getRegExpManager().isAvailable(this)){
//			this.getRegExpManager().
//		}else{
//			Add add = new Add(this.getReg1().toProcess().getStreamResult(), this.getReg2().toProcess().getStreamResult());
//			this.getRegExpManager().addRegularExpression(add);
//		}
		this.addExpressionsToRegularExpressionManager();
		//Hier sind alle RegExp schon in der Liste!
		
//		return this.getVervielfaeltiger().getNextBufferCopy();
		Add add = new Add(this.getReg1().getVervielfaeltiger().getNextBufferCopy(), this.getReg2().getVervielfaeltiger().getNextBufferCopy());
		return add;
	}

	@Override
	public void addExpressionsToRegularExpressionManager() {
		this.getReg1().addExpressionsToRegularExpressionManager();
		this.getReg2().addExpressionsToRegularExpressionManager();

		this.getRegExpManager().add(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof RegularExpression) {
			RegularExpression regExp = (RegularExpression) obj;
			return regExp.accept(new RegularExpressionVisitor() {

				@Override
				public boolean handle(RegConstant regConstant) {
					return false;
				}

				@Override
				public boolean handle(RegTwoPartOperation regTwoPartOperation) {
					return regTwoPartOperation.accept(new RegTwoPartOperationVisitor() {

						@Override
						public boolean handdle(RegDivide regDivide) {
							return false;
						}

						@Override
						public boolean handdle(RegMultiply regMultiply) {
							return false;
						}

						@Override
						public boolean handdle(RegSubtract regSubtract) {
							return false;
						}

						@Override
						public boolean handdle(RegAdd regAdd) {
							return (RegAdd.this.getReg1().equals(regTwoPartOperation.getReg1())
									&& RegAdd.this.getReg2().equals(regTwoPartOperation.getReg2()));
						}
					});
				}
			});
		}
		return false;
	}

	@Override
	public boolean accept(RegTwoPartOperationVisitor regTwoPartOperationVisitor) {
		return regTwoPartOperationVisitor.handdle(this);
	}

	@Override
	public Vervielfältiger getVervielfaeltiger() throws NoMoreBufferCopyAvailableException {
		RegularExpressionQuantity regExpQuantity;
		try {
			regExpQuantity = this.getRegExpManager().getRegularExpressionQuantity(this);
		} catch (RegularExpressionQuantityNotInListException e) {
			regExpQuantity = new RegularExpressionQuantity(this);
		}

		//TODO: add multiAdd in ListManager
		Add add = new Add(this.getReg1().getVervielfaeltiger().getNextBufferCopy(), this.getReg2().getVervielfaeltiger().getNextBufferCopy());
		Vervielfältiger multiAdd = new Vervielfältiger(add.getStreamResult(), regExpQuantity.getCounter());
		
		return multiAdd;
	}

}
