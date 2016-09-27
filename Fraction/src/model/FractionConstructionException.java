package model;

@SuppressWarnings("serial")
public class FractionConstructionException extends Exception {

	public FractionConstructionException(RuntimeException re){
		super(re);
	}
	public FractionConstructionException() {
		super();
	}
}
