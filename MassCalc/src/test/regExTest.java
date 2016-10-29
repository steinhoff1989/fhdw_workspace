package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import BufferAndLock.Buffer;
import BufferAndLock.Buffer.StoppException;
import exceptions.DivideByZeroException;
import exceptions.NoMoreVariablesAvailableException;
import regExpression.AEAdd;
import regExpression.AEConstant;
import regExpression.ArithmeticExpression;
import regExpression.StructureManager;

public class regExTest {

	@Test
	public void regExpToProcessTest1() throws NoMoreVariablesAvailableException, StoppException, DivideByZeroException {
		AEConstant constant5 = new AEConstant(5);
		AEConstant constant6 = new AEConstant(6);
		
		AEAdd add1 = new AEAdd(constant5, constant6);//11
		AEAdd add2 = new AEAdd(add1, constant6);//17
		
		AEConstant constant5_2 = new AEConstant(5);
		AEConstant constant6_2 = new AEConstant(6);
		
		AEAdd add3 = new AEAdd(constant5_2, constant6_2);//11
		AEAdd add4 = new AEAdd(add3, add2);//28

		Buffer<Integer> result = add4.calculate();
		
		assertEquals(28, result.get().intValue());
	}
	
	@Test
	public void regExpEqualsTest1() {
		AEConstant constant5 = new AEConstant(5);
		AEConstant constant6 = new AEConstant(6);
		
		AEAdd add1 = new AEAdd(constant5, constant6);
		
		AEAdd add2 = new AEAdd(constant5, constant6);
		
		assertTrue(add1.equals(add2));
	}
	
	@Test
	public void regExpEqualsTest2() {
		AEConstant constant5 = new AEConstant(5);
		AEConstant constant6 = new AEConstant(6);
		
		AEAdd add1 = new AEAdd(constant5, constant6);
		AEAdd add2 = new AEAdd(add1, constant6);
		
		AEConstant constant52 = new AEConstant(5);
		AEConstant constant62 = new AEConstant(6);
		
		AEAdd add3 = new AEAdd(constant52, constant62);
		AEAdd add4 = new AEAdd(add3, constant6);
		
		assertTrue(add2.equals(add4));
	}
	
	@Test
	public void regExpEqualsTest3() {
		AEConstant constant5 = new AEConstant(5);
		AEConstant constant52 = new AEConstant(5);
		
		assertTrue(constant5.equals(constant52));
	}
	
	@Test
	public void regExpEqualsTest4() {
		AEConstant constant5 = new AEConstant(5);
		AEConstant constant6 = new AEConstant(6);
		
		assertFalse(constant5.equals(constant6));
	}
	
	@Test
	public void regExpContainsTest1() {
		AEConstant constant5 = new AEConstant(5);
		AEConstant constant52 = new AEConstant(5);
		
		List<ArithmeticExpression> list = new ArrayList<ArithmeticExpression>();
		list.add(constant5);
		
		assertTrue(list.contains(constant52));
	}
	
	@Test
	public void regExpToContainsTest2() {
		AEConstant constant5 = new AEConstant(5);
		AEConstant constant6 = new AEConstant(6);
		
		StructureManager.getInstance().addAE(constant5);
		StructureManager.getInstance().addAE(constant5);
		
		AEAdd add1 = new AEAdd(constant5, constant6);
		
		StructureManager.getInstance().addAE(add1);
	}
}
