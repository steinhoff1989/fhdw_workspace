package model;

public class Add extends TwoPartOperation{

	private final String ADD = "+";
	
	public static Add create(Expression firstArgument, Expression secondArgument){
		return new Add(firstArgument, secondArgument);
	}
	
	public Add(Expression firstArgument, Expression secondArgument){
		this.firstArgument= firstArgument;
		this.secondArgument = secondArgument;
		this.firstArgument.register(this);
		this.secondArgument.register(this);
	}

	@Override
	public String getName() {
		return firstArgument.getName() + ADD + secondArgument.getName();
	}

	@Override
	public int calculateValue() throws DivisionByZeroException {
		return this.firstArgument.getValue() + this.secondArgument.getValue();
	}
	
}
