package model;

public class Subtract extends Process{

	public Subtract(Buffer<Integer> streamOne, Buffer<Integer> streamTwo) {
		super(streamOne, streamTwo);
	}

	@Override
	public int calculate(int value1, int value2) {
		return value1 - value2;
	}

}
