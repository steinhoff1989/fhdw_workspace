package model;

public interface Command {

	public void execute();
	public Object getResult() throws Throwable;
}
