package commands;

import editor.Editor;

public abstract class Command {
	
	public Editor editor;
	
	public Command(Editor e) {
		this.editor = e;
	}

	public abstract void execute();
	
	public abstract void undo();

}
