package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import model.NoMoreBufferCopyAvailableException;
import regExpression.RegAdd;
import regExpression.RegConstant;
import regExpression.RegularExpression;
import regExpression.RegularExpressionManager;

public class regExTest {

	@Test
	public void regExpToProcessTest1() throws NoMoreBufferCopyAvailableException {
		RegConstant constant5 = new RegConstant(5);
		RegConstant constant6 = new RegConstant(6);
		
		RegAdd add1 = new RegAdd(constant5, constant6);
		RegAdd add2 = new RegAdd(add1, constant6);
		
		RegConstant constant52 = new RegConstant(5);
		RegConstant constant62 = new RegConstant(6);
		
		RegAdd add3 = new RegAdd(constant52, constant62);
		RegAdd add4 = new RegAdd(add3, add2);

		add4.toProcess();
	}
	
	@Test
	public void regExpEqualsTest1() {
		RegConstant constant5 = new RegConstant(5);
		RegConstant constant6 = new RegConstant(6);
		
		RegAdd add1 = new RegAdd(constant5, constant6);
		
		RegAdd add2 = new RegAdd(constant5, constant6);
		
		assertTrue(add1.equals(add2));
	}
	
	@Test
	public void regExpEqualsTest2() {
		RegConstant constant5 = new RegConstant(5);
		RegConstant constant6 = new RegConstant(6);
		
		RegAdd add1 = new RegAdd(constant5, constant6);
		RegAdd add2 = new RegAdd(add1, constant6);
		
		RegConstant constant52 = new RegConstant(5);
		RegConstant constant62 = new RegConstant(6);
		
		RegAdd add3 = new RegAdd(constant52, constant62);
		RegAdd add4 = new RegAdd(add3, constant6);
		
		assertTrue(add2.equals(add4));
	}
	
	@Test
	public void regExpEqualsTest3() {
		RegConstant constant5 = new RegConstant(5);
		RegConstant constant52 = new RegConstant(5);
		
		assertTrue(constant5.equals(constant52));
	}
	
	@Test
	public void regExpEqualsTest4() {
		RegConstant constant5 = new RegConstant(5);
		RegConstant constant6 = new RegConstant(6);
		
		assertFalse(constant5.equals(constant6));
	}
	
	@Test
	public void regExpContainsTest1() {
		RegConstant constant5 = new RegConstant(5);
		RegConstant constant52 = new RegConstant(5);
		
		List<RegularExpression> list = new ArrayList<RegularExpression>();
		list.add(constant5);
		
		assertTrue(list.contains(constant52));
	}
	
	@Test
	public void regExpToContainsTest2() {
		RegConstant constant5 = new RegConstant(5);
		RegConstant constant6 = new RegConstant(6);
		
		RegularExpressionManager.getInstance().add(constant5);
		RegularExpressionManager.getInstance().add(constant5);
		
		RegAdd add1 = new RegAdd(constant5, constant6);
//		RegAdd add2 = new RegAdd(add1, constant6);
		
		RegularExpressionManager.getInstance().add(add1);
		
		
//		add1.toProcess();
	}
}
