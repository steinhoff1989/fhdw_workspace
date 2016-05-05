package model;

public class Subtract extends TwoPartOperation{
	
	private final String SUBTRCAT = "-";
			
	public static Subtract create(Expression firstArgument, Expression secondArgument){
		return new Subtract(firstArgument, secondArgument);
	}
	
	public Subtract(Expression firstArgument, Expression secondArgument){
		this.firstArgument= firstArgument;
		this.secondArgument = secondArgument;
	}

	@Override
	public String getName() {
		return firstArgument.getName() + SUBTRCAT + secondArgument.getName();
	}

	@Override
	public int getValue() {
		return firstArgument.getValue() - secondArgument.getValue();
	}
}
