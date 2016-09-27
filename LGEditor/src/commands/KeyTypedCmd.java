package commands;

import editor.Editor;

public class KeyTypedCmd extends Command {

	private Character c;

	private KeyTypedCmd(Character c, Editor e) {
		super(e);
		this.c = c;

	}

	@Override
	public void execute() {
		editor.getText().insert(editor.getPosition(), editor.getShiftMode() ? c : Character.toLowerCase(c));
		editor.setPosition(editor.getPosition() + 1);

	}

	@Override
	public void undo() {
		DeleteLeftCmd dlc = DeleteLeftCmd.create(editor);
		dlc.execute();
		
	}
	
	public static KeyTypedCmd create(Character c, Editor e) {
		return new KeyTypedCmd(c, e);
	}


}
