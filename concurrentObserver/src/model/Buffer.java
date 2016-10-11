package model;

import java.util.LinkedList;
import java.util.List;

public class Buffer {

	List<Integer> implementingList;
	
	public Buffer() {
		super();
		this.implementingList = new LinkedList<Integer>();
	}

	//hinten rein
	synchronized public void put(int value) {
		this.implementingList.add(value);
		this.notify();
	}

	//vorne raus
	synchronized public int get() {
		//eigentlich if, aber hier while, da das Betriebssystem manchmal das wait aufwekcen könnte
		while(this.isEmpty()){
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int result = this.implementingList.get(0);
		this.implementingList.remove(0);
		return result;
	}
	
	private boolean isEmpty(){
		return this.implementingList.isEmpty();
	}

}
