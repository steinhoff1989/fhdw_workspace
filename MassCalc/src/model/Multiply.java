package model;

public class Multiply extends Process{

	public Multiply(Buffer<Integer> streamOne, Buffer<Integer> streamTwo) {
		super(streamOne, streamTwo);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int calculate(int value1, int value2) {
		return value1 * value2;
	}

}
