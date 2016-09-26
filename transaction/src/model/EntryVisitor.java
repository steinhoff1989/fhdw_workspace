package model;

public interface EntryVisitor<T> {

	T handleFakeEntry(FakeEntry fakeEntry);//TODO Remove in final project!!!
	T handleDebitEntry(Debit debitEntry);
	T handleCreditEntry(Credit creditEntry);

}
