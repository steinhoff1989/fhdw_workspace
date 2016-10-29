package regExpression;

public interface AETwoPartOperationVisitor {

	boolean handdle(AEAdd regAdd);
	boolean handdle(AESubtract regSubtract);
	boolean handdle(AEMultiply regMultiply);
	boolean handdle(AEDivide regDivide);
}
