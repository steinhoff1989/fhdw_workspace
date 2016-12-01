package quick;

import BufferAndLock.Buffer;
import BufferAndLock.Buffer.StoppException;

public class QuickSort<T extends Comparable<T>> extends Process<T> {

	private final Buffer<T> inputBuffer;
	private final Buffer<T> resultBuffer;

	private final Buffer<T> leftList;
	private final Buffer<T> rightList;

	private T referenceElement;

	private QuickSort<T> sortLeftList;
	private QuickSort<T> sortRightList;

	/**
	 * Creates a QuickSort-Object witch an <insertBuffer
	 * @param inputBuffer defines the buffer getting sorted
	 */
	public QuickSort(final Buffer<T> inputBuffer) {
		this.inputBuffer = inputBuffer;
		this.resultBuffer = new Buffer<T>();
		this.leftList = new Buffer<T>();
		this.rightList = new Buffer<T>();
	}

	/**
	 * 
	 */
	@Override
	public void calculate() {
		//How to do it better?
		if(this.referenceElement == null){
			try {
				this.referenceElement = this.inputBuffer.get();
				this.sortLeftList = new QuickSort<T>(this.leftList);
				this.sortLeftList.startThread();
				this.sortRightList = new QuickSort<T>(this.rightList);
				this.sortRightList.startThread();
			} catch (final StoppException e) {
				this.resultBuffer.stopp();
				this.stopThread();
				return;
			}
		}
		
		try {
			final T nextElement = this.inputBuffer.get();
			if (nextElement.compareTo(this.referenceElement) <= 0) {
				this.leftList.put(nextElement);

			} else {
				this.rightList.put(nextElement);
			}
		} catch (final StoppException e) {
			this.leftList.stopp();
			this.rightList.stopp();
			this.stopThread();
			this.copyFromLeftSortedBufferToResultBuffer();
			this.resultBuffer.put(this.referenceElement);
			this.copyFromRightSortedBufferToResultBuffer();
			this.resultBuffer.stopp();
		}
	}

	private void copyFromRightSortedBufferToResultBuffer() {
		try {
			this.resultBuffer.put(this.sortRightList.getResultBuffer().get());
			this.copyFromRightSortedBufferToResultBuffer();
		} catch (final StoppException e) {
			
		}
	}

	private void copyFromLeftSortedBufferToResultBuffer() {
		try {
			this.resultBuffer.put(this.sortLeftList.getResultBuffer().get());
			this.copyFromLeftSortedBufferToResultBuffer();
		} catch (final StoppException e) {
			
		}
	}

	public Buffer<T> getResultBuffer() {
		return this.resultBuffer;
	}
}
