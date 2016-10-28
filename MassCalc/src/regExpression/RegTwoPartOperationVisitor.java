package regExpression;

public interface RegTwoPartOperationVisitor {

	boolean handdle(RegAdd regAdd);
	boolean handdle(RegSubtract regSubtract);
	boolean handdle(RegMultiply regMultiply);
	boolean handdle(RegDivide regDivide);
}
