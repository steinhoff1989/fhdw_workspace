package merge;

import merge.Buffer.StoppException;

public class Merge<T extends Comparable<T>> extends Process<T> {

	Buffer<T> divLeft;
	Buffer<T> divRight;
	Buffer<T> resultBuffer;

	Buffer<T> singleBufferLeft;
	Buffer<T> singleBufferRight;
	
	
	public Merge(Buffer<T> divLeft, Buffer<T> divRight, Buffer<T> resultBuffer) {
		this.divLeft = divLeft;
		this.divRight = divRight;
		this.resultBuffer = resultBuffer;
		this.singleBufferLeft = new Buffer<T>();
		this.singleBufferRight = new Buffer<T>();
		
		this.singleBufferLeft.stopp();
		this.singleBufferRight.stopp();
		
		this.startThread();
	}

	@Override
	public void calculate() {
		this.merge(divLeft, divRight);
	}

	private void merge(Buffer<T> firstBuffer, Buffer<T> secondBuffer) {
		try {
			T first;
			try {
				first = this.singleBufferLeft.get();
			} catch (StoppException e2) {
				first = firstBuffer.get();
			}
			try {
				T second;
				try {
					second = this.singleBufferRight.get();
				} catch (StoppException e2) {
					second = secondBuffer.get();
				}
				if (first.compareTo(second) < 0) {
					this.resultBuffer.put(first);
					
					singleBufferRight = new Buffer<T>();
					singleBufferRight.put(second);
					singleBufferRight.stopp();

//					this.resultBuffer.put(new Merge<T>(firstBuffer, singleBuffer).resultBuffer.get());
//					this.merge(singleBuffer, firstBuffer);
				} else {
					this.resultBuffer.put(second);

//					Buffer<T> singleBuffer = new Buffer<T>();
//					singleBuffer.put(first);
//					singleBuffer.stopp();

					singleBufferLeft = new Buffer<T>();
					singleBufferLeft.put(first);
					singleBufferLeft.stopp();
					
//					this.merge(singleBuffer, secondBuffer);
				}
			} catch (StoppException e) {
				this.resultBuffer.put(first);

//				this.merge(firstBuffer, secondBuffer);
			}
		} catch (StoppException e) {
			try {
				T second;
				try {
					second = this.singleBufferRight.get();
				} catch (StoppException e2) {
					second = secondBuffer.get();
				}
//				second = secondBuffer.get();
				this.resultBuffer.put(second);

//				this.merge(firstBuffer, secondBuffer);
			} catch (StoppException e1) {
			this.resultBuffer.stopp();
			this.stopThread();
			}
		}
	}
}