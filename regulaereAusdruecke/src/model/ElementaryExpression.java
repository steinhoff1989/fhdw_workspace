package model;

import model.automat.Automaton;
import model.automat.Transition;

public class ElementaryExpression extends RegularExpression{

	private char c;

	public ElementaryExpression(char c) {
		this.c = c;
	}

	@Override
	public Automaton toAutomat() {
		Automaton automat = new Automaton();
		Transition t01 = new Transition(automat.getAnfangszustand(), automat.getEndzustand(), this.c);
		automat.addTransition(t01);

		if(this.getIterated()){
			Transition t10 = new Transition(automat.getEndzustand(), automat.getEndzustand(), this.c);
			automat.addTransition(t10);
		}
		
		return automat;
	}
	
	public RegularExpression concatenate(RegularExpression regEx){
		return new ConcatenationExpression(this, regEx);
	}
	public RegularExpression choice(RegularExpression regEx){
		return new ChoiceExpression(this, regEx);
	}
}
