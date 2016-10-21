package merge;

import bubble.Buffer;

public class MergeSort {
	
	private Buffer<Comparable> inputBuffer;
	private Buffer<Comparable> divideBuffer1;
	private Buffer<Comparable> divideBuffer2;


	public MergeSort(Buffer<Comparable> inputBuffer) {
		this.inputBuffer = inputBuffer;
		this.divideBuffer1 = new Buffer<Comparable>();
		this.divideBuffer2 = new Buffer<Comparable>();

	}
	
	public void divide(){
		int halfSize = this.inputBuffer.size() / 2;
		int otherSize = this.inputBuffer.size() - halfSize;
		
		for(int i=0; i<=halfSize; i++){
			Comparable element = inputBuffer.get();
			this.divideBuffer1.put(element);
		}
		for (int i=0; i<=otherSize; i++){
			Comparable element = inputBuffer.get();
			this.divideBuffer2.put(element);
		}
		this.sort();

}

	public void sort() {
		if(this.divideBuffer1.size()>1){
			
		}
	};

}
