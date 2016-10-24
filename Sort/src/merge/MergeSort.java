package merge;

import bubble.Buffer;
import bubble.Buffer.StoppException;

public class MergeSort {
	
	private Buffer<Comparable> inputBuffer;
	private Buffer<Comparable> resultBuffer;


	@SuppressWarnings("rawtypes")
	public MergeSort(Buffer<Comparable> inputBuffer) {
		this.inputBuffer = inputBuffer;
		this.resultBuffer = new Buffer<Comparable>();

	}
	
	public void sort() {
		if(this.inputBuffer.size() >2){
			this.divide();
		}
		else{
			this.merge();
		}
	}

	private void merge() {
		
	};
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void divide(){
		int halfSize = this.inputBuffer.size() / 2;
		int otherSize = this.inputBuffer.size() - halfSize;
		
		for(int i=0; i<=halfSize; i++){
			Comparable element;
			try {
				element = this.inputBuffer.get();
				Buffer divideBuffer1 = new Buffer<Comparable>();
				divideBuffer1.put(element);
				MergeSort tempSort1 = new MergeSort(divideBuffer1);
				tempSort1.sort();
			} catch (StoppException e) {
				e.printStackTrace();
			}
		}
		for (int i=0; i<=otherSize; i++){
			Comparable element;
			try {
				element = this.inputBuffer.get();
				Buffer divideBuffer2 = new Buffer<Comparable>();
				divideBuffer2.put(element);
				MergeSort tempSort2 = new MergeSort(divideBuffer2);
				tempSort2.sort();
			} catch (StoppException e) {
				e.printStackTrace();
			}
		}
}

}
