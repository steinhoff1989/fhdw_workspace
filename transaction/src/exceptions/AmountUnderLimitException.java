package exceptions;

@SuppressWarnings("serial")
public class AmountUnderLimitException extends AccountException {

	public AmountUnderLimitException(String message) {
		super(message);
	}	
	
}
