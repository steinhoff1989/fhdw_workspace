package model;

import model.automat.Automaton;

public abstract class RegularExpression {

	private Boolean iterated;

	public RegularExpression() {
		this.iterated = false;
	}

	public abstract Automaton toAutomat();
	
	public void setIterated(Boolean iterated) {
		this.iterated = iterated;
	}

	public Boolean getIterated() {
		return iterated;
	}

	public boolean recognize(String input){
		return this.toAutomat().recognizes(input);
	}
	
	public abstract RegularExpression concatenate(RegularExpression regEx);
	public abstract RegularExpression choice(RegularExpression regEx);

	public RegularExpression iterate(){
		this.iterated = true;
		return this;
	}
}
