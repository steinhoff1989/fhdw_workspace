package exceptions;

@SuppressWarnings("serial")
public class NoMoreVariablesAvailableException extends Exception {

	private String message;

	public NoMoreVariablesAvailableException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

}
