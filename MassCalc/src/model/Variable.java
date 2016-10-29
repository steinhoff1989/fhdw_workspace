package model;

import java.util.ArrayList;
import java.util.List;

import BufferAndLock.Buffer;
import BufferAndLock.Buffer.StoppException;
import exceptions.DivideByZeroException;
import exceptions.NoMoreVariablesAvailableException;

public class Variable extends Process {

	final private int numberOfCopies;
	private List<Buffer<Integer>> bufferCopies;
	private int index;

	/**
	 * Creates a variable object 
	 * @param streamOne: the stream that should be available for direct access multiple times.
	 * @param numberOfCopies: Tells how often this variable object is accessable.
	 */
	public Variable(Buffer<Integer> inputStream, int numberOfCopies) {
		super(inputStream, new Buffer<Integer>(INTERNAL_BUFFER_SIZE));
		this.numberOfCopies = numberOfCopies;
		this.bufferCopies = new ArrayList<Buffer<Integer>>();
		this.index = 0;
		for (int i = 0; i < numberOfCopies; i++) {
			this.bufferCopies.add(new Buffer<Integer>(INTERNAL_BUFFER_SIZE));
		}
		this.startThread();
	}

	@Override
	public void calculate() {
		try {
			final Integer value = this.getStreamOne().get();
			for (int i = 0; i < numberOfCopies; i++) {
				this.bufferCopies.get(i).put(value);
			}
		} catch (final StoppException e) {
			for (int i = 0; i < numberOfCopies; i++) {
				this.bufferCopies.get(i).stopp();
			}
		} catch (final DivideByZeroException e) {
			for (int i = 0; i < numberOfCopies; i++) {
				this.bufferCopies.get(i).dividedByZero();
			}
		}
	}

	/**
	 * @return Returns the next element of <bufferCopies>
	 * @throws NoMoreVariablesAvailableException
	 * If there is no more copy available in <bufferCopies> this exception is thrown.
	 */
	private Buffer<Integer> getNextBufferCopy() throws NoMoreVariablesAvailableException {
		if (this.index == this.numberOfCopies) {
			throw new NoMoreVariablesAvailableException("All " + this.numberOfCopies + " copies of this Varible already used!");
		}
		Buffer<Integer> nextResultBuffer = bufferCopies.get(index);
		index++;
		return nextResultBuffer;
	}

	@Override
	public Buffer<Integer> getResults() throws NoMoreVariablesAvailableException {
		return this.getNextBufferCopy();
	}

}
