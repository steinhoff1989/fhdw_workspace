package insertion;

import BufferAndLock.Buffer;
import BufferAndLock.Buffer.StoppException;

public class InsertionSort<T extends Comparable<T>> extends Process<T>{
	
	Buffer<T> toSortBuffer;
	Buffer<T> lastThreadSortedBuffer;
	Buffer<T> resultBuffer;
	
	Buffer<T> sortedResultBuffer;
	
	T element;

	public InsertionSort(Buffer<T> toSort) {
		this.toSortBuffer = toSort;
		this.resultBuffer = new Buffer<T>();
		this.lastThreadSortedBuffer = new Buffer<T>();
		this.lastThreadSortedBuffer.stopp();
		this.sortedResultBuffer = new Buffer<T>();
	}
	
	public InsertionSort(Buffer<T> toSort, Buffer<T> sortedBuffer, Buffer<T> sortedResultBuffer) {
		this.toSortBuffer = toSort;
		this.lastThreadSortedBuffer = sortedBuffer;
		this.resultBuffer = new Buffer<T>();
		this.sortedResultBuffer = sortedResultBuffer;
	}

	@Override
	public void calculate() {
		if(element == null){
			try {
				this.element = this.toSortBuffer.get();
				InsertionSort<T> nextStep = new InsertionSort<T>(toSortBuffer, resultBuffer, this.sortedResultBuffer);
				nextStep.startThread();
			} catch (StoppException e) {
				copySortedBufferToResultBuffer();
				return;
			}
		}
		
		try {
			T nextSortedElement = this.lastThreadSortedBuffer.get();
			if(this.element.compareTo(nextSortedElement) <= 0){
				this.resultBuffer.put(element);
				this.element = nextSortedElement;
			}else{
				this.resultBuffer.put(nextSortedElement);
			}
		} catch (StoppException e) {
			this.resultBuffer.put(element);
			this.resultBuffer.stopp();
			this.stopThread();
		}
		
	}
	
	private void copySortedBufferToResultBuffer() {
		try {
			this.sortedResultBuffer.put(this.lastThreadSortedBuffer.get());
		} catch (StoppException e) {
			this.sortedResultBuffer.stopp();
			this.stopThread();
		}
	}

	public Buffer<T> getSortedResultBuffer() {
		return sortedResultBuffer;
	}

}
