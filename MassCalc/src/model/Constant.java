package model;

public class Constant implements ArithmetischerAusdruck{
	
	BufferConstant<Integer> streamResult;
	
	public Constant(int value) {
		streamResult = new BufferConstant<Integer>();
		this.streamResult.put(value);
	}

	public BufferConstant<Integer> getStreamResult() {
		return streamResult;
	}

	@Override
	public boolean contains(ArithmetischerAusdruck ar1) {
		return this.equals(ar1);
	}
}
