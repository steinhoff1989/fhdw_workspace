package bubble;

import bubble.Buffer.StoppException;

public class BubbleSort {

	@SuppressWarnings({ "rawtypes" })
	private Buffer<Comparable> inputBuffer;
	@SuppressWarnings({ "rawtypes" })
	private Buffer<Comparable> outputBuffer;

	@SuppressWarnings({ "rawtypes" })
	public BubbleSort(Buffer<Comparable> inputBuffer) {
		this.inputBuffer = inputBuffer;
		this.outputBuffer = new Buffer<Comparable>();
	}

	@SuppressWarnings("unchecked")
	public void sort() {
		@SuppressWarnings("rawtypes")
		Comparable firstArg = null;

		try {
			firstArg = this.inputBuffer.get();
		} catch (StoppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sortHelper(firstArg);
	}

	public void sortHelper(Comparable firstArg) {
		if (!this.inputBuffer.isEmpty()) {
			Comparable secondArg = null;
			try {
				secondArg = this.inputBuffer.get();
			} catch (StoppException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (firstArg.compareTo(secondArg) <= 0) {
				this.outputBuffer.put(firstArg);
				sortHelper(secondArg);
			} else {
				this.outputBuffer.put(secondArg);
				sortHelper(firstArg);
			}
		}else{
			this.outputBuffer.put(firstArg);
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

}
