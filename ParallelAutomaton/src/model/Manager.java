package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Manager {

	private final List<Process> threads;
	static private Manager instance;
	WrappedOutput output;

	private Manager() {
		this.threads = new ArrayList<Process>();
		this.output = new WrappedOutput();
	}

	static public Manager getTheInstance() {
		if (instance == null) {
			instance = new Manager();
		}
		return instance;
	}

	synchronized public void newThreadCreated(final Configuration configuration) {
		this.threads.add(configuration);
	}

	synchronized public void succeeded(final Configuration configuration) {
		final Iterator<Process> i = this.threads.iterator();
		while (i.hasNext()) {
			final Process current = i.next();
			current.stopThread();
		}
		this.output.setOutput(configuration.getOutput());
		this.notify();
	}

	synchronized public void fail(final Configuration configuration) {
		this.threads.remove(configuration);
		configuration.stopThread();

		//assert this.threads.size() >= 0;
		if (this.threads.size() == 0) {
			this.output.notRecognized();
			this.notify();
		}
	}

	synchronized public String getOutput() throws NotRecognizedException, InterruptedException {
		while (this.isEmpty()) {
			this.wait();
		}
		return this.output.getOutput();
	}

	synchronized private boolean isEmpty() {
		return this.output.isEmpty();
	}

	public Configuration doSomething() {
		// TODO
		throw new RuntimeException("not implemented");
	}
	
	synchronized public void clearManager(){
		final Iterator<Process> i = this.threads.iterator();
		while (i.hasNext()) {
			final Process current = i.next();
			current.stopThread();
			i.remove();
		}
		this.output = new WrappedOutput();
	}
}
