package model;

public abstract class OperationState {

	public abstract int getValue(Operation operation) throws DivisionByZeroException;

}
