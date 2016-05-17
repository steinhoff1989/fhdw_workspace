package model;

public class ValueNotCachedState extends OperationState{

	private static ValueNotCachedState instance;
	
	private ValueNotCachedState(){};
	
	public static ValueNotCachedState getInstance(){
		if(instance == null){
			instance = new ValueNotCachedState();
		}
		return instance;
	}

	@Override
	public int getValue(Operation operation) throws DivisionByZeroException {
		return operation.calculateValue();
	}
}
