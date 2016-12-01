package exceptions;

@SuppressWarnings("serial")
public class TransactionFailedException extends AccountException {
	public TransactionFailedException(String message) {
		super(message);
	}
}
