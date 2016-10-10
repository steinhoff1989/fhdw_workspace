package model;

public abstract class TransferOrTransactionState {

	protected int failCounter;

	public TransferOrTransactionState() {
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
