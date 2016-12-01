package merge;

import merge.Buffer.StoppException;

public class Divide<T extends Comparable<T>> extends Process<T> {

	Buffer<T> inputBuffer;
	Buffer<T> divLeft;
	Buffer<T> divRight;
	Sort<T> sortLeft;
	Sort<T> sortRight;

	/**
	 * Creates a new Divide Object and starts a new Thread to sort <inputBuffer>
	 * @param inputBuffer defines the buffer the divide-process is working with
	 **/
	public Divide(final Buffer<T> inputBuffer) {
		this.inputBuffer = inputBuffer;
		this.divLeft = new Buffer<T>();
		this.divRight = new Buffer<T>();
		
		this.sortLeft = new Sort<T>(this.divLeft);
		this.sortRight = new Sort<T>(this.divRight);
		
		this.startThread();
	}
/**
 * Calls the divide-Operation
 */
	@Override
	public void calculate() {
		this.divide();
	}

	/**
	 * Divides the inputBuffer into a leftBuffer <divLeft> and a rightBuffer <divRight>. The size of those buffers are the same or
	 * <divLeft> has one Element more than <divRight>. The last element will be a "Stopp-Element".  
	 */
	private void divide() {
		try {
			final T left = this.inputBuffer.get();
			this.divLeft.put(left);
			
			final T right = this.inputBuffer.get();
			this.divRight.put(right);
		} catch (final StoppException e) {
			this.divLeft.stopp();
			this.divRight.stopp();
			this.sortLeft.sort();
			this.sortRight.sort();
			this.stopThread();
		}
	}		
}
