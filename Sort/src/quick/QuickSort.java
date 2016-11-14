package quick;

import BufferAndLock.Buffer;
import BufferAndLock.Buffer.StoppException;

public class QuickSort<T extends Comparable<T>> extends Process<T> {

	private Buffer<T> insertBuffer;
	private Buffer<T> resultBuffer;

	private Buffer<T> leftList;
	private Buffer<T> rightList;

	private T referenceElement;

	private QuickSort<T> sortLeftList;
	private QuickSort<T> sortRightList;

	public QuickSort(Buffer<T> insertBuffer) {
		this.insertBuffer = insertBuffer;
		this.resultBuffer = new Buffer<T>();
		this.leftList = new Buffer<T>();
		this.rightList = new Buffer<T>();
	}

	@Override
	public void calculate() {
		//How to do it better?
		if(this.referenceElement == null){
			try {
				this.referenceElement = this.insertBuffer.get();
				this.sortLeftList = new QuickSort<T>(this.leftList);
				this.sortLeftList.startThread();
				this.sortRightList = new QuickSort<T>(this.rightList);
				this.sortRightList.startThread();
			} catch (StoppException e) {
				this.resultBuffer.stopp();
				this.stopThread();
				return;
			}
		}
		
		try {
			T nextElement = this.insertBuffer.get();
			if (nextElement.compareTo(this.referenceElement) <= 0) {
				this.leftList.put(nextElement);

			} else {
				this.rightList.put(nextElement);
			}
		} catch (StoppException e) {
			this.leftList.stopp();
			this.rightList.stopp();
			this.stopThread();
			this.copyFromLeftSortedBufferToResultBuffer();
			this.resultBuffer.put(referenceElement);
			this.copyFromRightSortedBufferToResultBuffer();
			this.resultBuffer.stopp();
		}
	}

	private void copyFromRightSortedBufferToResultBuffer() {
		try {
			this.resultBuffer.put(this.sortRightList.getResultBuffer().get());
			copyFromRightSortedBufferToResultBuffer();
		} catch (StoppException e) {
			
		}
	}

	private void copyFromLeftSortedBufferToResultBuffer() {
		try {
			this.resultBuffer.put(this.sortLeftList.getResultBuffer().get());
			copyFromLeftSortedBufferToResultBuffer();
		} catch (StoppException e) {
			
		}
	}

	public Buffer<T> getResultBuffer() {
		return resultBuffer;
	}
}
