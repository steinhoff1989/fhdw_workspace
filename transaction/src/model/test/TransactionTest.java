package model.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Account;
import model.AmountUnderLimitException;
import model.Transaction;
import model.TransactionFailedException;
import model.Transfer;
import model.TransferAlreadyBookedException;

public class TransactionTest {

	private Account a1;
	private Account a2;
	private Account a3;

	@Before
	public void setUp() {
		a1 = new Account("Account1");
		a2 = new Account("Account2");
		a3 = new Account("Account3");
	}
	
	@Test
	public void transactionUnderLimitTest1() throws AmountUnderLimitException, TransferAlreadyBookedException {
		Transfer t1 = new Transfer(a1, a2, 600, "Test1");
		Transfer t2 = new Transfer(a1, a2, 600, "Test2");
		Transfer t3 = new Transfer(a2, a1, 400, "Test3");

		Transaction trans1 = Transaction.create();
		trans1.addTransfer(t1);
		trans1.addTransfer(t2);
		trans1.addTransfer(t3);

		try {
			trans1.book();
			fail();
		} catch (TransactionFailedException e) {
			assertEquals(0, a1.getBalance());
			assertEquals(0, a2.getBalance());
			assertEquals(0, t1.state.getTries());
			assertEquals(1, t2.state.getTries());
			assertEquals(0, t3.state.getTries());
			assertEquals("NotCompletedState", t1.state.toString());
			assertEquals("NotCompletedState", t2.state.toString());
			assertEquals("NotCompletedState", t3.state.toString());
			assertEquals(2, a1.getAccountEntries().size());
			assertEquals(2, a2.getAccountEntries().size());
		}
	}
	
	@Test
	public void transactionRunMultipleTest1() throws AmountUnderLimitException, TransferAlreadyBookedException {
		Transfer t1 = new Transfer(a1, a2, 600, "Test1");
		Transfer t2 = new Transfer(a1, a2, 600, "Test1");
		Transfer t3 = new Transfer(a2, a1, 400, "Test1");

		Transaction trans1 = Transaction.create();
		trans1.addTransfer(t1);
		trans1.addTransfer(t2);
		trans1.addTransfer(t3);

		try {
			trans1.book();
			fail();
		} catch (TransactionFailedException e) {
			assertEquals(0, a1.getBalance());
			assertEquals(0, a2.getBalance());
			assertEquals(0, t1.state.getTries());
			assertEquals(1, t2.state.getTries());
			assertEquals(0, t3.state.getTries());
			assertEquals("NotCompletedState", t1.state.toString());
			assertEquals("NotCompletedState", t2.state.toString());
			assertEquals("NotCompletedState", t3.state.toString());
			assertEquals(2, a1.getAccountEntries().size());
			assertEquals(2, a2.getAccountEntries().size());
		}

		Transfer t4 = new Transfer(a3, a1, 600, "Test1");
		t4.book();

		assertEquals(600, a1.getBalance());
		
		try {
			trans1.book();
			assertEquals(-200, a1.getBalance());
			assertEquals(800, a2.getBalance());
			assertEquals(0, t1.state.getTries());
			assertEquals(1, t2.state.getTries());
			assertEquals(0, t3.state.getTries());
			assertEquals("SuccessState", t1.state.toString());
			assertEquals("SuccessState", t2.state.toString());
			assertEquals("SuccessState", t3.state.toString());
			assertEquals(6, a1.getAccountEntries().size());
			assertEquals(5, a2.getAccountEntries().size());
		} catch (TransactionFailedException e) {
			fail();
		}
	}

	@Test
	public void transactionRunMultipleTest2() throws AmountUnderLimitException, TransferAlreadyBookedException {
		Transfer t1 = new Transfer(a1, a2, 600, "Test1");
		Transfer t2 = new Transfer(a1, a2, 600, "Test1");
		Transfer t3 = new Transfer(a2, a1, 400, "Test1");
		
		Transaction trans1 = Transaction.create();
		trans1.addTransfer(t1);
		trans1.addTransfer(t2);
		trans1.addTransfer(t3);
		
		try {
			trans1.book();
			fail();
		} catch (TransactionFailedException e) {
			assertEquals(0, a1.getBalance());
			assertEquals(0, a2.getBalance());
			assertEquals(0, t1.state.getTries());
			assertEquals(1, t2.state.getTries());
			assertEquals(0, t3.state.getTries());
			assertEquals("NotCompletedState", t1.state.toString());
			assertEquals("NotCompletedState", t2.state.toString());
			assertEquals("NotCompletedState", t3.state.toString());
			assertEquals(2, a1.getAccountEntries().size());
			assertEquals(2, a2.getAccountEntries().size());
		}
		
		Transfer t4 = new Transfer(a3, a1, 199, "Test1");
		t4.book();
		
		assertEquals(199, a1.getBalance());
		
		try {
			trans1.book();
			fail();
		} catch (TransactionFailedException e) {
			assertEquals(199, a1.getBalance());
			assertEquals(0, a2.getBalance());
			assertEquals(-199, a3.getBalance());
			assertEquals(0, t1.state.getTries());
			assertEquals(2, t2.state.getTries());
			assertEquals(0, t3.state.getTries());
			assertEquals("NotCompletedState", t1.state.toString());
			assertEquals("NotCompletedState", t2.state.toString());
			assertEquals("NotCompletedState", t3.state.toString());
			assertEquals(5, a1.getAccountEntries().size());
			assertEquals(4, a2.getAccountEntries().size());
		}

		Transfer t5 = new Transfer(a3, a1, 1, "Test1");
		t5.book();
		
		assertEquals(200, a1.getBalance());
		
		
		try {
			trans1.book();
			assertEquals(-600, a1.getBalance());
			assertEquals(800, a2.getBalance());
			assertEquals(-200, a3.getBalance());
			assertEquals(0, t1.state.getTries());
			assertEquals(2, t2.state.getTries());
			assertEquals(0, t3.state.getTries());
			assertEquals("SuccessState", t1.state.toString());
			assertEquals("SuccessState", t2.state.toString());
			assertEquals("SuccessState", t3.state.toString());
			assertEquals(9, a1.getAccountEntries().size());
			assertEquals(7, a2.getAccountEntries().size());
		} catch (TransactionFailedException e) {
			fail();
		}
	}

}
