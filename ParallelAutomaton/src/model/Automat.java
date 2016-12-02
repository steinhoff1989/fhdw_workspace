package model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Automat {

	private final State anfangszustand;
	private final State endzustand;
	private final Set<Transition> transitions;
	
	/**
	 * Creates an automaton object with a fixed <anfangszustand> and <endzustand>
	 */
	public Automat(){
		this.anfangszustand = new State(this);
		this.endzustand = new State(this);
		this.transitions = new HashSet<Transition>();
	}


	/**
	 * Calculates one possible output that can be created with the given <input>
	 * @param input: the input for the automat from with one possible output will be returned.
	 * @return
	 * @throws NotRecognizedException if <input> is not an accepted input of the automat.
	 * @throws InterruptedException if a thread gets interrupted from outside.
	 */
	public String run(final String input) throws NotRecognizedException, InterruptedException {
		final Configuration configuration = new Configuration(this, this.anfangszustand, input,"");
		Manager.getTheInstance().newThreadCreated(configuration);
		configuration.startThread();
		
		return Manager.getTheInstance().getOutput();
	}

	/**
	 * Adds an Transition object to automatons set of transitions
	 * @param transition: The transition object that will be added.
	 */
	public void addTransition(final Transition transition){
		this.transitions.add(transition);
	}
	
	/**
	 * Calculates all possible Transitions from <state> with the input character <input>
	 * @param input: The character for which all possible transitions from <state> will be calculated.
	 * @param state: The state from which all Transitions with given <input> will be calculated.
	 * @return
	 */
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

	public State getAnfangszustand() {
		return this.anfangszustand;
	}
	
	public State getEndzustand() {
		return this.endzustand;
	}
}
