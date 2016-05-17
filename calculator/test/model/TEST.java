package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import javax.swing.JOptionPane;

import org.junit.Before;
import org.junit.Test;

public class TEST {

	private Variable v_n2;
	private Variable v_n1;
	private Variable v_0;
	private Variable v_1;
	private Variable v_2;

	@Before
	public void setUp() {
		// value = -2
		v_n2 = Variable.createVariable("A");
		v_n2.down();
		v_n2.down();

		// value = -1
		v_n1 = Variable.createVariable("B");
		v_n1.down();

		// value = 0
		v_0 = Variable.createVariable("B");

		// value = 1
		v_1 = Variable.createVariable("B");
		v_1.up();

		// value = 2
		v_2 = Variable.createVariable("B");
		v_2.up();
		v_2.up();
	}

	@Test
	public void VariableUpTest() {
		v_1.up();
		assertEquals(2, v_1.getValue());

		v_0.up();
		assertEquals(1, v_0.getValue());

		v_n1.up();
		assertEquals(0, v_n1.getValue());

		v_n2.up();
		assertEquals(-1, v_n2.getValue());
	}

	@Test
	public void VariableDownTest() {
		v_2.down();
		assertEquals(1, v_2.getValue());

		v_1.down();
		assertEquals(0, v_1.getValue());

		v_0.down();
		assertEquals(-1, v_0.getValue());

		v_n1.down();
		assertEquals(-2, v_n1.getValue());
	}

	@Test
	public void AddTest() throws DivisionByZeroException {
		Expression ex = Add.create(v_1, v_2);
		assertEquals(3, ex.getValue());

		ex = Add.create(v_0, v_1);
		assertEquals(1, ex.getValue());

		ex = Add.create(v_n1, v_0);
		assertEquals(-1, ex.getValue());

		ex = Add.create(v_0, v_0);
		assertEquals(0, ex.getValue());

		ex = Add.create(v_n1, v_2);
		assertEquals(1, ex.getValue());

		ex = Add.create(v_1, v_n1);
		assertEquals(0, ex.getValue());

		ex = Add.create(v_n1, v_n2);
		assertEquals(-3, ex.getValue());
	}

	@Test
	public void Multiplytest() throws DivisionByZeroException {
		Expression ex = Multiply.create(v_1, v_2);
		assertEquals(2, ex.getValue());

		ex = Multiply.create(v_0, v_1);
		assertEquals(0, ex.getValue());

		ex = Multiply.create(v_n1, v_0);
		assertEquals(0, ex.getValue());

		ex = Multiply.create(v_0, v_0);
		assertEquals(0, ex.getValue());

		ex = Multiply.create(v_n2, v_2);
		assertEquals(-4, ex.getValue());

		ex = Multiply.create(v_1, v_n2);
		assertEquals(-2, ex.getValue());

		ex = Multiply.create(v_n1, v_n2);
		assertEquals(2, ex.getValue());
	}

	@Test
	public void SubtractTest() throws DivisionByZeroException {
		Expression ex = Subtract.create(v_1, v_2);
		assertEquals(-1, ex.getValue());

		ex = Subtract.create(v_0, v_1);
		assertEquals(-1, ex.getValue());

		ex = Subtract.create(v_n1, v_0);
		assertEquals(-1, ex.getValue());

		ex = Subtract.create(v_0, v_0);
		assertEquals(0, ex.getValue());

		ex = Subtract.create(v_n2, v_2);
		assertEquals(-4, ex.getValue());

		ex = Subtract.create(v_1, v_n2);
		assertEquals(3, ex.getValue());

		ex = Subtract.create(v_n1, v_n2);
		assertEquals(1, ex.getValue());
	}

	@Test
	public void DivideWithoutExceptionTest() throws DivisionByZeroException {
		Expression ex;
		ex = Divide.create(v_1, v_2);
		assertEquals(0, ex.getValue());

		ex = Divide.create(v_0, v_1);
		assertEquals(0, ex.getValue());

		ex = Divide.create(v_2, v_1);
		assertEquals(2, ex.getValue());

		ex = Divide.create(v_n2, v_2);
		assertEquals(-1, ex.getValue());

		ex = Divide.create(v_1, v_n2);
		assertEquals(0, ex.getValue());

		ex = Divide.create(v_n2, v_n1);
		assertEquals(2, ex.getValue());
	}

	@Test
	public void DivideExceptionTest() {
		try {
			Expression ex = Divide.create(v_1, v_0);
			ex.getValue();
			fail();
		} catch (DivisionByZeroException e) {
		}

		try {
			Expression ex = Divide.create(v_0, v_0);
			ex.getValue();
			fail();
		} catch (DivisionByZeroException e) {
		}
		
	}
}
