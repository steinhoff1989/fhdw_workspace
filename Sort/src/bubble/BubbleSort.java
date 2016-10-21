package bubble;

import bubble.Buffer.StoppException;

public class BubbleSort {

	@SuppressWarnings({ "rawtypes" })
	private Buffer<Comparable> inputBuffer;
	@SuppressWarnings({ "rawtypes" })
	private Buffer<Comparable> outputBuffer;
	private boolean bubbled = false;

	@SuppressWarnings({ "rawtypes" })
	public BubbleSort(Buffer<Comparable> inputBuffer) {
		this.inputBuffer = inputBuffer;
		this.outputBuffer = new Buffer<Comparable>();
	}

	public void sort() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				@SuppressWarnings("rawtypes")
				Comparable firstArg = null;

				try {
					firstArg = BubbleSort.this.inputBuffer.get();
				} catch (StoppException e) {
					BubbleSort.this.outputBuffer.stopp();
					return;
				}
				sortHelper(firstArg);

				if (bubbled) {
//					BubbleSort temp = new BubbleSort(this.outputBuffer);
//					this.outputBuffer = temp.outputBuffer;
					bubbled = false;
					BubbleSort.this.inputBuffer = BubbleSort.this.outputBuffer;
					BubbleSort.this.outputBuffer = new Buffer<Comparable>();
					BubbleSort.this.sort();
				}
			}
		}).start();
	}

	private void sortHelper(Comparable firstArg) {
		Comparable secondArg = null;
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
			this.bubbled = true;
			sortHelper(firstArg);
		}
	}

	public Buffer<Comparable> getInputBuffer() {
		return inputBuffer;
	}

	public void setInputBuffer(Buffer<Comparable> inputBuffer) {
		this.inputBuffer = inputBuffer;
	}

	public Buffer<Comparable> getOutputBuffer() {
		return outputBuffer;
	}

	public void setOutputBuffer(Buffer<Comparable> outputBuffer) {
		this.outputBuffer = outputBuffer;
	}
	
	public Comparable getNextElement() throws StoppException{
		return this.outputBuffer.get();
	}

}
