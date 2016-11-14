package merge;

import merge.Buffer.StoppException;

public class Merge<T extends Comparable<T>> extends Process<T> {

	private final Buffer<T> divLeft;
	private final Buffer<T> divRight;
	private final Buffer<T> resultBuffer;

	private Buffer<T> singleBufferLeft;
	private Buffer<T> singleBufferRight;
	
	
	public Merge(final Buffer<T> divLeft, final Buffer<T> divRight, final Buffer<T> resultBuffer) {
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
		this.merge(this.divLeft, this.divRight);
	}

	private void merge(final Buffer<T> firstBuffer, final Buffer<T> secondBuffer) {
		try {
			T first;
			try {
				first = this.singleBufferLeft.get();
			} catch (final StoppException e2) {
				first = firstBuffer.get();
			}
			try {
				T second;
				try {
					second = this.singleBufferRight.get();
				} catch (final StoppException e2) {
					second = secondBuffer.get();
				}
				if (first.compareTo(second) < 0) {
					this.resultBuffer.put(first);
					
					this.singleBufferRight = new Buffer<T>();
					this.singleBufferRight.put(second);
					this.singleBufferRight.stopp();

//					this.resultBuffer.put(new Merge<T>(firstBuffer, singleBuffer).resultBuffer.get());
//					this.merge(singleBuffer, firstBuffer);
				} else {
					this.resultBuffer.put(second);

//					Buffer<T> singleBuffer = new Buffer<T>();
//					singleBuffer.put(first);
//					singleBuffer.stopp();

					this.singleBufferLeft = new Buffer<T>();
					this.singleBufferLeft.put(first);
					this.singleBufferLeft.stopp();
					
//					this.merge(singleBuffer, secondBuffer);
				}
			} catch (final StoppException e) {
				this.resultBuffer.put(first);

//				this.merge(firstBuffer, secondBuffer);
			}
		} catch (final StoppException e) {
			try {
				T second;
				try {
					second = this.singleBufferRight.get();
				} catch (final StoppException e2) {
					second = secondBuffer.get();
				}
//				second = secondBuffer.get();
				this.resultBuffer.put(second);

//				this.merge(firstBuffer, secondBuffer);
			} catch (final StoppException e1) {
			this.resultBuffer.stopp();
			this.stopThread();
			}
		}
	}
}