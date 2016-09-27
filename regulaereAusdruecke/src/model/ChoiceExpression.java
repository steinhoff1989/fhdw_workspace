package model;

import model.automat.Automaton;

public class ChoiceExpression extends CompositeExpression{

	public ChoiceExpression(RegularExpression regEx1, RegularExpression regEx2) {
		super(regEx1, regEx2);
	}

	@Override
	public Automaton toAutomat() {
		throw new UnsupportedOperationException();
	}

	@Override
	public RegularExpression concatenate(RegularExpression regEx) {
		return new ConcatenationExpression(this, regEx);
	}

	@Override
	public RegularExpression choice(RegularExpression regEx) {
		this.regExp.add(regEx);
		return this;
	}

}
