package merge;

import merge.Buffer.StoppException;

public class Merge<T extends Comparable<T>> extends Process<T>{

	Buffer<T> inputBuffer1;
	Buffer<T> inputBuffer2;
	Buffer<T> resultBuffer;
	
	public Merge(Buffer<T> inputBuffer1, Buffer<T> inputBuffer2){
		this.inputBuffer1 = inputBuffer1;
		this.inputBuffer2 = inputBuffer2;
		this.resultBuffer = new Buffer<T>();
		this.startThread();
	}
	
	@Override
	public void calculate() {
		this.merge(inputBuffer1, inputBuffer2);
	}

	private void merge(Buffer<T> firstBuffer, Buffer<T> secondBuffer) {
		try {
			T first = firstBuffer.get();
			try {
				T second = secondBuffer.get();
				if(first.compareTo(second)<0){
					this.resultBuffer.put(first);
					Buffer<T> singleBuffer = new Buffer<T>();
					singleBuffer.put(second);
					singleBuffer.stopp();
					
					this.merge(singleBuffer, firstBuffer);
				}
				else{
					this.resultBuffer.put(second);

					Buffer<T> singleBuffer = new Buffer<T>();
					singleBuffer.put(first);
					singleBuffer.stopp();
					
					this.merge(singleBuffer, secondBuffer);
				}
			} catch (StoppException e) {
				this.resultBuffer.put(first);

				this.merge(firstBuffer, secondBuffer);
			}
		} catch (StoppException e) {
			try {
				T second = secondBuffer.get();
				this.resultBuffer.put(second);
				
				this.merge(firstBuffer, secondBuffer);
			} catch (StoppException e1) {
			}
			
			}
	}
//	private void mergeHelper(MergeSort<T> tempSort, T element){
//			try {
//				T first = tempSort.resultBuffer.get();
//				if(first.compareTo(element) < 0){
//					this.resultBuffer.put(first);
//					mergeHelper(tempSort, element);
//				}else{
//					this.resultBuffer.put(element);
//					mergeHelper(tempSort1, first);
//				}
//			} catch (StoppException e) {
//				this.resultBuffer.put(element);
//				this.resultBuffer.stopp();
//			}
//	}

}
