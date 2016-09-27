package model.automat;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Configuration {

	Automaton myAutomat;
	Set<State> currentStates;

	public Configuration(Automaton myAutomat, Set<State> currentStates) {
		this.myAutomat = myAutomat;
		this.currentStates = currentStates;
	}

	public void step(String input){
		Set<State> nextStates = new HashSet<State>();
		Iterator<State> i = currentStates.iterator();
		while(i.hasNext()){
			State current = i.next();
			nextStates.addAll(myAutomat.nextStates(input.charAt(0), current));
		}
		this.currentStates = nextStates;
		input = input.substring(1);
		this.run(input);
	}
	
	public void run(String input){
		if(input.equals("")){
			return;
		}else{
			this.step(input);
		}
	}
	
	public boolean isEndConfiguration(){
		return currentStates.contains(myAutomat.getEndzustand());
	}
	
//	public Set<State> getCurrentStates(){
//		throw new UnsupportedOperationException();
//	}

}
