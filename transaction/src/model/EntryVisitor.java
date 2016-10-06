package model;

public interface EntryVisitor<T> {

	T handleFakeEntry(FakeEntry fakeEntry);//TODO Remove in final project!!!
	T handleDebitEntry(Debit debitEntry) throws AmountUnderLimitException;
	T handleCreditEntry(Credit creditEntry) throws AmountUnderLimitException;

}
