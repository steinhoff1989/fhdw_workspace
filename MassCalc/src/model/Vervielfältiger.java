package model;

import java.util.ArrayList;
import java.util.List;

import model.Buffer.StoppException;

public class Vervielf�ltiger extends Process {

	private int numberOfCopies;
	private List<Buffer<Integer>> bufferCopies;
	private int index;

	public Vervielf�ltiger(Buffer<Integer> streamOne, int numberOfCopies) {
		super(streamOne, new Buffer<Integer>(100));
		this.numberOfCopies = numberOfCopies;
		this.bufferCopies = new ArrayList<Buffer<Integer>>();
		this.index = 0;
		for (int i = 0; i < numberOfCopies; i++) {
			this.bufferCopies.add(new Buffer<Integer>(100));
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

	public Buffer<Integer> getBufferCopy(final int index) {
		return bufferCopies.get(index);
	}

	public Buffer<Integer> getNextBufferCopy() throws NoMoreBufferCopyAvailableException {
		if (this.index == this.numberOfCopies) {
			throw new NoMoreBufferCopyAvailableException("All " + this.numberOfCopies + " copies of this Vervielf�ltiger already used!");
		}
		Buffer<Integer> bufferAtIndex = this.getBufferCopy(index);
		index++;
		return bufferAtIndex;
	}

}
