package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import viewModel.AccountObserver;

public abstract class AccountObservee {

	protected List<AccountObserver> observers;
	
	public AccountObservee() {
		super();
		this.observers = new ArrayList<AccountObserver>();
	}

	protected void notifyObservers() {
		final Iterator<AccountObserver> currentObservers = this.observers.iterator();
		while (currentObservers.hasNext())
			currentObservers.next().update();
	}
}
