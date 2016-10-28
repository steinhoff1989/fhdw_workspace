package regExpression;

import model.NoMoreBufferCopyAvailableException;
import model.Process;
import model.Vervielfältiger;

public abstract class RegularExpression {

	private RegularExpressionManager regExpManager;
	
	public RegularExpression() {
		super();
		this.regExpManager = RegularExpressionManager.getInstance();
	}
	
	public abstract Process toProcess() throws NoMoreBufferCopyAvailableException;
	abstract Vervielfältiger getVervielfaeltiger() throws NoMoreBufferCopyAvailableException;
	public abstract boolean accept(RegularExpressionVisitor regularExpressionVisitor);
	public abstract boolean equals(Object obj);
	public abstract void addExpressionsToRegularExpressionManager();
	
	public RegularExpressionManager getRegExpManager() {
		return regExpManager;
	}
}
