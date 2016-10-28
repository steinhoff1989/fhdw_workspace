package regExpression;

import model.NoMoreBufferCopyAvailableException;
import model.Process;
import model.Vervielf�ltiger;

public abstract class RegularExpression {

	private RegularExpressionManager regExpManager;
	
	public RegularExpression() {
		super();
		this.regExpManager = RegularExpressionManager.getInstance();
	}
	
	public abstract Process toProcess() throws NoMoreBufferCopyAvailableException;
	abstract Vervielf�ltiger getVervielfaeltiger() throws NoMoreBufferCopyAvailableException;
	public abstract boolean accept(RegularExpressionVisitor regularExpressionVisitor);
	public abstract boolean equals(Object obj);
	public abstract void addExpressionsToRegularExpressionManager();
	
	public RegularExpressionManager getRegExpManager() {
		return regExpManager;
	}
}
