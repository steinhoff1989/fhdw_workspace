package model;

import java.util.Iterator;
import java.util.Set;

public class Configuration extends Process{

	Automat myAutomat;
	State currentState; 
	String input;	
	String output;
	
	/**
	 * Represents a active configuration object that checks if <input> is a valid input for the corresponding <automaton>
	 * @param myAutomat: The corresponding automaton to this configuration
	 * @param currentState: The current state in which the configuration is right n´now
	 * @param input: The upcoming input that still needs to be read
	 * @param output: The starting output
	 */
	public Configuration(final Automat myAutomat, final State currentState, final String input, final String output) {
		super();
		this.input = input;
		this.myAutomat = myAutomat;
		this.currentState = currentState;
		this.output = output;
	}

	/**
	 * Checks if there is a valid path to go for <input>.
	 * If so, the corresponding output will be concatenate to the output.
	 * @param input: The input that will be checked for a valid path through the automaton
	 */
	public void step(final String input){
		final Set<Transition> possibleTransitions = this.myAutomat.getPossibleTransitions(input.charAt(0),this.currentState);
		
		if(possibleTransitions.size() < 1){
			Manager.getTheInstance().fail(this);
		}else{
			final Iterator<Transition> i = possibleTransitions.iterator();
			int index = 0;
			while(i.hasNext()){
				final Transition current = i.next();
				if(index == 0){
					this.currentState = current.getRightState();
					this.input = input.substring(1);
					this.output = this.output + current.getA();
				}else{
					final Configuration nextConfiguration = new Configuration(this.myAutomat, current.getRightState(), input.substring(1), this.output + current.getA());
					Manager.getTheInstance().newThreadCreated(nextConfiguration);
					nextConfiguration.startThread();
				}
				index++;
			}	
		}
	}
	
	@Override
	public void run(){
		if(this.input.equals("")){
			if(this.isEndConfiguration()){
				Manager.getTheInstance().succeeded(this);
			}else{
				Manager.getTheInstance().fail(this);			}
		}else{
			this.step(this.input);
		}
	}
	
	/**
	 * Checks if the currentState of this configuration is a end state in the corresponding automaton
	 * @return
	 */
	public boolean isEndConfiguration(){
		return this.currentState == this.myAutomat.getEndzustand();
	}

	public String getOutput() {
		return this.output;
	}
}
