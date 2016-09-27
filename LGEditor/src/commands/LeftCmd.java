package commands;

import editor.Editor;

public class LeftCmd extends Command {

	public LeftCmd(Editor e) {
		super(e);

	}

	public static LeftCmd create(Editor e) {
		return new LeftCmd(e);
	}

	@Override
	public void execute() {
		if (editor.getPosition() > 0) {
			if (editor.getShiftMode()) {
				editor.setFirstPosition(editor.getFirstPosition() - 1);
			} else {
				editor.setPosition(editor.getPosition() - 1);
			}
		}
	}

	@Override
	public void undo() {
		RightCmd rcmd = RightCmd.create(editor);
		rcmd.execute();

	}

}
