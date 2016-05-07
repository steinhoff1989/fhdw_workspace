package model;

public class Multiply extends TwoPartOperation{

	private final String MULTIPLY = "*";
	
	public static Multiply create(Expression firstArgument, Expression secondArgument){
		return new Multiply(firstArgument, secondArgument);
	}
	
	public Multiply(Expression firstArgument, Expression secondArgument){
		this.firstArgument= firstArgument;
		this.secondArgument = secondArgument;
		this.firstArgument.register(this);
		this.secondArgument.register(this);
	}

	@Override
	public String getName() {
		return firstArgument.getName() + MULTIPLY + secondArgument.getName();
	}

	@Override
	public int getValue2() {
		return firstArgument.getValue() * secondArgument.getValue();
	}
	
	
}
