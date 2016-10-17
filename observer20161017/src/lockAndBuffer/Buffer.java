package lockAndBuffer;

import java.util.LinkedList;
import java.util.List;

public class Buffer<E> {
	
	Lock lockKA;
	Lock lockWaitingForNotEmpty;
	Lock lockWaitingForNotFull;
	boolean waitingForNotExpty;
	private Object[] myBuffer;
	private int first;
	private int behindLast;
//	List<BufferEntry<E>> implementingList;
	private int internalCapacity;
	private boolean waitingForNotFull;
	
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
	
//	public Buffer(){
////		this.implementingList = new LinkedList<BufferEntry<E>>();
//		this.lockKA = new Lock(false);
//		this.lockWaiting = new Lock(true);
//		this.waiting = false;
////		this.myBuffer
////		this.first = 0;
////		this.behindLast = 0;
//	}
	
	public Buffer(int capacity){
		this.internalCapacity = capacity <= 0 ? 1 : capacity + 1;
		this.myBuffer = new Object[this.internalCapacity];
		this.first = 0;
		this.behindLast = 0;
		
//		this.implementingList = new LinkedList<BufferEntry<E>>();
		this.lockKA = new Lock(false);
		this.lockWaitingForNotEmpty = new Lock(true);
		this.lockWaitingForNotFull= new Lock(true);
		this.waitingForNotExpty = false;
		this.waitingForNotFull= false;
	}
	
	
	public void put(E value) {
		this.lockKA.lock();
		if(this.isFull()){
			this.waitingForNotFull = true;
			this.lockKA.unlock();
			this.lockWaitingForNotFull.lock();
			this.lockKA.lock();
		}
		this.addNextEntry(new Wrapped<E>(value));
		if(waitingForNotExpty){
			this.waitingForNotExpty = false;
			lockWaitingForNotEmpty.unlock();
		}
		this.lockKA.unlock();
	}
	

	public E get() throws StoppException {
		lockKA.lock();
		if (this.isEmpty()){				
			this.waitingForNotExpty = true;
			this.lockKA.unlock();
			this.lockWaitingForNotEmpty.lock();
			lockKA.lock();
		}
		E result = this.getNextEntry().getWrapped();
		if(this.waitingForNotFull){
			this.waitingForNotFull = false;
			this.lockWaitingForNotFull.unlock();
		}
		lockKA.unlock();
		return result;
	}
	private Buffer<E>.BufferEntry<E> getNextEntry() {
		@SuppressWarnings("unchecked")
		BufferEntry<E> result = (Buffer<E>.BufferEntry<E>) this.myBuffer[this.first];
		this.first = (this.first + 1) % this.internalCapacity;
//		if(this.isEmpty()){
//			
//		}
		return result;
	}
	
	private void addNextEntry(Buffer<E>.BufferEntry<E> wrapped) {
		this.myBuffer[this.behindLast] = wrapped;
		this.behindLast = (this.behindLast + 1) % this.internalCapacity;
	}

	public void stopp(){
		this.addNextEntry(new Stopp<E>());
	}
	private boolean isEmpty(){
		return this.first == this.behindLast;
	}
	
	private boolean isFull(){
		return ((this.behindLast + 1) % this.internalCapacity) == this.first;
	}
}



