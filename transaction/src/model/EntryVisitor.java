package model;

import exceptions.AmountUnderLimitException;

public interface EntryVisitor<T> {

	T handleDebitEntry(Debit debitEntry) throws AmountUnderLimitException;
	T handleCreditEntry(Credit creditEntry) throws AmountUnderLimitException;

}
