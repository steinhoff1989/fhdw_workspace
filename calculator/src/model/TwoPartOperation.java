package model;

public abstract class TwoPartOperation extends Operation{

	protected static final String ValueOpenBracket = "(";
	protected static final String ValueCloseBracket = ")";
	
	Expression firstArgument;
	Expression secondArgument;
	
	@Override
	public boolean equals (Object argument) {
		return super.equals(argument);
	}
	
	@Override
	public String toString(){
		try {
			return this.getName() + ValueOpenBracket + this.getValue() + ValueCloseBracket;
		} catch (DivisionByZeroException e) {
			throw new Error();
		}
	}

	@Override
	public int getValue() throws DivisionByZeroException {
		return this.state.getValue(this);
	}
	
	@Override
	public void update() throws DivisionByZeroException {
		this.state = ValueNotCachedState.getInstance();
		this.state.getValue(this);
		this.notifyObservers();
	}
	
	public Expression getFirstArgument() {
		return firstArgument;
	}

	public Expression getSecondArgument() {
		return secondArgument;
	}
	
	

}
