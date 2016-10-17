package model;

public class Constant extends Process {
	
	public int value;

	public Constant(int value) {
		super(new Buffer<Integer>(), new Buffer<Integer>());
		this.value = value;
		this.getStreamResult().put(value);
	}

	@Override
	public void calculate() {
	}

}
