package model.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.Account;
import model.AmountUnderLimitException;
import model.Transfer;
import model.TransferAlreadyBookedException;

public class TransferTest {

	private Account a1;
	private Account a2;

	@Before
	public void setUp() {
		a1 = new Account("Account1");
		a2 = new Account("Account2");
	}

	@Test
	public void transfer0Test1() throws AmountUnderLimitException, TransferAlreadyBookedException {
		long balance1 = a1.getBalance();
		long balance2 = a2.getBalance();

		int entries1 = a1.getAccountEntries().size();
		int entries2 = a2.getAccountEntries().size();

		Transfer t1 = new Transfer(a1, a2, 0, "Test1");
		t1.book();

		assertEquals(balance1, a1.getBalance());
		assertEquals(balance2, a2.getBalance());
		assertEquals(entries1 + 1, a1.getAccountEntries().size());
		assertEquals(entries2 + 1, a2.getAccountEntries().size());
	}

	@Test
	public void transfer0Test2() throws AmountUnderLimitException, TransferAlreadyBookedException {
		long balance1 = a1.getBalance();
		long balance2 = a2.getBalance();
		
		int entries1 = a1.getAccountEntries().size();
		int entries2 = a2.getAccountEntries().size();
		
		Transfer t1 = new Transfer(a1, a2, 3, "Test1");
		t1.book();

		Transfer t2 = new Transfer(a1, a2, 0, "Test1");
		t2.book();

		assertEquals(balance1 - 3, a1.getBalance());
		assertEquals(balance2 + 3, a2.getBalance());
		assertEquals(entries1 + 2, a1.getAccountEntries().size());
		assertEquals(entries2 + 2, a2.getAccountEntries().size());
	}

	
	@Test
	public void transferStrandardTest1() throws AmountUnderLimitException, TransferAlreadyBookedException {
		long balance1 = a1.getBalance();
		long balance2 = a2.getBalance();

		int entries1 = a1.getAccountEntries().size();
		int entries2 = a2.getAccountEntries().size();

		Transfer t1 = new Transfer(a1, a2, 5, "Test1");
		t1.book();

		assertEquals(balance1 - 5, a1.getBalance());
		assertEquals(balance2 + 5, a2.getBalance());
		assertEquals(entries1 + 1, a1.getAccountEntries().size());
		assertEquals(entries2 + 1, a2.getAccountEntries().size());
	}

	@Test
	public void transferStrandardTest2() throws AmountUnderLimitException, TransferAlreadyBookedException {
		long balance1 = a1.getBalance();
		long balance2 = a2.getBalance();
		
		int entries1 = a1.getAccountEntries().size();
		int entries2 = a2.getAccountEntries().size();
		
		Transfer t1 = new Transfer(a1, a2, 3, "Test1");
		t1.book();
		Transfer t2 = new Transfer(a1, a2, 5, "Test1");
		t2.book();
		
		assertEquals(balance1 - 8, a1.getBalance());
		assertEquals(balance2 + 8, a2.getBalance());
		assertEquals(entries1 + 2, a1.getAccountEntries().size());
		assertEquals(entries2 + 2, a2.getAccountEntries().size());
	}

	@Test
	public void transferDoubleBookTest1() throws AmountUnderLimitException {
		long balance1 = a1.getBalance();
		long balance2 = a2.getBalance();
		
		int entries1 = a1.getAccountEntries().size();
		int entries2 = a2.getAccountEntries().size();
		
		Transfer t1 = new Transfer(a1, a2, 3, "Test1");
		try {
			t1.book();
		} catch (TransferAlreadyBookedException e) {
			fail();
		}
		try {
			t1.book();
			fail();
		} catch (TransferAlreadyBookedException e) {
		}
		
		assertEquals(balance1 - 3, a1.getBalance());
		assertEquals(balance2 + 3, a2.getBalance());
		assertEquals(entries1 + 1, a1.getAccountEntries().size());
		assertEquals(entries2 + 1, a2.getAccountEntries().size());
	}

	@Test
	public void transferEqualDebitAndCreditTest1() throws AmountUnderLimitException, TransferAlreadyBookedException {
		long balance1 = a1.getBalance();
		long balance2 = a2.getBalance();

		int entries1 = a1.getAccountEntries().size();
		int entries2 = a2.getAccountEntries().size();

		Transfer t1 = new Transfer(a1, a2, 5, "Test1");
		t1.book();

		Transfer t2 = new Transfer(a2, a1, 5, "Test1");
		t2.book();

		assertEquals(balance1, a1.getBalance());
		assertEquals(balance2, a2.getBalance());
		assertEquals(entries1 + 2, a1.getAccountEntries().size());
		assertEquals(entries2 + 2, a2.getAccountEntries().size());
	}

	@Test
	public void transferPositiveDebitTest1() throws AmountUnderLimitException, TransferAlreadyBookedException {
		long balance1 = a1.getBalance();

		int entries1 = a1.getAccountEntries().size();

		Transfer t1 = new Transfer(a1, a2, -3, "Test1");
		t1.book();

		Transfer t2 = new Transfer(a1, a2, 5, "Test1");
		t2.book();

		assertEquals(balance1 - 2, a1.getBalance());
		assertEquals(entries1 + 2, a1.getAccountEntries().size());
	}
	
	@Test
	public void transferPositiveDebitTest2() throws AmountUnderLimitException, TransferAlreadyBookedException {
		long balance1 = a1.getBalance();
		long balance2 = a2.getBalance();
		int entries1 = a1.getAccountEntries().size();
		int entries2 = a2.getAccountEntries().size();
		
		Transfer t1 = new Transfer(a1, a2, -3, "Test1");
		t1.book();

		Transfer t2 = new Transfer(a1, a2, -5, "Test1");
		t2.book();

		assertEquals(balance1 + 8, a1.getBalance());
		assertEquals(balance2 - 8, a2.getBalance());
		assertEquals(entries1 + 2, a1.getAccountEntries().size());
		assertEquals(entries2 + 2, a2.getAccountEntries().size());
	}

	@Test
	public void transferPositiveDebitTest3() throws AmountUnderLimitException, TransferAlreadyBookedException {
		long balance1 = a1.getBalance();
		long balance2 = a2.getBalance();
		
		int entries1 = a1.getAccountEntries().size();
		int entries2 = a2.getAccountEntries().size();
		
		Transfer t1 = new Transfer(a1, a2, -5, "Test1");
		t1.book();
		
		assertEquals(balance1 + 5, a1.getBalance());
		assertEquals(balance2 - 5, a2.getBalance());
		assertEquals(entries1 + 1, a1.getAccountEntries().size());
		assertEquals(entries2 + 1, a2.getAccountEntries().size());
	}


}
