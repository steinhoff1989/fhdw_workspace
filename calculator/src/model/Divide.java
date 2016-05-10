package model;

public class Divide extends TwoPartOperation{

	private final String DIVIDE = "/";
	
	public static Divide create(Expression firstArgument, Expression secondArgument){
		return new Divide(firstArgument, secondArgument);
	}
	
	private Divide(Expression firstArgument, Expression secondArgument){
		this.firstArgument= firstArgument;
		this.secondArgument = secondArgument;
		this.firstArgument.register(this);
		this.secondArgument.register(this);
	}

	@Override
	public String getName() {
		return firstArgument.getName() + DIVIDE + secondArgument.getName();
	}
	
	@Override
	public int calculateValue() throws DivisionByZeroException{
		if(this.secondArgument.getValue() == 0){
			throw new DivisionByZeroException();
		}
		int value = this.firstArgument.getValue() / this.secondArgument.getValue();
		this.setState(new ValueCachedState(value));
		return value;
	}
}
