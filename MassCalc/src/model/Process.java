package model;

import BufferAndLock.Buffer;
import BufferAndLock.Buffer.StoppException;
import exceptions.DivideByZeroException;
import exceptions.NoMoreVariablesAvailableException;

public abstract class Process{

	/**
	 * The final capacity of all buffer that will be used for this process.
	 */
	protected final static int INTERNAL_BUFFER_SIZE = 1000;
	
	private final Buffer<Integer> streamOne;
	private final Buffer<Integer> streamTwo;
	private Buffer<Integer> streamResult;
	private final Thread thread;

	/**
	 * Creates a process object that takes <streamOne> and <streamTwo> as an input stream for calculation.
	 * @param streamOne: represents the first input buffer.
	 * @param streamTwo: represents the second input buffer.
	 */
	public Process(Buffer<Integer> streamOne, Buffer<Integer> streamTwo) {
		super();
		this.streamOne = streamOne;
		this.streamTwo = streamTwo;
		this.streamResult = new Buffer<Integer>(INTERNAL_BUFFER_SIZE);
		this.thread = new Thread(new Runnable() {

			@Override
			public void run() {
				boolean running = true;
				while (running) {
					try {
						Process.this.calculate();
					} catch (StoppException e) {
						running = false;
						Process.this.streamResult.stopp();
					} catch (DivideByZeroException e) {
						Process.this.streamResult.dividedByZero();
					}
			}
			}
		});
	}
	
	/**
	 * Starts a object bound thread that does calculations
	 */
	protected void startThread() {
		this.thread.start();
	}

	/**
	 * Returns the calculation result of this process 
	 * @throws NoMoreVariablesAvailableException: If called on a variable, there is a limited number of times this variable can be used.
	 * 			If it is used more times as the variable is available the exception <NoMoreVariablesAvailableException> will be thrown.
	 */
	public abstract Buffer<Integer> getResults() throws NoMoreVariablesAvailableException;
	
	/**
	 * Calculates the result of the processes input and saves the result.
	 * @throws DivideByZeroException: If used in a dividing process and the second input value is zero, a <DivideByZeroException> gets thrown.
	 * @throws StoppException: If on input has no more entries to process, a <StoppException> will be thrown.
	 */
	public abstract void calculate() throws DivideByZeroException, StoppException;

	public Buffer<Integer> getStreamOne() {
		return streamOne;
	}
	
	public Buffer<Integer> getStreamTwo() {
		return streamTwo;
	}

	protected Buffer<Integer> getStreamResult() {
		return this.streamResult;
	}
	
	public void setStreamResult(Buffer<Integer> streamResult) {
		this.streamResult = streamResult;
	}
}
