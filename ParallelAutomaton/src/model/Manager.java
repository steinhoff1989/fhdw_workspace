package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Manager {

	private final List<Process> threads;
	static private Manager instance;
	WrappedOutput output;

	/**
	 * Creates a manager object that manages all active threads
	 */
	private Manager() {
		this.threads = new ArrayList<Process>();
		this.output = new WrappedOutput();
	}

	/**
	 * Returns the unique instance of a manager object that remembers and manage all started threads.
	 * @return
	 */
	static public Manager getTheInstance() {
		if (instance == null) {
			instance = new Manager();
		}
		return instance;
	}

	/**
	 * Adds the configuration to the list of threads that are running
	 * @param configuration
	 */
	synchronized public void newThreadCreated(final Configuration configuration) {
		this.threads.add(configuration);
	}

	/**
	 * Informs the manager, that the given <configuration> is a valid configuration that recognizes the requested input.
	 * It securely stops all running threads and writes the corresponding output of this configuration 
	 *  in the managers output Buffer
	 * @param configuration: The valid configuration for the requested input
	 */
	synchronized public void succeeded(final Configuration configuration) {
		final Iterator<Process> i = this.threads.iterator();
		while (i.hasNext()) {
			final Process current = i.next();
			current.stopThread();
		}
		this.output.setOutput(configuration.getOutput());
		this.notify();
	}

	/**
	 * Informs the manager, that the given <configuration> is not a valid input for the requested input.
	 * The <configuration> will be securely stopped and removed from the list of running threads
	 * @param configuration
	 */
	synchronized public void fail(final Configuration configuration) {
		this.threads.remove(configuration);
		configuration.stopThread();

		if (this.threads.size() == 0) {
			this.output.notRecognized();
			this.notify();
		}
	}

	/**
	 * Returns the wrapped output element. If the output is empty, the caller needs to wait.
	 * @return: Returns one valid output String to the requested input
	 * @throws NotRecognizedException: If there is no valid path for the requested input, an NotRecognizedException will get thrown
	 * @throws InterruptedException
	 */
	synchronized public String getOutput() throws NotRecognizedException, InterruptedException {
		while (this.isEmpty()) {
			this.wait();
		}
		return this.output.getOutput();
	}

	/**
	 * Returns true if, and only if <output> is empty
	 * @return
	 */
	synchronized private boolean isEmpty() {
		return this.output.isEmpty();
	}

	/**
	 * Clears the manager object, so that it will be reinitialized
	 */
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
