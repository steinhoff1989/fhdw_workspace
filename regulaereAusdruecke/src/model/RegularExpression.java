package model;

import model.automat.Automaton;

public abstract class RegularExpression {

	private Boolean iterated;

	public RegularExpression() {
		this.iterated = false;
	}

	public abstract Automaton toAutomat();

	public void setIterated(final Boolean iterated) {
		this.iterated = iterated;
	}

	public Boolean getIterated() {
		return this.iterated;
	}

	public boolean recognize(final String input) {
		if (input.equals(""))
			return true;

		final Automaton automat = this.toAutomat();
		if (this.iterated)
			automat.iterate();
		return automat.recognizes(input);
	}

	public abstract RegularExpression concatenate(RegularExpression regEx);

	public abstract RegularExpression choice(RegularExpression regEx);

	public RegularExpression iterate() {
		this.iterated = true;
		return this;
	}
}
