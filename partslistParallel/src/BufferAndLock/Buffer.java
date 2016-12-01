package BufferAndLock;

public class Buffer<E> {
	public static <E> Buffer<E> create(final int capacity) {
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
		Wrapped(final T toBeWrapped){
			this.wrapped = toBeWrapped;
		}
		@Override
		public T getWrapped() {
			return this.wrapped;
		}
	}
	
	public Buffer(final int capacity){
		this.internalCapacity = (capacity <= 0 ? 1 : capacity) + 1;
		this.myBuffer = new Object[this.internalCapacity];
		this.first = 0;
		this.behindLast = 0;
	}
	
	private final Lock mutex = new Lock(false);
	private final Lock reading = new Lock(true);
	private int waitingForNotEmpty = 0;
	private final Lock writing = new Lock(true);
	private int waitingForNotFull = 0;

	private final Object[] myBuffer;
	private int first;
	private int behindLast;
	private final int internalCapacity;
	
	private final Lock writingUnlockFinished = new Lock(true);
	private final Lock readingUnlockFinished = new Lock(true);
	
	public void put(final E value) {
		this.put(new Wrapped<E>(value));
	}
	private void put(final BufferEntry<E> value) {
		this.mutex.lock();
		if (this.isFull()) {
//			System.out.println("full");
			this.waitingForNotFull++;
			this.mutex.unlock();
			this.writing.lock();
			this.writingUnlockFinished.unlock();
			this.mutex.lock();
		}
		this.addNextEntry(value);
		if (this.waitingForNotEmpty > 0) {
			this.waitingForNotEmpty--;
			this.reading.unlock();
			this.readingUnlockFinished.lock();
		}
		this.mutex.unlock();
	}
	public E get() throws StoppException {
		this.mutex.lock();
		if (this.isEmpty()){
			this.waitingForNotEmpty++;
			this.mutex.unlock();
			this.reading.lock();
			this.readingUnlockFinished.unlock();
			this.mutex.lock();
		}
		E result = null;
		try{
			result = this.getNextEntry().getWrapped();
		}
		finally{
			if (this.waitingForNotFull > 0){
				this.waitingForNotFull--;
				this.writing.unlock();
				this.writingUnlockFinished.lock();
			}
			this.mutex.unlock();
		}
		return result;
	}
	private void addNextEntry(final Buffer<E>.BufferEntry<E> wrapped) {
		this.myBuffer[this.behindLast] = wrapped;
		this.behindLast = (this.behindLast + 1) % this.internalCapacity;
	}
	private Buffer<E>.BufferEntry<E> getNextEntry() {
		@SuppressWarnings("unchecked")
		final
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
		final int result = this.first <= this.behindLast 
						? this.behindLast - this.first
						: this.behindLast + (this.internalCapacity - this.first)				;
		this.mutex.unlock();
		return result;
	}
}





