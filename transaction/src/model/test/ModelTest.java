package model.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import model.Account;
import model.Transfer;

public class ModelTest {

	
	@BeforeClass
	public static void setUp(){
		
	}
	
	@Test
	public void transferTest1() {
		Account a1 = new Account("Account1");
		Account a2 = new Account("Account2");
		
		Transfer t1 = new Transfer(a1, a2, 0, "Test1");
		t1.book();
		
		if(a1.getAccountEntries().size() == 1 ||
				a2.getAccountEntries().size() == 1){
			fail();
		}
	}
	
	@Test
	public void transferTest2() {
		Account a1 = new Account("Account1");
		Account a2 = new Account("Account2");
		
		Transfer t1 = new Transfer(a1, a2, -5, "Test1");
		t1.book();
		
		assertEquals(-5, a1.getBalance());
	}
	
	@Test
	public void transferTest3() {
		Account a1 = new Account("Account1");
		Account a2 = new Account("Account2");
		
		Transfer t1 = new Transfer(a1, a2, 5, "Test1");
		t1.book();
		
		assertEquals(5, a1.getBalance());
	}
	
	@Test
	public void transferTest4() {
		Account a1 = new Account("Account1");
		Account a2 = new Account("Account2");
		
		Transfer t1 = new Transfer(a1, a2, 5, "Test1");
		t1.book();
		
		Transfer t2 = new Transfer(a2, a1, 5, "Test1");
		t2.book();
		
		assertEquals(0, a1.getBalance());
		assertEquals(0, a2.getBalance());
	}
	
	@Test
	public void transferTest5() {
		Account a1 = new Account("Account1");
		Account a2 = new Account("Account2");
		
		Transfer t1 = new Transfer(a1, a2, -3, "Test1");
		t1.book();

		Transfer t2 = new Transfer(a1, a2, 5, "Test1");
		t2.book();
		
		assertEquals(-2, a1.getBalance());
	}
	
	@Test
	public void transferTest6() {
		Account a1 = new Account("Account1");
		Account a2 = new Account("Account2");
		
		Transfer t1 = new Transfer(a1, a2, 3, "Test1");
		t1.book();

		Transfer t2 = new Transfer(a1, a2, 5, "Test1");
		t2.book();
		
		assertEquals(-8, a1.getBalance());
		assertEquals(8, a2.getBalance());
	}
	
	@Test
	public void transferTest7() {
		Account a1 = new Account("Account1");
		Account a2 = new Account("Account2");
		
		Transfer t1 = new Transfer(a1, a2, -3, "Test1");
		t1.book();

		Transfer t2 = new Transfer(a1, a2, -5, "Test1");
		t2.book();
		
		assertEquals(8, a1.getBalance());
		assertEquals(-8, a2.getBalance());
	}
	
	@Test
	public void transferTest8() {
		Account a1 = new Account("Account1");
		Account a2 = new Account("Account2");
		
		Transfer t1 = new Transfer(a1, a2, 3, "Test1");
		t1.book();

		Transfer t2 = new Transfer(a1, a2, 0, "Test1");
		t2.book();
		
		assertEquals(-3, a1.getBalance());
		assertEquals(3, a2.getBalance());
	}

}
