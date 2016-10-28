package model;

@SuppressWarnings("serial")
public class NoMoreBufferCopyAvailableException extends Exception {

	private String message;

	public NoMoreBufferCopyAvailableException(String message) {
		super();
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}

}
