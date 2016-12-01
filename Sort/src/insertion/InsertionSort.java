package insertion;

import BufferAndLock.Buffer;
import BufferAndLock.Buffer.StoppException;

public class InsertionSort<T extends Comparable<T>> extends Process<T>{
	
	private final Buffer<T> toSortBuffer;
	private final Buffer<T> lastThreadSortedBuffer;
	private final Buffer<T> resultBuffer;
	
	private final Buffer<T> sortedResultBuffer;
	
	private T element;

	/**
	 * Creates a new InsertionSort Object
	 * @param toSort defines the Buffer, which must be sorted.
	 */
	public InsertionSort(final Buffer<T> toSort) {
		this.toSortBuffer = toSort;
		this.resultBuffer = new Buffer<T>();
		this.lastThreadSortedBuffer = new Buffer<T>();
		this.lastThreadSortedBuffer.stopp();
		this.sortedResultBuffer = new Buffer<T>();
	}
	
	/**
	 * 
	 * @param toSort is the remaining Buffer of elements, which are not sorted yet.
	 * @param sortedBuffer is a temporary result. Elements from <toSort> are going to be sorted in <sortedBuffer>
	 * @param sortedResultBuffer is written, when the algorithm comes to an end. 
	 */
	private InsertionSort(final Buffer<T> toSort, final Buffer<T> sortedBuffer, final Buffer<T> sortedResultBuffer) {
		this.toSortBuffer = toSort;
		this.lastThreadSortedBuffer = sortedBuffer;
		this.resultBuffer = new Buffer<T>();
		this.sortedResultBuffer = sortedResultBuffer;
	}

	/**
	 * Inserts the first element from the origin list <toSort> into <resultBuffer> which is the temporary result. 
	 * Remaining elements will be compared to the <resultBuffer> 
	 */
	@Override
	public void calculate() {
		if(this.element == null){
			try {
				this.element = this.toSortBuffer.get();
				final InsertionSort<T> nextStep = new InsertionSort<T>(this.toSortBuffer, this.resultBuffer, this.sortedResultBuffer);
				nextStep.startThread();
			} catch (final StoppException e) {
				this.copySortedBufferToResultBuffer();
				return;
			}
		}
		
		try {
			final T nextSortedElement = this.lastThreadSortedBuffer.get();
			if(this.element.compareTo(nextSortedElement) <= 0){
				this.resultBuffer.put(this.element);
				this.element = nextSortedElement;
			}else{
				this.resultBuffer.put(nextSortedElement);
			}
		} catch (final StoppException e) {
			this.resultBuffer.put(this.element);
			this.resultBuffer.stopp();
			this.stopThread();
		}
		
	}
	/**
	 * After finishing calculate-Operation the temporary <resultbuffer> gets copied to the <sortedResultBuffer> and threads
	 * will be stopped.
	 */
	private void copySortedBufferToResultBuffer() {
		try {
			this.sortedResultBuffer.put(this.lastThreadSortedBuffer.get());
		} catch (final StoppException e) {
			this.sortedResultBuffer.stopp();
			this.stopThread();
		}
	}
/**
 * Getter for the result of the insertionSort-algorithm
 * @return: result of insertionSort
 */
	public Buffer<T> getSortedResultBuffer() {
		return this.sortedResultBuffer;
	}

}
