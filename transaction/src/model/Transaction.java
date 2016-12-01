package model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import exceptions.AmountUnderLimitException;
import exceptions.TransactionFailedException;
import exceptions.TransferAlreadyBookedException;
import state.NotCompletedState;

public class Transaction extends TransferOrTransaction {

	public static Transaction create() {
		return new Transaction();
	}

	private final List<Transfer> transfers;

	private Transaction() {
		this.transfers = new LinkedList<Transfer>();
	}

	public void addTransfer(final Transfer transfer) {
		this.transfers.add(transfer);
	}

	public List<Transfer> getTransfers() {
		return this.transfers;
	}

	@Override
	public void book() throws AmountUnderLimitException, TransactionFailedException, TransferAlreadyBookedException {

		final Stack<Transfer> bookStack = new Stack<Transfer>();
		final Iterator<Transfer> i = this.transfers.iterator();
		try {
			while (i.hasNext()) {
				final Transfer current = i.next();
				current.book();
				bookStack.push(current);
			}
		} catch (AmountUnderLimitException | TransferAlreadyBookedException e) {
			while (!bookStack.isEmpty()) {
				final Transfer rebook = bookStack.pop();
				final Transfer reBookTransfer = Transfer.create(rebook.getToAccount(), rebook.getFromAccount(), rebook.getAmount(), rebook.getPurpose());
				reBookTransfer.book();
				rebook.setState(new NotCompletedState());
			}
			throw new TransactionFailedException("One transfer in transaction failed.");
		}

	}
}
