package model;

public class Transition {

	private final State leftState;
	private final State rightState;
	private final char e;
	private final char a;

	/**
	 * Represents a Transition between <leftState> and <rightState> with the input character <e> and output character <a> 
	 * @param leftState: The state from which the transition starts
	 * @param rightState: The state where the transition ends
	 * @param e: input character to do the transition
	 * @param a: output character for this transition
	 */
	public Transition(final State leftState, final State rightState, final char e, final char a) {
		this.leftState = leftState;
		this.rightState = rightState;
		this.e = e;
		this.a = a;
	}

	/**
	 * Returns the starting <leftState> of this transition object.
	 */
	public State getLeftState() {
		return this.leftState;
	}

	/**
	 * Returns the ending <rightState> of this transition object.
	 */
	public State getRightState() {
		return this.rightState;
	}

	/**
	 * Returns the input <e> from this transition
	 */
	public char getE() {
		return this.e;
	}

	/**
	 * Returns the output <a> from this transition
	 */
	public char getA() {
		return this.a;
	}

}
