package model;

public class Transfer implements TransferOrTransaction {

	public static Transfer create(Account from, Account to,	long amount, String purpose) {
		return new Transfer(from, to, amount, purpose);
	}
	final private Account fromAccount;
	final private Account toAccount;
	final private long amount;
	final private String purpose;

	public Transfer(Account from, Account to, long amount, String purpose) {
		this.fromAccount = from;
		this.toAccount = to;
		this.amount = amount;
		this.purpose = purpose;
	}
	public Account getFromAccount() {
		return fromAccount;
	}
	public Account getToAccount() {
		return toAccount;
	}
	public long getAmount() {
		return amount;
	}
	public String getPurpose() {
		return this.purpose;
	}
	@Override
	public void book() {
		if(amount>=0){
			try {
				fromAccount.book(new Debit(this));
				toAccount.book(new Credit(this));
			} catch (AmountUnderLimitException e) {
				//TODO: COUNT FAIL
				System.out.println("Booking of transfer failed!");
				return;
			}
		}else{
			try {
				toAccount.book(new Credit(this));
				fromAccount.book(new Debit(this));
			} catch (AmountUnderLimitException e) {
				//TODO: COUNT FAIL
				System.out.println("Booking of transfer failed!");
				return;
			}
		}
		System.out.println("Booking of transfer finished!");
	}

}
