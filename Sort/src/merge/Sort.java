package merge;

//import merge.Buffer.StoppException;
import BufferAndLock.Buffer;
import BufferAndLock.Buffer.StoppException;;

public class Sort<T extends Comparable<T>> {

	private final Buffer<T> inputBuffer;
	private final Buffer<T> resultBuffer;
	private Divide<T> div;
	private Merge<T> merge;

	/**
	 * Create a new Sort-Object which will be sorted by the merge-sort algorithm
	 * @param inputBuffer defines the buffer which will be sorted.
	 */
	public Sort(final Buffer<T> inputBuffer) {
		this.inputBuffer = inputBuffer;
		this.resultBuffer = new Buffer<T>();
	}
/**
 * Starts the merge-sort algorithm on <inputBuffer>. Result is saved in <resultBuffer>
 */
	public void sort() {
		if (this.inputBuffer.size() <= 2) {
			try {
				this.resultBuffer.put(this.inputBuffer.get());
				this.resultBuffer.put(this.inputBuffer.get());
			} catch (final StoppException e) {
				this.resultBuffer.stopp();
			}
		} else {
			this.div = new Divide<T>(this.inputBuffer);
			this.merge = new Merge<T>(this.div.sortLeft.resultBuffer, this.div.sortRight.resultBuffer, this.resultBuffer);
		}
	}

	/**
	 * Getter for <resultBuffer>
	 * @returns <resultBuffer>
	 */
	public Buffer<T> getResultBuffer() {
		return this.resultBuffer;
	}
}
