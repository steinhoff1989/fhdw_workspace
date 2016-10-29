package regExpression;

public interface AEVisitor {

	boolean handle(AEConstant regConstant);
	boolean handle(AETwoPartOperation regTwoPartOperation);
	
}
