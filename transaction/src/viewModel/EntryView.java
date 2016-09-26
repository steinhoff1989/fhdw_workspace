package viewModel;

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
		return this.entry.acceptEntryVisitor(new EntryVisitor<String>(){
			@Override
			public String handleFakeEntry(FakeEntry fakeEntry) {
				return fakeEntry.getFake();
			}
		});
	}

}
