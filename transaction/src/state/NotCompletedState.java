package state;

import exceptions.AmountUnderLimitException;
import model.Credit;
import model.Debit;
import model.Transfer;

public class NotCompletedState extends TransferState {

	public NotCompletedState() {
		super();
	}

	@Override
	public void book(Transfer transfer) throws AmountUnderLimitException {
		if (transfer.getAmount() >= 0) {
			transfer.getFromAccount().book(new Debit(transfer));
			transfer.getToAccount().book(new Credit(transfer));
		} else {
			transfer.getToAccount().book(new Credit(transfer));
			transfer.getFromAccount().book(new Debit(transfer));
		}
		transfer.setState(new SuccessState(this.failCounter));
	}

	@Override
	public String toString() {
		return "NotCompletedState";
	}
	
	
}
