package model;

public class NotRecognizedElement implements WrappedElement {

	@Override
	public String getWrapped() throws NotRecognizedException {
		throw new NotRecognizedException();
	}

}
