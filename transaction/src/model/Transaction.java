package model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class Transaction extends TransferOrTransaction {

	Stack<Transfer> bookStack;

	public static Transaction create() {
		return new Transaction();
	}

	private List<Transfer> transfers;

	private Transaction() {
		this.transfers = new LinkedList<Transfer>();
		this.bookStack = new Stack<Transfer>();
	}

	public void addTransfer(Transfer transfer) {
		this.transfers.add(transfer);
	}

	public List<Transfer> getTransfers() {
		return this.transfers;
	}

	@Override
	public void book() throws AmountUnderLimitException, TransactionFailedException, TransferAlreadyBookedException {

		Iterator<Transfer> i = transfers.iterator();
		try {
			while (i.hasNext()) {
				Transfer current = i.next();
				current.book();
				bookStack.push(current);
			}
		} catch (AmountUnderLimitException | TransferAlreadyBookedException e) {
			Iterator<Transfer> i2 = bookStack.iterator();
			while (i2.hasNext()) {
				Transfer rebook = i2.next();
				Transfer reBookTransfer = new Transfer(rebook.getToAccount(), rebook.getFromAccount(), rebook.getAmount(), rebook.getPurpose());
				reBookTransfer.book();
			}
			throw new TransactionFailedException("One transfer in transaction failed.");
		}

	}
}
