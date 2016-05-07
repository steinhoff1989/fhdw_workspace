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
		return this.getName() + ValueOpenBracket + this.getValue() + ValueCloseBracket;
	}
	
	@Override
	public void update() {
		//this.state.getValue(this);
		this.state = ValueNotCachedState.getInstance();
		this.state.getValue(this);
		this.notifyObservers();
	}
	
	@Override
	public int getValue() {
		return this.state.getValue(this);
	}
}
