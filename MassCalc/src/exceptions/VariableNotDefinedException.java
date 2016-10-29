package exceptions;

@SuppressWarnings("serial")
public class VariableNotDefinedException extends Exception {

	String message;
	
	public VariableNotDefinedException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}
