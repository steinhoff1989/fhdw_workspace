package model;

public class Divide extends TwoPartOperation{

	private final String DIVIDE = "/";
	
	public static Divide create(Expression firstArgument, Expression secondArgument) throws CreationWithSeconArgumentZeroException{
		if(secondArgument.getValue() == 0){
			throw new CreationWithSeconArgumentZeroException();
		}
		return new Divide(firstArgument, secondArgument);
	}
	
	public Divide(Expression firstArgument, Expression secondArgument){
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
	public int getValue2(){
		if(secondArgument.getValue() == 0){
			//throw new DivisionByZeroException();
		}
		return firstArgument.getValue() / secondArgument.getValue();
	}
}
