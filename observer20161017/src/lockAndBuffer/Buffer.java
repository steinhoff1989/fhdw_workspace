package lockAndBuffer;

import java.util.LinkedList;
import java.util.List;

public class Buffer<E> {
	
	Lock lockKA;
	Lock lockWaiting;
	boolean waiting;
	
	@SuppressWarnings("serial")
	public static class StoppException extends Exception {}

	private abstract class BufferEntry<T> {
		abstract T getWrapped() throws StoppException;
	}
	private class Stopp<T> extends BufferEntry<T>{
		Stopp(){}
		@Override
		T getWrapped() throws StoppException {
			throw new StoppException();
		}
	}
	private class Wrapped<T> extends BufferEntry<T> {
		final private T wrapped;
		Wrapped(T toBeWrapped){
			this.wrapped = toBeWrapped;
		}
		@Override
		public T getWrapped() {
			return this.wrapped;
		}
	}
	
	List<BufferEntry<E>> implementingList;
	
	public Buffer(){
		this.implementingList = new LinkedList<BufferEntry<E>>();
		this.lockKA = new Lock(false);
		this.lockWaiting = new Lock(true);
		this.waiting = false;
	}
	
	public void put(E value) {
		this.lockKA.lock();
		this.implementingList.add(new Wrapped<E>(value));
		if(waiting){
			this.waiting = false;
			lockWaiting.unlock();
		}
		this.lockKA.unlock();
	}
	public E get() throws StoppException {
		lockKA.lock();
		if (this.isEmpty()){				
			this.waiting = true;
			this.lockKA.unlock();
			this.lockWaiting.lock();
			lockKA.lock();
		}
		E result = this.implementingList.get(0).getWrapped();
		this.implementingList.remove(0);
		lockKA.unlock();
		return result;
	}
	public void stopp(){
		this.implementingList.add(new Stopp<E>());
	}
	private boolean isEmpty(){
		return this.implementingList.isEmpty();
	}
}



