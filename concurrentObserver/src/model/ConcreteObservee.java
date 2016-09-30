package model;

import observer.Observee;

public class ConcreteObservee extends Observee {
	
	public static ConcreteObservee create() {
		return new ConcreteObservee();
	}
	private int value;
	
	private ConcreteObservee(){
		this.value = 0;
	}
	public int getValue(){
		return this.value;
	}
	public void setValue(int value){
		this.value = value; 
		//TODO Think about notification for registered observers!
	}
}
