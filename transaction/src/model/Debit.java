package model;

public class Debit extends Entry{

	public Debit(Transfer transfer){
		this.transfer = transfer;
	}
	
	@Override
	public <T> T acceptEntryVisitor(EntryVisitor<T> visitor) throws AmountUnderLimitException {
		return visitor.handleDebitEntry(this);
	}

}
