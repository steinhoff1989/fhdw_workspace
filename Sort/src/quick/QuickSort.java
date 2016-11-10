package quick;

import BufferAndLock.Buffer;

public class QuickSort<T extends Comparable<T>> extends Process<T> {

	Buffer<T> insertBuffer;
	Buffer<T> resultBuffer;
	
	public QuickSort(Buffer<T> insertBuffer) {
		super();
		this.insertBuffer = insertBuffer;
		this.startThread();
	}

	@Override
	public void calculate() {
		// TODO Auto-generated method stub
		
	}
	
	public Buffer<T> getResultBuffer() {
		return resultBuffer;
	}
}
