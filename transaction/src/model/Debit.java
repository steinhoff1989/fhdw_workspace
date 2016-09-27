package model;

public class Debit extends Entry{

	public Debit(Transfer transfer){
		this.transfer = transfer;
	}
	
	@Override
	public <T> T acceptEntryVisitor(EntryVisitor<T> visitor) {
		return visitor.handleDebitEntry(this);
	}

}
