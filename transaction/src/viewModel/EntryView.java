package viewModel;

import exceptions.AmountUnderLimitException;
import model.Credit;
import model.Debit;
import model.Entry;
import model.EntryVisitor;

public class EntryView {

	protected static final String DebitPrefix = "Debit: ";
	protected static final String CreditPrefix = "Credit: ";

	public static EntryView create(final Entry entry) {
		return new EntryView(entry);
	}
	private final Entry entry;
	
	public EntryView(final Entry entry) {
		this.entry = entry;
	}
	
	@Override
	public String toString(){
		try {
			return this.entry.acceptEntryVisitor(new EntryVisitor<String>(){
				@Override
				public String handleDebitEntry(final Debit debitEntry) {
					return debitEntry.toString();
				}

				@Override
				public String handleCreditEntry(final Credit creditEntry) {
					return creditEntry.toString();
				}
			});
		} catch (final AmountUnderLimitException e) {
			//should not be possible!
			throw new Error();
		}
	}

}
