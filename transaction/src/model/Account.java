package model;

//PENIS
//MUSCHI
import java.util.LinkedList;
import java.util.List;

import exceptions.AmountUnderLimitException;

/**
 * A simple account that possesses a list of account entries, the sum of which
 * constitutes the account's >balance>.
 */
public class Account {

	/**
	 * The balance of all accounts shall be greater or equal than this limit.
	 */
	public static final long UniversalAccountLimit = -1000;

	public static Account create(final String name) {
		return new Account(name);
	}

	private final String name;
	private long balance;
	List<Entry> accountEntries;
	private final List<AccountObserver> observers;

	/**
	 * Represents an Account with a given name. Starting value and accountEnties equals zero
	 * @param name: The name of the Account
	 */
	public Account(final String name) {
		this.name = name;
		this.balance = 0;
		this.accountEntries = new LinkedList<Entry>();
		this.observers = new LinkedList<AccountObserver>();
	}

	public long getBalance() {
		return this.balance;
	}

	public String getName() {
		return this.name;
	}

	public List<Entry> getAccountEntries() {
		return this.accountEntries;
	}

	public void register(final AccountObserver observer) {
		if (this.observers.contains(observer))
			return;
		this.observers.add(observer);
	}

	public void deregister(final AccountObserver observer) {
		this.observers.remove(observer);
	}

//	private void notifyObservers() {
//		final Iterator<AccountObserver> currentObservers = this.observers.iterator();
//		while (currentObservers.hasNext())
//			currentObservers.next().update();
//	}

	public void book(final Entry e) throws AmountUnderLimitException {
		e.acceptEntryVisitor(new EntryVisitor<Boolean>() {
			@Override
			public Boolean handleDebitEntry(final Debit e) throws AmountUnderLimitException {
				if (Account.this.balance - e.transfer.getAmount() >= UniversalAccountLimit) {
					Account.this.balance = Account.this.balance - e.transfer.getAmount();
					return true;
				} else {
					e.transfer.state.incrementCounter();
					throw new AmountUnderLimitException("Booking not possible. Booking undershot the limit by " + (((Account.this.balance - e.transfer.getAmount()) - UniversalAccountLimit) *(-1)));
				}
			}

			@Override
			public Boolean handleCreditEntry(final Credit e) throws AmountUnderLimitException {
				if (Account.this.balance + e.transfer.getAmount() >= UniversalAccountLimit) {
					Account.this.balance = Account.this.balance + e.transfer.getAmount();
					return true;
				}else{
					e.transfer.state.incrementCounter();
					throw new AmountUnderLimitException("Booking not possible. Booking undershot the limit by " + ((Account.this.balance + e.transfer.getAmount() - UniversalAccountLimit)  * (-1)));
				}
			}
		});
		this.accountEntries.add(e);
	}
}
