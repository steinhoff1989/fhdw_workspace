package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import exceptions.AmountUnderLimitException;
import exceptions.TransactionFailedException;
import exceptions.TransferAlreadyBookedException;

public class TransactionTest {

	private Account a1;
	private Account a2;
	private Account a3;

	@Before
	public void setUp() {
		this.a1 = new Account("Account1");
		this.a2 = new Account("Account2");
		this.a3 = new Account("Account3");
	}
	
	@Test
	public void transactionUnderLimitTest1() throws AmountUnderLimitException, TransferAlreadyBookedException {
		final Transfer t1 = Transfer.create(this.a1, this.a2, 600, "Test1");
		final Transfer t2 = Transfer.create(this.a1, this.a2, 600, "Test2");
		final Transfer t3 = Transfer.create(this.a2, this.a1, 400, "Test3");

		final Transaction trans1 = Transaction.create();
		trans1.addTransfer(t1);
		trans1.addTransfer(t2);
		trans1.addTransfer(t3);

		try {
			trans1.book();
			fail();
		} catch (final TransactionFailedException e) {
			assertEquals(0, this.a1.getBalance());
			assertEquals(0, this.a2.getBalance());
			assertEquals(0, t1.state.getTries());
			assertEquals(1, t2.state.getTries());
			assertEquals(0, t3.state.getTries());
			assertEquals("NotCompletedState", t1.state.toString());
			assertEquals("NotCompletedState", t2.state.toString());
			assertEquals("NotCompletedState", t3.state.toString());
			assertEquals(2, this.a1.getAccountEntries().size());
			assertEquals(2, this.a2.getAccountEntries().size());
		}
	}
	
	@Test
	public void transactionRunMultipleTest1() throws AmountUnderLimitException, TransferAlreadyBookedException {
		final Transfer t1 = Transfer.create(this.a1, this.a2, 600, "Test1");
		final Transfer t2 = Transfer.create(this.a1, this.a2, 600, "Test1");
		final Transfer t3 = Transfer.create(this.a2, this.a1, 400, "Test1");

		final Transaction trans1 = Transaction.create();
		trans1.addTransfer(t1);
		trans1.addTransfer(t2);
		trans1.addTransfer(t3);

		try {
			trans1.book();
			fail();
		} catch (final TransactionFailedException e) {
			assertEquals(0, this.a1.getBalance());
			assertEquals(0, this.a2.getBalance());
			assertEquals(0, t1.state.getTries());
			assertEquals(1, t2.state.getTries());
			assertEquals(0, t3.state.getTries());
			assertEquals("NotCompletedState", t1.state.toString());
			assertEquals("NotCompletedState", t2.state.toString());
			assertEquals("NotCompletedState", t3.state.toString());
			assertEquals(2, this.a1.getAccountEntries().size());
			assertEquals(2, this.a2.getAccountEntries().size());
		}

		final Transfer t4 = Transfer.create(this.a3, this.a1, 600, "Test1");
		t4.book();

		assertEquals(600, this.a1.getBalance());
		
		try {
			trans1.book();
			assertEquals(-200, this.a1.getBalance());
			assertEquals(800, this.a2.getBalance());
			assertEquals(0, t1.state.getTries());
			assertEquals(1, t2.state.getTries());
			assertEquals(0, t3.state.getTries());
			assertEquals("SuccessState", t1.state.toString());
			assertEquals("SuccessState", t2.state.toString());
			assertEquals("SuccessState", t3.state.toString());
			assertEquals(6, this.a1.getAccountEntries().size());
			assertEquals(5, this.a2.getAccountEntries().size());
		} catch (final TransactionFailedException e) {
			fail();
		}
	}

	@Test
	public void transactionRunMultipleTest2() throws AmountUnderLimitException, TransferAlreadyBookedException {
		final Transfer t1 = Transfer.create(this.a1, this.a2, 600, "Test1");
		final Transfer t2 = Transfer.create(this.a1, this.a2, 600, "Test1");
		final Transfer t3 = Transfer.create(this.a2, this.a1, 400, "Test1");
		
		final Transaction trans1 = Transaction.create();
		trans1.addTransfer(t1);
		trans1.addTransfer(t2);
		trans1.addTransfer(t3);
		
		try {
			trans1.book();
			fail();
		} catch (final TransactionFailedException e) {
			assertEquals(0, this.a1.getBalance());
			assertEquals(0, this.a2.getBalance());
			assertEquals(0, t1.state.getTries());
			assertEquals(1, t2.state.getTries());
			assertEquals(0, t3.state.getTries());
			assertEquals("NotCompletedState", t1.state.toString());
			assertEquals("NotCompletedState", t2.state.toString());
			assertEquals("NotCompletedState", t3.state.toString());
			assertEquals(2, this.a1.getAccountEntries().size());
			assertEquals(2, this.a2.getAccountEntries().size());
		}
		
		final Transfer t4 = Transfer.create(this.a3, this.a1, 199, "Test1");
		t4.book();
		
		assertEquals(199, this.a1.getBalance());
		
		try {
			trans1.book();
			fail();
		} catch (final TransactionFailedException e) {
			assertEquals(199, this.a1.getBalance());
			assertEquals(0, this.a2.getBalance());
			assertEquals(-199, this.a3.getBalance());
			assertEquals(0, t1.state.getTries());
			assertEquals(2, t2.state.getTries());
			assertEquals(0, t3.state.getTries());
			assertEquals("NotCompletedState", t1.state.toString());
			assertEquals("NotCompletedState", t2.state.toString());
			assertEquals("NotCompletedState", t3.state.toString());
			assertEquals(5, this.a1.getAccountEntries().size());
			assertEquals(4, this.a2.getAccountEntries().size());
		}

		final Transfer t5 = Transfer.create(this.a3, this.a1, 1, "Test1");
		t5.book();
		
		assertEquals(200, this.a1.getBalance());
		
		
		try {
			trans1.book();
			assertEquals(-600, this.a1.getBalance());
			assertEquals(800, this.a2.getBalance());
			assertEquals(-200, this.a3.getBalance());
			assertEquals(0, t1.state.getTries());
			assertEquals(2, t2.state.getTries());
			assertEquals(0, t3.state.getTries());
			assertEquals("SuccessState", t1.state.toString());
			assertEquals("SuccessState", t2.state.toString());
			assertEquals("SuccessState", t3.state.toString());
			assertEquals(9, this.a1.getAccountEntries().size());
			assertEquals(7, this.a2.getAccountEntries().size());
		} catch (final TransactionFailedException e) {
			fail();
		}
	}

}
