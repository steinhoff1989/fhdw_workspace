package model;

import exceptions.AmountUnderLimitException;
import exceptions.TransferAlreadyBookedException;
import state.TransferState;

public class Transfer extends TransferOrTransaction {

	private Account fromAccount;
	private Account toAccount;
	final private long amount;
	final private String purpose;

	/**
	 * Creates a transfer object that can be booked
	 * @param from: The account from that the transfer shall be booked
	 * @param to: The account to which the transfer shall be booked
	 * @param amount: The amount that shall be booked
	 * @param purpose: The purpose of this transfer
	 */
	public static Transfer create(final Account from, final Account to,	final long amount, final String purpose) {
		return new Transfer(from, to, amount, purpose);
	}
	
	/**
	 * Represents a transfer object that can be booked
	 * @param from: The account from that the transfer shall be booked
	 * @param to: The account to which the transfer shall be booked
	 * @param amount: The amount that shall be booked
	 * @param purpose: The purpose of this transfer
	 */
	private Transfer(final Account from, final Account to, final long amount, final String purpose) {
		this.fromAccount = from;
		this.toAccount = to;
		this.amount = amount;
		this.purpose = purpose;
	}
	
	public Account getFromAccount() {
		return this.fromAccount;
	}
	
	public Account getToAccount() {
		return this.toAccount;
	}
	
	public long getAmount() {
		return this.amount;
	}
	
	public String getPurpose() {
		return this.purpose;
	}
	
	@Override
	public void book() throws AmountUnderLimitException, TransferAlreadyBookedException {
		this.state.book(this);
	}
	
	public void setFromAccount(final Account fromAccount) {
		this.fromAccount = fromAccount;
		
	}
	
	public void setToAccount(final Account toAccount) {
		this.toAccount = toAccount;
	}

	public void setState(final TransferState newState) {
		this.state = newState;
	}
}
