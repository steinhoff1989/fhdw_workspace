package model;

/** An entry (debit or credit) for an account, typically result of a booking process. */
public abstract class Entry {
	
	Transfer transfer;

	abstract public <T> T acceptEntryVisitor(EntryVisitor<T> visitor);
	
}
