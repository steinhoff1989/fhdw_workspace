package model;

public class ValueCachedState extends OperationState{
	
	private int value;
	
	public ValueCachedState(int value) {
		this.value = value;
	}
	
	@Override
	public int getValue(Operation operation) {
		return value;
	}
}
