package model;

public abstract class Operation extends AObservee implements Expression, Observer  {

	public OperationState state;
	
	public Operation(){
		this.state = ValueNotCachedState.getInstance();
	}
	
	public void setState(OperationState state){
		this.state = state;
	}
	
	public abstract int getValue2();
}
