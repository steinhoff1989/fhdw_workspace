package model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Transaction extends TransferOrTransaction {

	public static Transaction create() {
		return new Transaction();
	}

	private List<Transfer> transfers;

	private Transaction() {
		this.transfers = new LinkedList<Transfer>();
	}

	public void addTransfer(Transfer transfer) {
		this.transfers.add(transfer);
	}

	public List<Transfer> getTransfers() {
		return this.transfers;
	}

	@Override
	public void book() throws AmountUnderLimitException, TransactionFailedException, TransferAlreadyBookedException {

		Stack<Transfer> bookStack = new Stack<Transfer>();
		Iterator<Transfer> i = transfers.iterator();
		try {
			while (i.hasNext()) {
				Transfer current = i.next();
				current.book();
				bookStack.push(current);
			}
		} catch (AmountUnderLimitException | TransferAlreadyBookedException e) {
			while (!bookStack.isEmpty()) {
				Transfer rebook = bookStack.pop();
				Transfer reBookTransfer = new Transfer(rebook.getToAccount(), rebook.getFromAccount(), rebook.getAmount(), rebook.getPurpose());
				reBookTransfer.book();
				rebook.setState(new NotCompletedState());
			}
			throw new TransactionFailedException("One transfer in transaction failed.");
		}

	}
}
