package model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Automat {

	private State anfangszustand;
	private State endzustand;
	//private Set<State> states;
	private Set<Transition> transitions;
	
	public Automat(){
		this.anfangszustand = new State(this);
		this.endzustand = new State(this);
		//this.states = new HashSet<State>();
		this.transitions = new HashSet<Transition>();
	}

	public State getAnfangszustand() {
		return anfangszustand;
	}

	public State getEndzustand() {
		return endzustand;
	}

	public boolean recognizes(String input){
		Set<State> z0Set = new HashSet<State>();
		z0Set.add(this.anfangszustand);
		
		Configuration configuration = new Configuration(this, z0Set);
		configuration.run(input);
		return configuration.isEndConfiguration();
	}
	
	public void addTransition(Transition transition){
		this.transitions.add(transition);
	}
	
	public Set<State> nextStates(char input, State state){
		Set<State> nextStates = new HashSet<State>();
		
		Iterator<Transition> i = transitions.iterator();
		while(i.hasNext()){
			Transition current = i.next();
			if(current.getLeftState() == state && current.getE() == input){
				nextStates.add(current.getRightState());
			}
		}
		
		return nextStates;
		
	}
	
}
