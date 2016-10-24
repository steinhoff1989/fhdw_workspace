package bubble;

import bubble.Buffer.StoppException;

public class BubbleSort<T extends Comparable<T>> {

	private Buffer<T> inputBuffer;
	private Buffer<T> outputBuffer;
	private boolean bubbled = false;
	private Buffer<T> resultBuffer;

	private BubbleSort<T> nextBubbleSort;

	
	/**
	 * Creates a new BubbleSort Object and starts a new Thread to sort <inputBuffer>
	 * @param inputBuffer
	 */
	public BubbleSort(Buffer<T> inputBuffer) {
		this.inputBuffer = inputBuffer;
		this.outputBuffer = new Buffer<>();
		this.resultBuffer = new Buffer<>();

		Thread t1 = new Thread(new Runnable() {
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
		} catch (StoppException e) {
			BubbleSort.this.outputBuffer.stopp();
			return;
		}
		sortHelper(firstArg);
		
		if (bubbled) {
			adaptResultFromNextThread();
		} else {
			adaptResultFromOutput();
		}
	}

	/**
	 * Uses <inputBuffer> to creates the <outputBuffer> so that the greatest element will be at the end
	 * @param firstArg
	 */
	private void sortHelper(T firstArg) {
		T secondArg = null;
		try {
			secondArg = this.inputBuffer.get();
		} catch (StoppException e) {
			this.outputBuffer.put(firstArg);
			this.outputBuffer.stopp();
			return;
		}

		if (firstArg.compareTo(secondArg) <= 0) {
			this.outputBuffer.put(firstArg);
			sortHelper(secondArg);
		} else {
			this.outputBuffer.put(secondArg);
			if (!bubbled) {
				this.bubbled = true;
				nextBubbleSort = new BubbleSort<>(this.outputBuffer);
			}
			sortHelper(firstArg);
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
			} catch (StoppException e) {
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
				this.resultBuffer.put(nextBubbleSort.resultBuffer.get());
			} catch (StoppException e) {
				this.resultBuffer.stopp();
				return;
			}
		}
	}

}
