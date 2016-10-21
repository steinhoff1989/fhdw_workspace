

package lockAndBuffer;

public class Buffer<E> {
	public static <E> Buffer<E> create(int capacity) {
		return new Buffer<E> (capacity);
	}
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
	
	public Buffer(int capacity){
		this.internalCapacity = (capacity <= 0 ? 1 : capacity) + 1;
		this.myBuffer = new Object[this.internalCapacity];
		this.first = 0;
		this.behindLast = 0;
	}
	
	private Lock mutex = new Lock(false);
	private Lock reading = new Lock(true);
	private boolean waitingForNotEmpty = false;
	private Lock writing = new Lock(true);
	private boolean waitingForNotFull = false;

	private Object[] myBuffer;
	private int first;
	private int behindLast;
	private int internalCapacity;
	
	public void put(E value) {
		this.put(new Wrapped<E>(value));
	}
	private void put(BufferEntry<E> value) {
		mutex.lock();
		if (this.isFull()) {
			this.waitingForNotFull = true;
			this.mutex.unlock();
			writing.lock();
			this.mutex.lock();
		}
		this.addNextEntry(value);
		if (waitingForNotEmpty) {
			this.waitingForNotEmpty = false;
			reading.unlock();
		}
		mutex.unlock();
	}
	public E get() throws StoppException {
		mutex.lock();
		if (this.isEmpty()){
			this.waitingForNotEmpty = true;
			mutex.unlock();
			reading.lock();
			mutex.lock();
		}
		E result = this.getNextEntry().getWrapped();
		if (this.waitingForNotFull){
			this.waitingForNotFull = false;
			writing.unlock();
		}
		mutex.unlock();
		return result;
	}
	private void addNextEntry(Buffer<E>.BufferEntry<E> wrapped) {
		this.myBuffer[this.behindLast] = wrapped;
		this.behindLast = (this.behindLast + 1) % this.internalCapacity;
	}
	private Buffer<E>.BufferEntry<E> getNextEntry() {
		@SuppressWarnings("unchecked")
		BufferEntry<E> result = (Buffer<E>.BufferEntry<E>) this.myBuffer[this.first];
		this.first = (this.first + 1) % this.internalCapacity;
		return result;
	}
	public void stopp(){
		this.put(new Stopp<E>());
	}
	private boolean isEmpty(){
		return this.first == this.behindLast;
	}
	private boolean isFull(){
		return (this.behindLast + 1) % this.internalCapacity == this.first;
	}
	public int size(){
		this.mutex.lock();
		int result = this.first <= this.behindLast 
						? this.behindLast - this.first
						: this.behindLast + (this.internalCapacity - this.first)				;
		this.mutex.unlock();
		return result;
	}
}





