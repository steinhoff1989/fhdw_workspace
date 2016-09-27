package model;

import java.util.Iterator;
import java.util.Set;

import model.automat.Automaton;
import model.automat.Transition;

public class ConcatenationExpression extends CompositeExpression{

	public ConcatenationExpression(RegularExpression regEx1, RegularExpression regEx2) {
		super(regEx1, regEx2);
	}

	@Override
	public Automaton toAutomat() {
		Automaton automat = regExp.get(0).toAutomat();
		
		Iterator<RegularExpression> i = regExp.iterator();
		while(i.hasNext()){
			RegularExpression current = i.next();
			if(current.equals(regExp.get(0))) continue ;
			automat.sequence(current.toAutomat());
		}
		
		if(this.getIterated()){
			Set<Transition> prevTransitions = automat.prevTransitions(automat.getEndzustand());
			Iterator<Transition> i1 = prevTransitions.iterator();
			while(i1.hasNext()){
				Transition current = i1.next();
				Transition newTransition = new Transition(current.getLeftState(), automat.getAnfangszustand(), current.getE());
				automat.addTransition(newTransition);
			}
		}
		
		return automat;
	}

	@Override
	public RegularExpression concatenate(RegularExpression regEx) {
		this.regExp.add(regEx);
		return this;
	}

	@Override
	public RegularExpression choice(RegularExpression regEx) {
		return new ChoiceExpression(this, regEx);
	}
}
