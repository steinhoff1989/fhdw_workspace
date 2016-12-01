package exceptions;

@SuppressWarnings("serial")
public class TransferAlreadyBookedException extends AccountException {

	public TransferAlreadyBookedException(final String message) {
		super(message);
	}
}
