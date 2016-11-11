package BufferAndLock;

import java.util.LinkedList;
import java.util.List;

public class Buffer<E> {

	public static class StoppException extends Exception {

	}
	
	public static class DivideByZeroException extends Exception {

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

	public Buffer() {
		this.implementingList = new LinkedList<BufferEntry<E>>();
	}
	
	// hinten rein
	synchronized public void put(E value) {
		this.implementingList.add(new Wrapped<E>(value));
		this.notify();
	}

	synchronized public E get() throws StoppException {
		while (this.isEmpty()) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		E result = this.implementingList.get(0).getWrapped();

		this.implementingList.remove(0);
		return result;
	}

	synchronized public void stopp() {
		this.implementingList.add(new Stopp<E>());
		this.notify();
	}

	public boolean isEmpty() {
		return this.implementingList.isEmpty();
	}
	public int size(){
		return this.implementingList.size();
	}

}
