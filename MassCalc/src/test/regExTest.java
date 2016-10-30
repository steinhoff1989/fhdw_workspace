package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import BufferAndLock.Buffer;
import BufferAndLock.Buffer.StoppException;
import exceptions.DivideByZeroException;
import exceptions.NoMoreVariablesAvailableException;
import model.Constant;
import regExpression.AEAdd;
import regExpression.AEConstant;
import regExpression.AESubtract;
import regExpression.ArithmeticExpression;
import regExpression.StructureManager;

public class regExTest {

	@Before
	public void setUP(){
		StructureManager.getInstance().clear();
	}
	
	@Test
	public void regExpToProcessTest1() throws NoMoreVariablesAvailableException, StoppException, DivideByZeroException {
		AEConstant constant5 = new AEConstant(5);
		AEConstant constant5_2 = new AEConstant(5);
		
		AEAdd add1 = new AEAdd(constant5, constant5_2);//10

		Buffer<Integer> result = add1.calculate();
		
		assertEquals(2, add1.getRegExpManager().countVariables());
		
		
		assertEquals(10, result.get().intValue());
	}
	
	@Test
	public void regExpToProcessTest2() throws NoMoreVariablesAvailableException, StoppException, DivideByZeroException {
		AEConstant constant5_1 = new AEConstant(5);//NumberVariables: 1
		AEConstant constant5_2 = new AEConstant(5);//NumberVariables: 1
		AEConstant constant5_3 = new AEConstant(5);//NumberVariables: 1
		AEConstant constant5_4 = new AEConstant(5);//NumberVariables: 1
//		AEConstant constant5_5 = new AEConstant(5);
//		AEConstant constant5_6 = new AEConstant(5);
//		AEConstant constant5_7 = new AEConstant(5);
//		AEConstant constant5_8 = new AEConstant(5);
		
		AEAdd add_5plus5_1 = new AEAdd(constant5_1, constant5_2);//10 //NumberVariables: 2
		AEAdd add_5plus5_2 = new AEAdd(constant5_3, constant5_4);//10 //NumberVariables: 2
//		AEAdd add_5plus5_3 = new AEAdd(constant5_5, constant5_6);//10
//		AEAdd add_5plus5_4 = new AEAdd(constant5_7, constant5_8);//10

		AEAdd add = new AEAdd(add_5plus5_1, add_5plus5_2);//20 //NumberVariables: 2
				
		Buffer<Integer> result = add.calculate();
		
		assertEquals(3, add.getRegExpManager().countArithmeticExpressions());

		assertEquals(3, add.getRegExpManager().countVariables());
		
		assertEquals(20, result.get().intValue());
	}
	
	@Test
	public void regExpToProcessTest3() throws NoMoreVariablesAvailableException, StoppException, DivideByZeroException {
		AEConstant constant5_1 = new AEConstant(5);//NumberVariables: 1
		AEConstant constant5_2 = new AEConstant(5);//NumberVariables: 1
		AEConstant constant5_3 = new AEConstant(5);//NumberVariables: 1
		AEConstant constant5_4 = new AEConstant(5);//NumberVariables: 1
		AEConstant constant5_5 = new AEConstant(5);//NumberVariables: 1
		AEConstant constant5_6 = new AEConstant(5);//NumberVariables: 1
		AEConstant constant5_7 = new AEConstant(5);//NumberVariables: 1
		AEConstant constant5_8 = new AEConstant(5);//NumberVariables: 1
		
		AEAdd add_5plus5_1 = new AEAdd(constant5_1, constant5_2);//10 //NumberVariables: 2
		AEAdd add_5plus5_2 = new AEAdd(constant5_3, constant5_4);//10 //NumberVariables: 2
		AEAdd add_5plus5_3 = new AEAdd(constant5_5, constant5_6);//10 //NumberVariables: 2
		AEAdd add_5plus5_4 = new AEAdd(constant5_7, constant5_8);//10 //NumberVariables: 2

		AEAdd add = new AEAdd(add_5plus5_1, add_5plus5_2);//20 //NumberVariables: 3
		AEAdd add2 = new AEAdd(add_5plus5_3, add_5plus5_4);//20 //NumberVariables: 3
		
		AEAdd add3 = new AEAdd(add, add2);//40 //NumberVariables: 4
				
		Buffer<Integer> result = add3.calculate();
		
		assertEquals(4, add.getRegExpManager().countArithmeticExpressions());

		assertEquals(4, add.getRegExpManager().countVariables());
		
		assertEquals(40, result.get().intValue());
	}
	
	@Test
	public void regExpToProcessTest6() throws NoMoreVariablesAvailableException, StoppException, DivideByZeroException {
		AEConstant constant7_1 = new AEConstant(7);//NumberVariables: 1
		AEConstant constant7_2 = new AEConstant(7);//NumberVariables: 1
		AEConstant constant7_3 = new AEConstant(7);//NumberVariables: 1
		AEConstant constant7_4 = new AEConstant(7);//NumberVariables: 1
		
		AEAdd add_7plus7_1 = new AEAdd(constant7_1, constant7_2);//14 //NumberVariables: 2
		AEAdd add_7plus7_2 = new AEAdd(constant7_3, constant7_4);//14 //NumberVariables: 2

		AEAdd add = new AEAdd(add_7plus7_1, add_7plus7_2);//28 //NumberVariables: 2
				
		Buffer<Integer> result = add.calculate(); //NumberVariables: 3
		
		assertEquals(3, add.getRegExpManager().countArithmeticExpressions());

		assertEquals(3, add.getRegExpManager().countVariables());
		
		assertEquals(28, result.get().intValue());
	}
	
	@Test
	public void regExpToProcessTest4() throws NoMoreVariablesAvailableException, StoppException, DivideByZeroException {
		AEConstant constant5 = new AEConstant(5); //No of AE: 1 //No of Variables: 1 //Threads: 1 (Constant thread)
		AEConstant constant6 = new AEConstant(6); //No of AE: 2  //No of Variables: 2 //Threads: 2 (Constant thread)
		
		AEAdd add1 = new AEAdd(constant5, constant6);//11 //No of AE: 3 //No of Variables: 3 //Threads: 4 (Add + Variable)
		AEAdd add2 = new AEAdd(add1, constant6);//17 //No of AE: 4 //No of Variables: 4 //Threads: 6 (Add + Variable)
		
		AEConstant constant5_2 = new AEConstant(5); //No of AE: 4 //No of Variables: 4 //Threads: 6 (already available)
		AEConstant constant6_2 = new AEConstant(6); //No of AE: 4 //No of Variables: 4 //Threads: 6 (already available)
		
		AEAdd add3 = new AEAdd(constant5_2, constant6_2);//11  //No of AE: 4  //No of Variables: 2 //Threads: 6 (already available)
		AEAdd add4 = new AEAdd(add3, add2);//28 //No of AE: 5 //No of Variables: 5 //Threads: 8 (Add + Variable)

		Buffer<Integer> result = add4.calculate(); //Threads: 10 (Add + Variable)
		
		assertEquals(5, add4.getRegExpManager().countArithmeticExpressions());
		
		assertEquals(5, add4.getRegExpManager().countVariables());
		
		assertEquals(28, result.get().intValue());
	}
	
	@Test
	public void regExpToProcessTest5() throws NoMoreVariablesAvailableException, StoppException, DivideByZeroException {
		AEConstant constant5 = new AEConstant(5); //No of AE: 1 //No of Variables: 1 //Threads: 1 (Constant thread)
		AEConstant constant6 = new AEConstant(6); //No of AE: 2 //No of Variables: 2 //Threads: 2 (Constant thread)
		
		AEAdd add1 = new AEAdd(constant5, constant6);//11  //No of AE: 3 //No of Variables: 3 //Threads: 4 (Add + Variable)
		AEAdd add2 = new AEAdd(add1, constant6);//17 //No of AE: 4 //No of Variables: 4 //Threads: 6 (Add + Variable)
		
		AEConstant constant8 = new AEConstant(8); //No of AE: 5 //No of Variables: 5  //Threads: 7
		AEConstant constant12 = new AEConstant(12); //No of AE: 6 //No of Variables: 6 //Threads: 8
		
		AESubtract sub1 = new AESubtract(constant8, constant12);//-4  //No of AE: 7 //No of Variables: 7 //Threads: 10
		AEAdd add5 = new AEAdd(add2, sub1);//37 //No of AE: 8 //No of Variables: 8 //Threads: 12 (Add + Variable)

		Buffer<Integer> result = add5.calculate(); //Threads: 14 (Add + Variable)
		
		assertEquals(8, add5.getRegExpManager().countArithmeticExpressions());
		
		assertEquals(8, add5.getRegExpManager().countVariables());
		
		assertEquals(13, result.get().intValue());
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
	public void regExpContainsTest2() {
		AEConstant constant5 = new AEConstant(5);
		AEConstant constant52 = new AEConstant(5);
		
		Set<ArithmeticExpression> set = new HashSet<ArithmeticExpression>();
		set.add(constant5);
		
		assertTrue(set.contains(constant52));
	}
	
//	@Test
//	public void regExpToContainsTest2() {
//		AEConstant constant5 = new AEConstant(5);
//		AEConstant constant6 = new AEConstant(6);
//		
//		StructureManager.getInstance().addAE(constant5);
//		StructureManager.getInstance().addAE(constant5);
//		
//		AEAdd add1 = new AEAdd(constant5, constant6);
//		
//		StructureManager.getInstance().addAE(add1);
//	}
}
