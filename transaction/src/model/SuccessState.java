package model;

public class SuccessState extends TransferState{

	public SuccessState(int failCount) {
		super();
		this.failCounter = failCount;
	}

	@Override
	public void book(Transfer transfer) throws TransferAlreadyBookedException {
		throw new TransferAlreadyBookedException("Transfer is already booked.");
	}

	@Override
	public String toString() {
		return "SuccessState";
	}
}
