package state;

import exceptions.TransferAlreadyBookedException;
import model.Transfer;

public class SuccessState extends TransferState{

	/**
	 * Represents a success state of a transfer
	 * @param failCount
	 */
	public SuccessState(final int failCount) {
		super();
		this.failCounter = failCount;
	}

	@Override
	public void book(final Transfer transfer) throws TransferAlreadyBookedException {
		throw new TransferAlreadyBookedException("Transfer is already booked.");
	}

	@Override
	public String toString() {
		return "SuccessState";
	}
}
