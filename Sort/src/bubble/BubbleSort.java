package bubble;

import BufferAndLock.Buffer;
import BufferAndLock.Buffer.StoppException;
//import bubble.Buffer.StoppException;

public class BubbleSort<T extends Comparable<T>> {

	private final Buffer<T> inputBuffer;
	private final Buffer<T> outputBuffer;
	private boolean bubbled = false;
	private final Buffer<T> resultBuffer;

	private BubbleSort<T> nextBubbleSort;

	
	/**
	 * Creates a new BubbleSort Object and starts a new Thread to sort <inputBuffer>
	 * @param inputBuffer defines the buffer the algorithm is starting with
	 */
	public BubbleSort(final Buffer<T> inputBuffer) {
		this.inputBuffer = inputBuffer;
		this.outputBuffer = new Buffer<>();
		this.resultBuffer = new Buffer<>();

		final Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				BubbleSort.this.sort();
			}
		});
		t1.start();
	}

	/**
	 * sorts the <inputBuffer> and writes the result to <resultBuffer>
	 */
	private void sort() {
		T firstArg = null;

		try {
			firstArg = BubbleSort.this.inputBuffer.get();
		} catch (final StoppException e) {
			BubbleSort.this.outputBuffer.stopp();
			return;
		}
		this.sortHelper(firstArg);
		
		if (this.bubbled) {
			this.adaptResultFromNextThread();
		} else {
			this.adaptResultFromOutput();
		}
	}

	/**
	 * Uses <inputBuffer> to creates the <outputBuffer> so that the greatest element will be at the end
	 * @param firstArg
	 */
	private void sortHelper(final T firstArg) {
		T secondArg = null;
		try {
			secondArg = this.inputBuffer.get();
		} catch (final StoppException e) {
			this.outputBuffer.put(firstArg);
			this.outputBuffer.stopp();
			return;
		}

		if (firstArg.compareTo(secondArg) <= 0) {
			this.outputBuffer.put(firstArg);
			this.sortHelper(secondArg);
		} else {
			this.outputBuffer.put(secondArg);
			if (!this.bubbled) {
				this.bubbled = true;
				this.nextBubbleSort = new BubbleSort<>(this.outputBuffer);
			}
			this.sortHelper(firstArg);
		}
	}

	/**
	 * Returns the next element that is available in <resultBuffer>
	 * @return
	 * @throws StoppException
	 */
	public T getNextElement() throws StoppException {
		return this.resultBuffer.get();
	}
	
	/**
	 * Writes all elements of <outputBuffer> in <resultBuffer>.
	 */
	private void adaptResultFromOutput() {
		while (true) {
			try {
				this.resultBuffer.put(this.outputBuffer.get());
			} catch (final StoppException e) {
				this.resultBuffer.stopp();
				return;
			}
		}
	}

	/**
	 * Writes the elements of the started thread resultBuffer in this.resultBuffer.
	 */
	private void adaptResultFromNextThread() {
		while (true) {
			try {
				this.resultBuffer.put(this.nextBubbleSort.resultBuffer.get());
			} catch (final StoppException e) {
				this.resultBuffer.stopp();
				return;
			}
		}
	}

}
