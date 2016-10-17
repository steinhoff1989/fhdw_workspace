package model;

import java.util.LinkedList;
import java.util.List;

public class Buffer<E> {

	public static class StoppException extends Exception{
		
	}
	private interface BufferEntry<E> {
		E getWrapped() throws StoppException;
	}

	private class Stopp<E> implements BufferEntry<E> {
		Stopp() {
		}

		@Override
		public E getWrapped() throws StoppException {
			throw new StoppException();
		}
	}

	private class Wrapped<E> implements BufferEntry<E> {
		final private E wrapped;

		Wrapped(E toBeWrapped) {
			this.wrapped = toBeWrapped;
		}

		@Override
		public E getWrapped() {
			return this.wrapped;
		}
	}

	List<BufferEntry<E>> implementingList;

	public Buffer() {
		super();
		this.implementingList = new LinkedList<BufferEntry<E>>();
	}

	// hinten rein
	synchronized public void put(E value) {
		this.implementingList.add(new Wrapped<E>(value));
		this.notify();
	}

	// vorne raus
	synchronized public E get() throws StoppException {
		// eigentlich if, aber hier while, da das Betriebssystem manchmal das
		// wait aufwekcen könnte
		while (this.isEmpty()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		E result = this.implementingList.get(0).getWrapped();
		this.implementingList.remove(0);
		return result;
	}
	
	synchronized public void stopp(){
		this.implementingList.add(new Stopp<E>());
		this.notify();
	}

	private boolean isEmpty() {
		return this.implementingList.isEmpty();
	}

}
