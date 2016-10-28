package regExpression;

public class RegularExpressionQuantity {

	private final RegularExpression regExp;
	private int counter;
	
	public RegularExpressionQuantity(RegularExpression regExp) {
		super();
		this.regExp = regExp;
		this.counter = 1;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof RegularExpressionQuantity){
			RegularExpressionQuantity regExpQ = (RegularExpressionQuantity)obj;
			return this.regExp.equals(regExpQ.getRegExp());
		}
		return false;
	}
	
	public void increment(){
		this.counter++;
	}

	public int getCounter() {
		return counter;
	}
	public RegularExpression getRegExp() {
		return regExp;
	}

}
