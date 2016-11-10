package merge;

import merge.Buffer.StoppException;

public class Sort<T extends Comparable<T>> {

	Buffer<T> inputBuffer;
	Buffer<T> resultBuffer;
	Divide<T> div;
	Merge<T> merge;

	public Sort(Buffer<T> inputBuffer) {
		this.inputBuffer = inputBuffer;
		this.resultBuffer = new Buffer<T>();
	}

	// @Override
	// public void calculate() {
	// this.sort();
	// }

	public void sort() {
		if (this.inputBuffer.size() <= 2) {
			try {
				this.resultBuffer.put(this.inputBuffer.get());
				this.resultBuffer.put(this.inputBuffer.get());
			} catch (StoppException e) {
				this.resultBuffer.stopp();
			}
		} else {
			div = new Divide<T>(inputBuffer);
			merge = new Merge<T>(div.sortLeft.resultBuffer, div.sortRight.resultBuffer, this.resultBuffer);
//			this.resultBuffer = merge.sortedBuffer;
		}
		// if (this.inputBuffer.size() <= 2) {
		// this.resultBuffer = this.inputBuffer;
		// } else {
		// div = new Divide<T>(inputBuffer);
		// merge = new Merge<T>(div.sortLeft.resultBuffer,
		// div.sortRight.resultBuffer);
		// this.resultBuffer = merge.sortedBuffer;
		// this.stopThread();
		// }
		// if(div.isNeedSplit()){//dirty
		// Sort<T> sortLeft = new Sort<T>(div.divLeft);
		// Sort<T> sortRight = new Sort<T>(div.divRight);
		// merge = new Merge<T>(sortLeft.sortedBuffer, sortRight.sortedBuffer);
		// }else{
		// merge = new Merge<T>(div.divLeft, div.divRight);
		//
		// this.sortedBuffer = merge.getResultBuffer();
		// }
		// this.resultBuffer.p
	}

	// private void sort(){
	// if(this.inputBuffer.size() >2){
	// Divide<T> divided = new Divide<>(this.inputBuffer);
	// Merge<T> merged = new Merge<T>(divided.resultBuffer1,
	// divided.resultBuffer2);
	// this.resultBuffer = merged.resultBuffer;
	// }
	// else{
	// try {
	// this.resultBuffer.put(this.inputBuffer.get());
	// this.resultBuffer.put(this.inputBuffer.get());
	// } catch (StoppException e) {
	// this.resultBuffer.stopp();
	// this.stopThread();
	// }
	// }
	// }

	public Buffer<T> getResultBuffer() {
		return resultBuffer;
	}

}
