package model;

public class WrappedOutput {

	private WrappedElement output;
	
	public boolean isEmpty() {
		return this.output == null;
	}

	public String getOutput() throws NotRecognizedException {
		return this.output.getWrapped();
	}

	public void notRecognized() {
		this.output = new NotRecognizedElement();
	}

	public void setOutput(final String output) {
		this.output = new Output(output);
	}

}
