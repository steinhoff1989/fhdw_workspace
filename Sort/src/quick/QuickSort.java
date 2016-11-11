package quick;

import BufferAndLock.Buffer;
import BufferAndLock.Buffer.StoppException;

public class QuickSort<T extends Comparable<T>> extends Process<T> {

	private Buffer<T> insertBuffer;
	private Buffer<T> resultBuffer;
	private T referenceElement;
	
	private Buffer<T> leftList;
	private Buffer<T> rightList;
	
	private QuickSort<T> sortLeftList;
	private QuickSort<T> sortRightList;
	private Buffer<T> firstElement;
	
	public QuickSort(Buffer<T> insertBuffer) {
		this.insertBuffer = insertBuffer;
		this.resultBuffer = new Buffer<T>();
		this.firstElement = new Buffer<T>();

		this.leftList = new Buffer<T>();
		this.rightList = new Buffer<T>();
		try {
			this.referenceElement = insertBuffer.get();
			try{
				this.firstElement.put(insertBuffer.get());
				this.firstElement.stopp();
			}catch (StoppException e) {
				this.resultBuffer.put(referenceElement);
				this.resultBuffer.stopp();
			}
		} catch (StoppException e) {
			this.resultBuffer.stopp();
		}
	}

	@Override
	public void calculate() {
		T nextElement;
		try {
			try{
				nextElement = this.firstElement.get();	
			}catch(StoppException ex){
				nextElement = this.insertBuffer.get();
			}
			if(nextElement.compareTo(referenceElement) <= 0){
				this.leftList.put(nextElement);
			}else{
				this.rightList.put(nextElement);
			}
		} catch (StoppException e) {
			this.leftList.put(referenceElement);
			this.leftList.stopp();
			this.rightList.stopp();
			
			try {
				this.sortLeftList = new QuickSort<>(leftList);
				T left = this.sortLeftList.getResultBuffer().get();
				this.sortLeftList.startThread();
				this.resultBuffer.put(left);
			} catch (StoppException e1) {
				try {
					this.sortRightList = new QuickSort<>(rightList);
					T right = this.sortRightList.getResultBuffer().get();
					this.sortRightList.startThread();
					this.resultBuffer.put(right);
				} catch (StoppException e2) {
					this.resultBuffer.stopp();
					this.stopThread();
				}
			}
			
		}
	}
	
	public Buffer<T> getResultBuffer() {
		return resultBuffer;
	}
}
