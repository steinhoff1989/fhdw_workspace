package model;

public class SuccessState extends TransferOrTransactionState{

	public SuccessState() {
		super();
	}

	@Override
	public void book(Transfer transfer) throws TransferAlreadyBookedException {
		throw new TransferAlreadyBookedException("Transfer is already booked.");
	}

}
