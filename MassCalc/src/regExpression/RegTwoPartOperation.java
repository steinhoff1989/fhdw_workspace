package regExpression;

public abstract class RegTwoPartOperation extends RegularExpression{

	private final RegularExpression reg1;
	private final RegularExpression reg2;
	private final RegularExpressionManager regExpManager;
	
	protected RegTwoPartOperation(RegularExpression reg1, RegularExpression reg2) {
		super();
		this.reg1 = reg1;
		this.reg2 = reg2;
		this.regExpManager = RegularExpressionManager.getInstance();
	}
	
	public RegularExpressionManager getRegExpManager() {
		return regExpManager;
	}

	public RegularExpression getReg1() {
		return reg1;
	}

	public RegularExpression getReg2() {
		return reg2;
	}

	@Override
	public boolean accept(RegularExpressionVisitor regularExpressionVisitor) {
		return regularExpressionVisitor.handle(this);
	}
	
	public abstract boolean accept(RegTwoPartOperationVisitor regTwoPartOperationVisitor);
}
