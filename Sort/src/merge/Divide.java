package merge;

import merge.Buffer.StoppException;

public class Divide<T extends Comparable<T>> extends Process<T> {

	Buffer<T> inputBuffer;
	Buffer<T> resultBuffer1;
	Buffer<T> resultBuffer2;
	private boolean needSplit = false;

	public Divide(Buffer<T> inputBuffer) {
		this.inputBuffer = inputBuffer;
		this.resultBuffer1 = new Buffer<T>();
		this.resultBuffer2 = new Buffer<T>();
		this.startThread();
	}

	@Override
	public void calculate() {
		this.divide();
	}

	private void divide() {
		try {
			T left = this.inputBuffer.get();
			this.resultBuffer1.put(left);
			
			T right = this.inputBuffer.get();
			this.resultBuffer2.put(right);
			this.needSplit = true;
		} catch (StoppException e) {
			this.resultBuffer1.stopp();
			this.resultBuffer2.stopp();
		}
		
//		if(isNeedSplit()){
//			Divide<T> div1 = new Divide<T>(resultBuffer1);
//			Divide<T> div2 = new Divide<T>(resultBuffer2);
//		}
	}

	public boolean isNeedSplit() {
		return needSplit;
	}

	// private void divide() {
	// int halfSize = this.inputBuffer.size() / 2;
	// int otherSize = this.inputBuffer.size() - halfSize;
	//
	//
	// for(int i=0; i<halfSize; i++){
	// try {
	// T element = this.inputBuffer.get();
	// this.resultBuffer1.put(element);
	// } catch (StoppException e) {
	// this.resultBuffer1.stopp();
	// }
	// }
	// this.resultBuffer1.stopp();
	//
	// for (int i=0; i<otherSize; i++){
	// try {
	// T element = this.inputBuffer.get();
	// this.resultBuffer2.put(element);
	// } catch (StoppException e) {
	// this.resultBuffer2.stopp();
	// }
	// }

	// Merge<T> merged = new Merge<T>(divided.resultBuffer,
	// divided.resultBuffer);

	// while(sort1.size() > 0){
	// try {
	// this.resultBuffer1.put(sort1.get());
	// } catch (StoppException e) {
	// this.resultBuffer1.stopp();
	// }
	// }
	//
	// while(sort2.size() > 0){
	// try {
	// this.resultBuffer2.put(sort2.get());
	// } catch (StoppException e) {
	// this.resultBuffer1.stopp();
	// }
	// }

	// this.stopThread();
	// }

}
