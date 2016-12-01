package state;

import exceptions.AmountUnderLimitException;
import exceptions.TransferAlreadyBookedException;
import model.Transfer;

public abstract class TransferState {

	protected int failCounter;

	/**
	 * Represents a transfer state that counts how many times the transfer failed
	 */
	public TransferState() {
		super();
		this.failCounter = 0;
	}
	
	/**
	 * increments the counter that represents how many times the transfer failed
	 */
	public void incrementCounter(){
		this.failCounter += 1;
	}
	
	/**
	 * returns the number of tries this transfer was tried to book
	 * @return
	 */
	public int getTries(){
		return this.failCounter;
	}
	
	/**
	 * Books a transfer object
	 * @param transfer
	 * @throws AmountUnderLimitException
	 * @throws TransferAlreadyBookedException
	 */
	public abstract void book(Transfer transfer) throws AmountUnderLimitException, TransferAlreadyBookedException;

}
