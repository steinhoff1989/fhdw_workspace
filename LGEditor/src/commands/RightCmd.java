package commands;

import editor.Editor;

public class RightCmd extends Command {

	public RightCmd(Editor e) {
		super(e);
	}

	@Override
	public void execute() {
		if (editor.getPosition() < editor.getText().length()) {
			if (editor.getShiftMode()){
				editor.setFirstPosition(editor.getFirstPosition() + 1);
			}else{
				editor.setPosition(editor.getPosition() + 1);
			}
		}

	}

	@Override
	public void undo() {
		LeftCmd lcmd = LeftCmd.create(editor);
		lcmd.execute();
	}

	public static RightCmd create(Editor e) {
		return new RightCmd(e);
	}

}
