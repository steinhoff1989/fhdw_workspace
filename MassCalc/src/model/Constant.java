package model;

public class Constant extends Process {
	
	public int value;

	public Constant(int value) {
		super(new Buffer<Integer>(), new Buffer<Integer>());
		this.value = value;
		Buffer<Integer> result = new Buffer<Integer>();
		result.put(value);
	}

	@Override
	public int calculate(int value1, int value2) {
		// TODO Auto-generated method stub
		return 0;
	}

}
