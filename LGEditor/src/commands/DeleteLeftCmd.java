package commands;

import editor.Editor;

public class DeleteLeftCmd extends Command{

	private Character c;

	public DeleteLeftCmd(Character c, Editor e) {
		super(e);
		this.c = c;
		
	}

	public static DeleteLeftCmd create(Editor e) {
		
		return new DeleteLeftCmd(e.getText().charAt(e.getPosition()-1),e);
	}
	@Override
	public void execute() {
		StringBuffer text = editor.getText();
		int position = editor.getPosition();
		text.deleteCharAt(position - 1);
		editor.setPosition(position - 1);
		
	}

	@Override
	public void undo() {
		KeyTypedCmd ktc = KeyTypedCmd.create(c, editor);
		ktc.execute();
		
	}


}
