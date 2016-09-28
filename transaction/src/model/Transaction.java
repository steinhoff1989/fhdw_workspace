package model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Transaction implements TransferOrTransaction {

	public static Transaction create() {
		return new Transaction();
	}

	private List<Transfer> transfers;
	
	private Transaction() {
		this.transfers = new LinkedList<Transfer>();
	}
	public void addTransfer(Transfer transfer) {
		this.transfers.add(transfer);
	}
	public List<Transfer> getTransfers() {
		return this.transfers;
	}
	@Override
	public void book() {
		
		Iterator<Transfer> i = transfers.iterator();
		while(i.hasNext()){
			Transfer current = i.next();
			
			current.book();
		}
		

	}
}
