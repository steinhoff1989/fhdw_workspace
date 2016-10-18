package model;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractBuffer<E> {

	public static class StoppException extends Exception{
		
	}
	
	protected interface BufferEntry<E> {
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

	protected List<BufferEntry<E>> implementingList;

	public AbstractBuffer() {
		this.implementingList = new LinkedList<BufferEntry<E>>();
	}

	// hinten rein
	synchronized public void put(E value) {
		this.implementingList.add(new Wrapped<E>(value));
		this.notify();
	}

	public abstract E get() throws StoppException;
	
	
	
	synchronized public void stopp(){
		this.implementingList.add(new Stopp<E>());
		this.notify();
	}

	public boolean isEmpty() {
		return this.implementingList.isEmpty();
	}

}
