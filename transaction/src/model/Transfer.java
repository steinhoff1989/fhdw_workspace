package model;

import viewModel.TransferOrTransactionView;

public class Transfer extends TransferOrTransaction {

	public static Transfer create(Account from, Account to,	long amount, String purpose) {
		return new Transfer(from, to, amount, purpose);
	}
	private Account fromAccount;
	private Account toAccount;
	final private long amount;
	final private String purpose;

	public Transfer(Account from, Account to, long amount, String purpose) {
		this.fromAccount = from;
		this.toAccount = to;
		this.amount = amount;
		this.purpose = purpose;
	}
	public Account getFromAccount() {
		return fromAccount;
	}
	public Account getToAccount() {
		return toAccount;
	}
	public long getAmount() {
		return amount;
	}
	public String getPurpose() {
		return this.purpose;
	}
	@Override
	public void book() throws AmountUnderLimitException, TransferAlreadyBookedException {
		this.state.book(this);
	}
	public void setFromAccount(Account fromAccount) {
		this.fromAccount = fromAccount;
		
	}
	public void setToAccount(Account toAccount) {
		this.toAccount = toAccount;
		
	}
	public void setState(TransferState newState) {
		this.state = newState;
	}

}
