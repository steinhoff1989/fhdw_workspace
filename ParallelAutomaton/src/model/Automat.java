package model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Automat {

	private final State anfangszustand;
	private final State endzustand;
	private final Set<Transition> transitions;
	
	public Automat(){
		this.anfangszustand = new State(this);
		this.endzustand = new State(this);
		this.transitions = new HashSet<Transition>();
	}

	public State getAnfangszustand() {
		return this.anfangszustand;
	}

	public State getEndzustand() {
		return this.endzustand;
	}

//	public boolean recognizes(final String input){
//		Manager.getTheInstance().getOutput();
//		
////		return configuration.isEndConfiguration();
//	}

	public String run(final String input) throws NotRecognizedException, InterruptedException {
		final Configuration configuration = new Configuration(this, this.anfangszustand, input,"");
		Manager.getTheInstance().newThreadCreated(configuration);
		configuration.startThread();
		
		return Manager.getTheInstance().getOutput();
	}

	public void addTransition(final Transition transition){
		this.transitions.add(transition);
	}
	
	public Set<Transition> getPossibleTransitions(final char input, final State state){
		final Set<Transition> possibleTransitions = new HashSet<Transition>();
		
		final Iterator<Transition> i = this.transitions.iterator();
		while(i.hasNext()){
			final Transition current = i.next();
			if(current.getLeftState() == state && current.getE() == input){
				possibleTransitions.add(current);
			}
		}
		return possibleTransitions;
		
	}
	
}
