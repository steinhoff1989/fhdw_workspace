package model;

public class Transition {

	private State leftState;
	private State rightState;
	private char e;

	public Transition(State leftState, State rightState, char e) {
		this.leftState = leftState;
		this.rightState = rightState;
		this.e = e;
	}

	public State getLeftState() {
		return leftState;
	}

	public State getRightState() {
		return rightState;
	}

	public char getE() {
		return e;
	}

}
