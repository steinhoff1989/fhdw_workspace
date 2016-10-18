package model;

public class Constant extends Process {
	
	public Constant(int value) {
		super(new BufferConstant<Integer>(), new BufferConstant<Integer>());
		BufferConstant<Integer> bufferConstant = new BufferConstant<Integer>();
		this.setStreamResult(bufferConstant);
		this.getStreamResult().put(value);
	}

	@Override
	public void calculate() {
	}

}
