package model;

@SuppressWarnings("serial")
public class TransactionFailedException extends AccountException {
	public TransactionFailedException(String message) {
		super(message);
	}
}
