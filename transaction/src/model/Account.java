package model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/** A simple account that possesses a list of account entries, 
 *  the sum of which constitutes the account's >balance>.
 */
public class Account {
	
	/** The balance of all accounts shall be greater or equal than this limit. */
	public static final long UniversalAccountLimit = -1000; 

	public static Account create(String name) {
		return new Account(name);
	}

	private String name;
	private long balance;
	List<Entry> accountEntries;
	private List<AccountObserver> observers;

	public Account(String name) {
		this.name = name;
		this.balance = 0;
		this.accountEntries = new LinkedList<Entry>();
		this.observers = new LinkedList<AccountObserver>();
		this.initialise(); //TODO Remove in final project!!!
	}
	//TODO Remove in final project!!!
	private void initialise() { 
		for (int i = 0; i < this.name.length();i++) this.accountEntries.add(FakeEntry.create(name, i));
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
	public void register(AccountObserver observer) {
		if (this.observers.contains(observer)) return;
		this.observers.add(observer);
	}
	public void deregister(AccountObserver observer) {
		this.observers.remove(observer);
	}
	private void notifyObservers() {
		Iterator<AccountObserver> currentObservers = this.observers.iterator();
		while (currentObservers.hasNext()) currentObservers.next().update();
	}
}
