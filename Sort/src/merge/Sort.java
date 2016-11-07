package merge;

import merge.Buffer.StoppException;

public class Sort<T extends Comparable<T>>{

	Buffer<T> inputBuffer;
	Buffer<T> resultBuffer;
	
	public Sort(Buffer<T> inputBuffer){
		this.inputBuffer = inputBuffer;
		this.resultBuffer = new Buffer<T>();
//		this.startThread();
	}
	
//	@Override
//	public void calculate() {
//		this.sort();
//	}

	public void sort() {
		Divide<T> div = new Divide<T>(inputBuffer);
		Merge<T> merge;
		if(div.isNeedSplit()){
			Sort<T> sortLeft = new Sort<T>(div.resultBuffer1);
			Sort<T> sortRight = new Sort<T>(div.resultBuffer2);
			merge = new Merge<T>(sortLeft.resultBuffer, sortRight.resultBuffer);
		}else{
			merge = new Merge<T>(div.resultBuffer1, div.resultBuffer2);
		}
//		this.resultBuffer.p
	}
	
//	private void sort(){
//		if(this.inputBuffer.size() >2){
//			Divide<T> divided = new Divide<>(this.inputBuffer);
//			Merge<T> merged = new Merge<T>(divided.resultBuffer1, divided.resultBuffer2);
//			this.resultBuffer = merged.resultBuffer;
//		}
//		else{ 
//			try {
//			this.resultBuffer.put(this.inputBuffer.get());
//			this.resultBuffer.put(this.inputBuffer.get());
//		} catch (StoppException e) {
//			this.resultBuffer.stopp();
//			this.stopThread();
//			}
//		}
//	}


	public Buffer<T> getResultBuffer() {
		return resultBuffer;
	}

}
