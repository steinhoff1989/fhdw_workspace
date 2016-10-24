package model;

import java.util.ArrayList;
import java.util.List;

import model.Buffer.StoppException;

public class Vervielfältiger extends Process {

	private int numberOfCopies;
	private List<Buffer<Integer>> bufferCopies;

	public Vervielfältiger(Buffer<Integer> streamOne, int numberOfCopies) {
		super(streamOne, new Buffer<Integer>(100));
		this.numberOfCopies = numberOfCopies;
		this.bufferCopies = new ArrayList<Buffer<Integer>>();
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

}
