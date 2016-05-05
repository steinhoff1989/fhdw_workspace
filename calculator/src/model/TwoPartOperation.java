package model;

public abstract class TwoPartOperation implements Operation{

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
}
