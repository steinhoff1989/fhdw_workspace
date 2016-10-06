package viewModel;

import model.AmountUnderLimitException;
import model.Credit;
import model.Debit;
import model.Entry;
import model.EntryVisitor;
import model.FakeEntry;

public class EntryView {

	protected static final String DebitPrefix = "Debit: ";
	protected static final String CreditPrefix = "Credit: ";

	public static EntryView create(Entry entry) {
		return new EntryView(entry);
	}
	private Entry entry;
	
	public EntryView(Entry entry) {
		this.entry = entry;
	}
	
	public String toString(){
		//TODO Implement reasonable string representation!!!
		try {
			return this.entry.acceptEntryVisitor(new EntryVisitor<String>(){
				@Override
				public String handleFakeEntry(FakeEntry fakeEntry) {
					return fakeEntry.getFake();
				}

				@Override
				public String handleDebitEntry(Debit debitEntry) {
					// TODO Auto-generated method stub
					return debitEntry.toString();
				}

				@Override
				public String handleCreditEntry(Credit creditEntry) {
					// TODO Auto-generated method stub
					return creditEntry.toString();
				}
			});
		} catch (AmountUnderLimitException e) {
			//should not be possible!
			throw new Error();
		}
	}

}
