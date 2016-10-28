package regExpression;

public interface RegularExpressionVisitor {

	boolean handle(RegConstant regConstant);
	boolean handle(RegTwoPartOperation regTwoPartOperation);
	
}
