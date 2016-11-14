package model;

public class Transition {

	private final State leftState;
	private final State rightState;
	private final char e;
	private final char a;

	public Transition(final State leftState, final State rightState, final char e, final char a) {
		this.leftState = leftState;
		this.rightState = rightState;
		this.e = e;
		this.a = a;
	}

	public State getLeftState() {
		return this.leftState;
	}

	public State getRightState() {
		return this.rightState;
	}

	public char getE() {
		return this.e;
	}

	public char getA() {
		return this.a;
	}

}
