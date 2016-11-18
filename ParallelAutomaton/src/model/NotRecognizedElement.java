package model;

public class NotRecognizedElement implements WrappedElement {

	public String getWrapped() throws NotRecognizedException {
		throw new NotRecognizedException();
	}

}
