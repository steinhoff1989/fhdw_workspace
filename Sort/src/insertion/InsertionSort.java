package insertion;

import BufferAndLock.Buffer;

public class InsertionSort<T extends Comparable<T>> extends Process<T>{
	
	Buffer<T> insertBuffer;
	Buffer<T> resultBuffer;

	public InsertionSort(Buffer<T> toSort) {
		super();
		this.insertBuffer = toSort;
		this.resultBuffer = new Buffer<T>(50);
	}

	@Override
	public void calculate() {
		// TODO Auto-generated method stub
	}
	
	public Buffer<T> getResultBuffer() {
		return resultBuffer;
	}

}
