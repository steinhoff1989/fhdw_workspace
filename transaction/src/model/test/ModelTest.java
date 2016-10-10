package model.test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import model.Account;
import model.AmountUnderLimitException;
import model.Transaction;
import model.TransactionFailedException;
import model.Transfer;
import model.TransferAlreadyBookedException;

public class ModelTest {

	
	@BeforeClass
	public static void setUp(){
		
	}
	
	@Test
	public void TransferNullBuchung() throws AmountUnderLimitException, TransferAlreadyBookedException {
		Account a1 = new Account("Account1");
		Account a2 = new Account("Account2");
		
		int entries1=a1.getAccountEntries().size();
		int entries2=a2.getAccountEntries().size();
		
		
		Transfer t1 = new Transfer(a1, a2, 0, "Test1");
		t1.book();
		
		assertEquals(entries1 +1, a1.getAccountEntries().size());
		assertEquals(entries2 +1, a2.getAccountEntries().size());
	}
	
	@Test
	public void TransNegBuchung() throws AmountUnderLimitException, TransferAlreadyBookedException {
		Account a1 = new Account("Account1");
		Account a2 = new Account("Account2");
		
		long balance1 = a1.getBalance();
		long balance2 = a2.getBalance();
		
		int entries1=a1.getAccountEntries().size();
		int entries2=a2.getAccountEntries().size();
		
		Transfer t1 = new Transfer(a1, a2, -5, "Test1");
		t1.book();
		
		assertEquals(balance1 + 5, a1.getBalance());
		assertEquals(balance2 - 5, a2.getBalance());
		assertEquals(entries1 +1, a1.getAccountEntries().size());
		assertEquals(entries2 +1, a2.getAccountEntries().size());
	}
	
	@Test
	public void TransferStandard() throws AmountUnderLimitException, TransferAlreadyBookedException {
		Account a1 = new Account("Account1");
		Account a2 = new Account("Account2");
		
		long balance1 = a1.getBalance();
		long balance2 = a2.getBalance();
		
		int entries1=a1.getAccountEntries().size();
		int entries2=a2.getAccountEntries().size();
		
		Transfer t1 = new Transfer(a1, a2, 5, "Test1");
		t1.book();
		
		
		assertEquals(balance1 - 5, a1.getBalance());
		assertEquals(balance2 + 5, a2.getBalance());
		assertEquals(entries1 + 1, a1.getAccountEntries().size());
		assertEquals(entries2 + 1, a2.getAccountEntries().size());
	}
	
	@Test
	public void equalDebitAndCreditTransfer() throws AmountUnderLimitException, TransferAlreadyBookedException {
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
	public void lowerThanZeroBalanceTest() throws AmountUnderLimitException, TransferAlreadyBookedException {
		Account a1 = new Account("Account1");
		Account a2 = new Account("Account2");
		
		Transfer t1 = new Transfer(a1, a2, -3, "Test1");
		t1.book();

		Transfer t2 = new Transfer(a1, a2, 5, "Test1");
		t2.book();
		
		assertEquals(-2, a1.getBalance());
	}
	
	@Test
	public void LowerThanZeroTest() throws AmountUnderLimitException, TransferAlreadyBookedException {
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
	public void standardTransfer() throws AmountUnderLimitException, TransferAlreadyBookedException {
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
	public void doubleTransferBooking() throws AmountUnderLimitException, TransferAlreadyBookedException {
		Account a1 = new Account("Account1");
		Account a2 = new Account("Account2");
		
		Transfer t1 = new Transfer(a1, a2, 3, "Test1");
		t1.book();

		Transfer t2 = new Transfer(a1, a2, 0, "Test1");
		t2.book();
		
		assertEquals(-3, a1.getBalance());
		assertEquals(3, a2.getBalance());
	}
	
	@Test
	public void transactionFailedTest1() throws AmountUnderLimitException, TransferAlreadyBookedException {
		Account a1 = new Account("Account1");
		Account a2 = new Account("Account2");
		
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
		}
	}

}
