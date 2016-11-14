package model;

public class State {

	Automat automat;
	
	public State(Automat automat) {
		this.automat = automat;
	}

	public void add(char c, State s)
	{
		throw new UnsupportedOperationException();
	}
	
}
