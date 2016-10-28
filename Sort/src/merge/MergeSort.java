package merge;

import merge.Buffer.StoppException;

public class MergeSort<T extends Comparable<T>> {
	
	private Buffer<T> inputBuffer;
	private Buffer<T> resultBuffer;


	public MergeSort(Buffer<T> inputBuffer) {
		this.inputBuffer = inputBuffer;
		this.resultBuffer = new Buffer<T>();
	}
	
	public void sort() {
		if(this.inputBuffer.size() >2){
			this.divide();
		}
		else{ 
			try {
			this.resultBuffer.put(this.inputBuffer.get());
			this.resultBuffer.put(this.inputBuffer.get());
		} catch (StoppException e) {
			this.resultBuffer.stopp();
			}
		}
	}

	public void divide(){
		int halfSize = this.inputBuffer.size() / 2;
		int otherSize = this.inputBuffer.size() - halfSize;
		
		Buffer<T> divideBuffer1 = new Buffer<T>();
		Buffer<T> divideBuffer2 = new Buffer<T>();
		
		for(int i=0; i<halfSize; i++){
			try {
				T element = this.inputBuffer.get();
				divideBuffer1.put(element);
			} catch (StoppException e) {
				divideBuffer1.stopp();
			}
		}
		divideBuffer1.stopp();
		
		for (int i=0; i<otherSize; i++){
			try {
				T element = this.inputBuffer.get();
				divideBuffer2.put(element);
			} catch (StoppException e) {
				divideBuffer2.stopp();
			}
		}

		MergeSort<T> tempSort1 = new MergeSort<T>(divideBuffer1);
		MergeSort<T> tempSort2 = new MergeSort<T>(divideBuffer2);

		tempSort1.sort();
		tempSort2.sort();
		
		this.merge(tempSort1,tempSort2);
}

	private void merge(MergeSort<T> tempSort1, MergeSort<T> tempSort2) {
		
		try {
			T first = tempSort1.resultBuffer.get();
			try {
				T second = tempSort2.resultBuffer.get();
				if(first.compareTo(second)<0){
					this.resultBuffer.put(first);
//					this.resultBuffer.put(second);
					this.mergeHelper(tempSort1, second);
				}
				else{
					this.resultBuffer.put(second);
					this.mergeHelper(tempSort2, first);
//					this.resultBuffer.put(first);
				}
//				this.merge(tempSort1, tempSort2);
				
			} catch (StoppException e) {
				this.resultBuffer.put(first);
				this.resultBuffer.stopp();
			}
		} catch (StoppException e) {
			try {
				T second = tempSort2.resultBuffer.get();
				this.resultBuffer.put(second);
				this.resultBuffer.stopp();
			} catch (StoppException e1) {
				this.resultBuffer.stopp();
			}
			
			}
	}
	private void mergeHelper(MergeSort<T> tempSort, T element){
			try {
				T first = tempSort.resultBuffer.get();
				if(first.compareTo(element) < 0){
					this.resultBuffer.put(first);
					mergeHelper(tempSort, element);
				}else{
					this.resultBuffer.put(element);
					mergeHelper(tempSort1, first);
				}
			} catch (StoppException e) {
				this.resultBuffer.put(element);
				this.resultBuffer.stopp();
			}
	}
	
	public Buffer<T> getInputBuffer() {
		return inputBuffer;
	}

	public void setInputBuffer(Buffer<T> inputBuffer) {
		this.inputBuffer = inputBuffer;
	}

	public Buffer<T> getResultBuffer() {
		return resultBuffer;
	}

	public void setResultBuffer(Buffer<T> resultBuffer) {
		this.resultBuffer = resultBuffer;
	}

}
