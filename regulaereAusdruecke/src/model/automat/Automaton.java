package model.automat;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Automaton {

	private State anfangszustand;
	private State endzustand;
	private Set<Transition> transitions;

	public Automaton() {
		this.anfangszustand = new State(this, "z0");
		this.endzustand = new State(this, "ze");
		this.transitions = new HashSet<Transition>();
	}

	public State getAnfangszustand() {
		return anfangszustand;
	}

	public State getEndzustand() {
		return endzustand;
	}

	/**
	 * Proofs if <this> automaton is able to recognize <input>
	 * 
	 * @param input:
	 *            The input that will be tested for recognizability
	 * @return Returns true if, and only if <this> automaton is able to
	 *         recognize <input>
	 */
	public boolean recognizes(String input) {
		Set<State> z0Set = new HashSet<State>();
		z0Set.add(this.anfangszustand);

		Configuration configuration = new Configuration(this, z0Set);
		configuration.run(input);
		return configuration.isEndConfiguration();
	}

	/**
	 * Adds the new Transition <transition> to the list <transitions>
	 * 
	 * @param transition
	 */
	public void addTransition(Transition transition) {
		this.transitions.add(transition);
	}

	/**
	 * Returns all nextStates to the given <state> with input <input>
	 * 
	 * @param input:
	 *            The input character to go to the next state
	 * @param state:
	 *            The state from which this function return the next States with
	 *            <input>
	 * @return
	 */
	public Set<State> nextStates(char input, State state) {
		Set<State> nextStates = new HashSet<State>();

		Iterator<Transition> i = transitions.iterator();
		while (i.hasNext()) {
			Transition current = i.next();
			if (current.getLeftState() == state && current.getE() == input) {
				nextStates.add(current.getRightState());
			}
		}
		return nextStates;
	}

	public Set<State> prevStates(char input, State state) {
		Set<State> prevStates = new HashSet<State>();

		Iterator<Transition> i = transitions.iterator();
		while (i.hasNext()) {
			Transition current = i.next();
			if (current.getRightState() == state && current.getE() == input) {
				prevStates.add(current.getLeftState());
			}
		}
		return prevStates;
	}

	public Set<Transition> prevTransitions(State state) {
		Set<Transition> prevTransitions = new HashSet<Transition>();

		Iterator<Transition> i = transitions.iterator();
		while (i.hasNext()) {
			Transition current = i.next();
			if (current.getRightState() == state) {
				prevTransitions.add(current);
			}
		}
		return prevTransitions;
	}

	/**
	 * Concatenates <automaton> to <this> automaton
	 * 
	 * @param automat:
	 *            The automaton that will be concatenating to <this> automaton
	 */
	public void sequence(Automaton automat) {
		this.transitions.addAll(automat.transitions);
		restructureLastTransitionsSequence(automat);
		this.endzustand = automat.endzustand;
	}

	/**
	 * Changes all transitions pointing on a final state to point to the
	 * starting state of <a>
	 * 
	 * @param a:
	 *            The automaton that will be concatenated to <this> one
	 */
	private void restructureLastTransitionsSequence(Automaton automat) {
		Iterator<Transition> i = transitions.iterator();
		while (i.hasNext()) {
			Transition current = i.next();
			if (current.getRightState() == this.getEndzustand()) {
				current.setRightState(automat.getAnfangszustand());
			}
		}
	}

	/**
	 * Allows <this> automaton to accept not only the Language of
	 * <this> automaton but also the Language of <automat>
	 * 
	 * @param automat:
	 *            The Automat which Language will be accepted too
	 */
	public void choice(Automaton automat) {
		restructureFirstTransitionsChoice(automat);
		restructureLastTransitionsChoice(automat);
		this.transitions.addAll(automat.transitions);
	}

	/**
	 * changes all first transitions from <automat> starting from the starting
	 * state of <this> automaton
	 * 
	 * @param automat:
	 *            The automaton of which all first transitions will be changed
	 *            to start from the starting state of <this> automaton
	 */
	private void restructureFirstTransitionsChoice(Automaton automat) {
		Iterator<Transition> i = automat.transitions.iterator();
		while (i.hasNext()) {
			Transition current = i.next();
			if (current.getLeftState() == automat.anfangszustand) {
				current.setLeftState(this.anfangszustand);
			}
		}
	}

	private void restructureLastTransitionsChoice(Automaton automat) {
		Iterator<Transition> i = automat.transitions.iterator();
		while (i.hasNext()) {
			Transition current = i.next();
			if (current.getRightState() == automat.endzustand) {
				current.setRightState(this.endzustand);
			}
		}
	}

	/**
	 * Sets the possibility for <this> automaton to recognize every iteration of
	 * any recognized <input>
	 */
	public void iterate() {
		Transition t = null;
		
		Iterator<Transition> iterator = transitions.iterator();
		while(iterator.hasNext()){
			Transition current = iterator.next();
			
			if(current.getLeftState().equals(this.anfangszustand)){
				t = new Transition(this.endzustand, current.getRightState(), current.getE());
			}
		}
		
		if(t!=null){
			this.transitions.add(t);		
		}
		
		//this.iterate = true;
		// add a transition from (ze,z0, epsilon) achtung: muss noch angepasst
		// werden. keine epsilon übergänge
	}

}
