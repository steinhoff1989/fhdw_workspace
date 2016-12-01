package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import exceptions.AmountUnderLimitException;
import exceptions.TransferAlreadyBookedException;

public class TransferTest {

	private Account a1;
	private Account a2;

	@Before
	public void setUp() {
		this.a1 = new Account("Account1");
		this.a2 = new Account("Account2");
	}
	
	@Test
	public void transfer0Test1() throws AmountUnderLimitException, TransferAlreadyBookedException {
		final long balance1 = this.a1.getBalance();
		final long balance2 = this.a2.getBalance();

		final int entries1 = this.a1.getAccountEntries().size();
		final int entries2 = this.a2.getAccountEntries().size();

		final Transfer t1 = Transfer.create(this.a1, this.a2, 0, "Test1");
		t1.book();

		assertEquals(balance1, this.a1.getBalance());
		assertEquals(balance2, this.a2.getBalance());
		assertEquals(entries1 + 1, this.a1.getAccountEntries().size());
		assertEquals(entries2 + 1, this.a2.getAccountEntries().size());
	}

	@Test
	public void transfer0Test2() throws AmountUnderLimitException, TransferAlreadyBookedException {
		final long balance1 = this.a1.getBalance();
		final long balance2 = this.a2.getBalance();
		
		final int entries1 = this.a1.getAccountEntries().size();
		final int entries2 = this.a2.getAccountEntries().size();
		
		final Transfer t1 = Transfer.create(this.a1, this.a2, 3, "Test1");
		t1.book();

		final Transfer t2 = Transfer.create(this.a1, this.a2, 0, "Test1");
		t2.book();

		assertEquals(balance1 - 3, this.a1.getBalance());
		assertEquals(balance2 + 3, this.a2.getBalance());
		assertEquals(entries1 + 2, this.a1.getAccountEntries().size());
		assertEquals(entries2 + 2, this.a2.getAccountEntries().size());
	}

	
	@Test
	public void transferStrandardTest1() throws AmountUnderLimitException, TransferAlreadyBookedException {
		final long balance1 = this.a1.getBalance();
		final long balance2 = this.a2.getBalance();

		final int entries1 = this.a1.getAccountEntries().size();
		final int entries2 = this.a2.getAccountEntries().size();

		final Transfer t1 = Transfer.create(this.a1, this.a2, 5, "Test1");
		t1.book();

		assertEquals(balance1 - 5, this.a1.getBalance());
		assertEquals(balance2 + 5, this.a2.getBalance());
		assertEquals(entries1 + 1, this.a1.getAccountEntries().size());
		assertEquals(entries2 + 1, this.a2.getAccountEntries().size());
	}

	@Test
	public void transferStrandardTest2() throws AmountUnderLimitException, TransferAlreadyBookedException {
		final long balance1 = this.a1.getBalance();
		final long balance2 = this.a2.getBalance();
		
		final int entries1 = this.a1.getAccountEntries().size();
		final int entries2 = this.a2.getAccountEntries().size();
		
		final Transfer t1 = Transfer.create(this.a1, this.a2, 3, "Test1");
		t1.book();
		final Transfer t2 = Transfer.create(this.a1, this.a2, 5, "Test1");
		t2.book();
		
		assertEquals(balance1 - 8, this.a1.getBalance());
		assertEquals(balance2 + 8, this.a2.getBalance());
		assertEquals(entries1 + 2, this.a1.getAccountEntries().size());
		assertEquals(entries2 + 2, this.a2.getAccountEntries().size());
	}

	@Test
	public void transferDoubleBookTest1() throws AmountUnderLimitException {
		final long balance1 = this.a1.getBalance();
		final long balance2 = this.a2.getBalance();
		
		final int entries1 = this.a1.getAccountEntries().size();
		final int entries2 = this.a2.getAccountEntries().size();
		
		final Transfer t1 = Transfer.create(this.a1, this.a2, 3, "Test1");
		try {
			t1.book();
		} catch (final TransferAlreadyBookedException e) {
			fail();
		}
		try {
			t1.book();
			fail();
		} catch (final TransferAlreadyBookedException e) {
		}
		
		assertEquals(balance1 - 3, this.a1.getBalance());
		assertEquals(balance2 + 3, this.a2.getBalance());
		assertEquals(entries1 + 1, this.a1.getAccountEntries().size());
		assertEquals(entries2 + 1, this.a2.getAccountEntries().size());
	}

	@Test
	public void transferEqualDebitAndCreditTest1() throws AmountUnderLimitException, TransferAlreadyBookedException {
		final long balance1 = this.a1.getBalance();
		final long balance2 = this.a2.getBalance();

		final int entries1 = this.a1.getAccountEntries().size();
		final int entries2 = this.a2.getAccountEntries().size();

		final Transfer t1 = Transfer.create(this.a1, this.a2, 5, "Test1");
		t1.book();

		final Transfer t2 = Transfer.create(this.a2, this.a1, 5, "Test1");
		t2.book();

		assertEquals(balance1, this.a1.getBalance());
		assertEquals(balance2, this.a2.getBalance());
		assertEquals(entries1 + 2, this.a1.getAccountEntries().size());
		assertEquals(entries2 + 2, this.a2.getAccountEntries().size());
	}

	@Test
	public void transferPositiveDebitTest1() throws AmountUnderLimitException, TransferAlreadyBookedException {
		final long balance1 = this.a1.getBalance();

		final int entries1 = this.a1.getAccountEntries().size();

		final Transfer t1 = Transfer.create(this.a1, this.a2, -3, "Test1");
		t1.book();

		final Transfer t2 = Transfer.create(this.a1, this.a2, 5, "Test1");
		t2.book();

		assertEquals(balance1 - 2, this.a1.getBalance());
		assertEquals(entries1 + 2, this.a1.getAccountEntries().size());
	}
	
	@Test
	public void transferPositiveDebitTest2() throws AmountUnderLimitException, TransferAlreadyBookedException {
		final long balance1 = this.a1.getBalance();
		final long balance2 = this.a2.getBalance();
		final int entries1 = this.a1.getAccountEntries().size();
		final int entries2 = this.a2.getAccountEntries().size();
		
		final Transfer t1 = Transfer.create(this.a1, this.a2, -3, "Test1");
		t1.book();

		final Transfer t2 = Transfer.create(this.a1, this.a2, -5, "Test1");
		t2.book();

		assertEquals(balance1 + 8, this.a1.getBalance());
		assertEquals(balance2 - 8, this.a2.getBalance());
		assertEquals(entries1 + 2, this.a1.getAccountEntries().size());
		assertEquals(entries2 + 2, this.a2.getAccountEntries().size());
	}

	@Test
	public void transferPositiveDebitTest3() throws AmountUnderLimitException, TransferAlreadyBookedException {
		final long balance1 = this.a1.getBalance();
		final long balance2 = this.a2.getBalance();
		
		final int entries1 = this.a1.getAccountEntries().size();
		final int entries2 = this.a2.getAccountEntries().size();
		
		final Transfer t1 = Transfer.create(this.a1, this.a2, -5, "Test1");
		t1.book();
		
		assertEquals(balance1 + 5, this.a1.getBalance());
		assertEquals(balance2 - 5, this.a2.getBalance());
		assertEquals(entries1 + 1, this.a1.getAccountEntries().size());
		assertEquals(entries2 + 1, this.a2.getAccountEntries().size());
	}


}
