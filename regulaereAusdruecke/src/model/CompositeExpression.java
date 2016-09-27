package model;

import java.util.ArrayList;
import java.util.List;

public abstract class CompositeExpression extends RegularExpression{

	List<RegularExpression> regExp;
	
	public CompositeExpression(){
		regExp = new ArrayList<RegularExpression>();
	}
	
	public CompositeExpression(RegularExpression regEx1, RegularExpression regEx2){
		regExp = new ArrayList<RegularExpression>();
		regExp.add(regEx1);
		regExp.add(regEx2);
	}
}
