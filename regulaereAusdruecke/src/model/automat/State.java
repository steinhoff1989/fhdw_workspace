package model.automat;

public class State {

	Automaton automat;
	String name;
	
	public State(Automaton automat, String name) {
		this.automat = automat;
		this.name = name;
	}

	public String toString(){
		return this.name;
	}
	
}
