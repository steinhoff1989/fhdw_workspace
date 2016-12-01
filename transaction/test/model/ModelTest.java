package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;

import exceptions.AmountUnderLimitException;
import exceptions.TransactionFailedException;
import exceptions.TransferAlreadyBookedException;

public class ModelTest {

	@BeforeClass
	public static void setUp() {

	}

	@Test
	public void TransferNullBuchung() throws AmountUnderLimitException, TransferAlreadyBookedException {
		final Account a1 = new Account("Account1");
		final Account a2 = new Account("Account2");

		final int entries1 = a1.getAccountEntries().size();
		final int entries2 = a2.getAccountEntries().size();

		final Transfer t1 = Transfer.create(a1, a2, 0, "Test1");
		t1.book();

		assertEquals(entries1 + 1, a1.getAccountEntries().size());
		assertEquals(entries2 + 1, a2.getAccountEntries().size());
	}

	@Test
	public void TransNegBuchung() throws AmountUnderLimitException, TransferAlreadyBookedException {
		final Account a1 = new Account("Account1");
		final Account a2 = new Account("Account2");

		final long balance1 = a1.getBalance();
		final long balance2 = a2.getBalance();

		final int entries1 = a1.getAccountEntries().size();
		final int entries2 = a2.getAccountEntries().size();

		final Transfer t1 = Transfer.create(a1, a2, -5, "Test1");
		t1.book();

		assertEquals(balance1 + 5, a1.getBalance());
		assertEquals(balance2 - 5, a2.getBalance());
		assertEquals(entries1 + 1, a1.getAccountEntries().size());
		assertEquals(entries2 + 1, a2.getAccountEntries().size());
	}

	@Test
	public void TransferStandard() throws AmountUnderLimitException, TransferAlreadyBookedException {
		final Account a1 = new Account("Account1");
		final Account a2 = new Account("Account2");

		final long balance1 = a1.getBalance();
		final long balance2 = a2.getBalance();

		final int entries1 = a1.getAccountEntries().size();
		final int entries2 = a2.getAccountEntries().size();

		final Transfer t1 = Transfer.create(a1, a2, 5, "Test1");
		t1.book();

		assertEquals(balance1 - 5, a1.getBalance());
		assertEquals(balance2 + 5, a2.getBalance());
		assertEquals(entries1 + 1, a1.getAccountEntries().size());
		assertEquals(entries2 + 1, a2.getAccountEntries().size());
	}

	@Test
	public void equalDebitAndCreditTransfer() throws AmountUnderLimitException, TransferAlreadyBookedException {
		final Account a1 = new Account("Account1");
		final Account a2 = new Account("Account2");

		final Transfer t1 = Transfer.create(a1, a2, 5, "Test1");
		t1.book();

		final Transfer t2 = Transfer.create(a2, a1, 5, "Test1");
		t2.book();

		assertEquals(0, a1.getBalance());
		assertEquals(0, a2.getBalance());
	}

	@Test
	public void lowerThanZeroBalanceTest() throws AmountUnderLimitException, TransferAlreadyBookedException {
		final Account a1 = new Account("Account1");
		final Account a2 = new Account("Account2");

		final Transfer t1 = Transfer.create(a1, a2, -3, "Test1");
		t1.book();

		final Transfer t2 = Transfer.create(a1, a2, 5, "Test1");
		t2.book();

		assertEquals(-2, a1.getBalance());
	}

	@Test
	public void LowerThanZeroTest() throws AmountUnderLimitException, TransferAlreadyBookedException {
		final Account a1 = new Account("Account1");
		final Account a2 = new Account("Account2");

		final Transfer t1 = Transfer.create(a1, a2, 3, "Test1");
		t1.book();

		final Transfer t2 = Transfer.create(a1, a2, 5, "Test1");
		t2.book();

		assertEquals(-8, a1.getBalance());
		assertEquals(8, a2.getBalance());
	}

	@Test
	public void standardTransfer() throws AmountUnderLimitException, TransferAlreadyBookedException {
		final Account a1 = new Account("Account1");
		final Account a2 = new Account("Account2");

		final Transfer t1 = Transfer.create(a1, a2, -3, "Test1");
		t1.book();

		final Transfer t2 = Transfer.create(a1, a2, -5, "Test1");
		t2.book();

		assertEquals(8, a1.getBalance());
		assertEquals(-8, a2.getBalance());
	}

	@Test
	public void doubleTransferBooking() throws AmountUnderLimitException, TransferAlreadyBookedException {
		final Account a1 = new Account("Account1");
		final Account a2 = new Account("Account2");

		final Transfer t1 = Transfer.create(a1, a2, 3, "Test1");
		t1.book();

		final Transfer t2 = Transfer.create(a1, a2, 0, "Test1");
		t2.book();

		assertEquals(-3, a1.getBalance());
		assertEquals(3, a2.getBalance());
	}

	@Test
	public void transactionFailedTest1() throws AmountUnderLimitException, TransferAlreadyBookedException {
		final Account a1 = new Account("Account1");
		final Account a2 = new Account("Account2");

		final Transfer t1 = Transfer.create(a1, a2, 600, "Test1");
		final Transfer t2 = Transfer.create(a1, a2, 600, "Test2");
		final Transfer t3 = Transfer.create(a2, a1, 400, "Test3");

		final Transaction trans1 = Transaction.create();
		trans1.addTransfer(t1);
		trans1.addTransfer(t2);
		trans1.addTransfer(t3);

		try {
			trans1.book();
			fail();
		} catch (final TransactionFailedException e) {
			assertEquals(0, a1.getBalance());
			assertEquals(0, a2.getBalance());
		}
	}

	@Test
	public void transactionFailedTest2() throws AmountUnderLimitException, TransferAlreadyBookedException {
		final Account a1 = new Account("Account1");
		final Account a2 = new Account("Account2");
		final Account a3 = new Account("Account3");

		final Transfer t1 = Transfer.create(a1, a2, 600, "Test1");
		final Transfer t2 = Transfer.create(a1, a2, 600, "Test1");
		final Transfer t3 = Transfer.create(a2, a1, 400, "Test1");

		final Transaction trans1 = Transaction.create();
		trans1.addTransfer(t1);
		trans1.addTransfer(t2);
		trans1.addTransfer(t3);

		try {
			trans1.book();
			fail();
		} catch (final TransactionFailedException e) {
			assertEquals("NotCompletedState", t1.state.toString());
			assertEquals(0, t1.state.getTries());
			assertEquals(1, t2.state.getTries());
			assertEquals(0, t3.state.getTries());
			assertEquals(2, t1.getFromAccount().getAccountEntries().size());
			assertEquals(0, a1.getBalance());
			assertEquals(0, a2.getBalance());
		}

		final Transfer t4 = Transfer.create(a3, a1, 600, "Test1");
		t4.book();

		try {
			trans1.book();
			assertEquals("SuccessState", t1.state.toString());
		} catch (final TransactionFailedException e) {
			fail();
		}
	}

}
