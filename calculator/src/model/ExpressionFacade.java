package model;

import java.util.Vector;

public class ExpressionFacade {

	public static ExpressionFacade createExpressionFacade() {
		return new ExpressionFacade(new Vector<Expression>(), new Vector<Variable>());
	}

	private final Vector<Expression> expressions;
	private final Vector<Variable> variables;

	private ExpressionFacade(final Vector<Expression> expressions, final Vector<Variable> variables) {
		this.expressions = expressions;
		this.variables = variables;
	}

	public void createVariable(final String name) {
		final Variable newVariable = Variable.createVariable(name);
		this.getExpressions().add(newVariable);
		this.getVariables().add(newVariable);
	}

	public Vector<Expression> getExpressions() {
		return this.expressions;
	}

	public Vector<Variable> getVariables() {
		return this.variables;
	}

	public void up(final Variable variable) {
		variable.up();
	}

	public void down(final Variable variable) {
		variable.down();
	}

	public void createAdd(final Expression firstArgument, final Expression secondArgument) {
		final Add newAdd = Add.create(firstArgument, secondArgument);
		this.getExpressions().add(newAdd);
		firstArgument.register(newAdd);
		secondArgument.register(newAdd);
	}

	public void createSubtract(final Expression firstArgument, final Expression secondArgument) {
		final Subtract newSubtract = Subtract.create(firstArgument, secondArgument);
		this.getExpressions().add(newSubtract);
		firstArgument.register(newSubtract);
		secondArgument.register(newSubtract);
	}

	public void createMultiply(final Expression firstArgument, final Expression secondArgument) {
		final Multiply newMultiply = Multiply.create(firstArgument, secondArgument);
		this.getExpressions().add(newMultiply);
		firstArgument.register(newMultiply);
		secondArgument.register(newMultiply);
	}

	public void createDivide(final Expression firstArgument, final Expression secondArgument) {
		Divide newDivide;
		try {
			newDivide = Divide.create(firstArgument, secondArgument);
			this.getExpressions().add(newDivide);
			firstArgument.register(newDivide);
			secondArgument.register(newDivide);
		} catch (CreationWithSeconArgumentZeroException e) {
			e.printStackTrace();
		}
	}

}
