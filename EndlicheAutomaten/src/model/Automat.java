package model;

import java.util.ArrayList;
import java.util.List;

public class Automat {

	private State anfangszustand;
	private State endzustand;
	private List<State> states;
	
	public Automat(State anfangszustand, State endzustand){
		this.anfangszustand = anfangszustand;
		this.endzustand = endzustand;
		this.states = new ArrayList<State>();
	}
	
}
