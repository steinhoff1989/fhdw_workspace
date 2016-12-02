package model;

public interface WrappedElement {

	/**
	 * Returns the wrapped Element
	 * @throws NotRecognizedException gets thrown if the wrapped Element is a NotrecognizedElement
	 */
	String getWrapped() throws NotRecognizedException;
}
