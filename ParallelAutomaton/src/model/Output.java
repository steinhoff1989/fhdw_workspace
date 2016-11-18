package model;

public class Output implements WrappedElement {

	final String output;
	
	public Output(final String output) {
		this.output = output;
	}

	public String getWrapped() throws NotRecognizedException {
		return this.output;
	}

}
