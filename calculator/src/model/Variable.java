package model;


public class Variable extends AObservee implements Expression {
	
	private static final int InitialVariableValue = 0;
	private static final String ValueOpenBracket = "(";
	private static final String ValueCloseBracket = ")";
	private static final int IncrementValue = 1;

	public static Variable createVariable(final String name){
		return new Variable(name, InitialVariableValue);
	}
	
	private final String name;
	private int value;
	
	private Variable(final String name, final int initialValue){
		this.name = name;
		try {
			this.setValue(initialValue);
		} catch (DivisionByZeroException e) {
			throw new Error();
		}
	}
	public String getName() {
		return this.name;
	}

	@Override
	public boolean equals (Object argument) {
		return super.equals(argument);
	}
	@Override
	public String toString(){
		return this.getName() + ValueOpenBracket + this.getValue() + ValueCloseBracket;
	}
	public void up(){
		try {
			this.setValue(this.getValue() + IncrementValue);
		} catch (DivisionByZeroException e) {
			throw new Error();
		}
	}
	public void down(){
		try {
			this.setValue(this.getValue() - IncrementValue);
		} catch (DivisionByZeroException e) {
			throw new Error();
		}
	}
	public int getValue() {
		return this.value;
	}
	private void setValue(final int newValue) throws DivisionByZeroException {
		this.value = newValue;
		this.notifyObservers();
	}
}
