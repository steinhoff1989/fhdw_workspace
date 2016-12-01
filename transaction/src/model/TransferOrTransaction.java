package model;

import exceptions.AccountException;
import state.NotCompletedState;
import state.TransferState;

public abstract class TransferOrTransaction {
	
	/** Removes the receiver's <amount> from the receiver's <fromAccount> (debit)
	 *  and adds the receiver's <amount> to the receiver's <toAccount> (credit).
	 * @throws AccountException if the booking violates account limits. 
	 */
	public abstract void book() throws AccountException;
	
	public TransferState state;

	public TransferOrTransaction() {
		super();
		state = new NotCompletedState();
	}

}
