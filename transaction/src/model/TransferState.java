package model;

public abstract class TransferState {

	protected int failCounter;

	public TransferState() {
		super();
		this.failCounter = 0;
	}
	
	public void incrementCounter(){
		this.failCounter += 1;
	}
	
	public int getTries(){
		return this.failCounter;
	}
	
	public abstract void book(Transfer transfer) throws AmountUnderLimitException, TransferAlreadyBookedException;

}
