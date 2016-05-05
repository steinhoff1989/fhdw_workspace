package model;

public class Add extends TwoPartOperation{

	private final String ADD = "+";
	
	public static Add create(Expression firstArgument, Expression secondArgument){
		return new Add(firstArgument, secondArgument);
	}
	
	public Add(Expression firstArgument, Expression secondArgument){
		this.firstArgument= firstArgument;
		this.secondArgument = secondArgument;
	}

	@Override
	public String getName() {
		return firstArgument.getName() + ADD + secondArgument.getName();
	}

	@Override
	public int getValue() {
		return firstArgument.getValue() + secondArgument.getValue();
	}
}
