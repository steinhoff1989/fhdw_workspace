package model;

public class Credit extends Entry{

	public Credit(Transfer transfer){
		this.transfer = transfer;
	}
	
	@Override
	public <T> T acceptEntryVisitor(EntryVisitor<T> visitor) {
		return visitor.handleCreditEntry(this);
	}

}
