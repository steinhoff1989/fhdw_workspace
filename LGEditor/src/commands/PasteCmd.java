package commands;

import editor.Editor;

public class PasteCmd extends Command{

	public PasteCmd(Editor e) {
		super(e);
	}

	public static PasteCmd create(Editor editor) {

		return new PasteCmd(editor);
	}

	@Override
	public void execute() {
		editor.getText().insert(editor.getPosition(), editor.getCopiedText());
		editor.setPosition(editor.getPosition() + editor.getCopiedText().length());		
	}

	@Override
	public void undo() {
		StringBuffer text = editor.getText();
		int position = editor.getPosition();
		editor.setPosition(position - editor.getCopiedText().length());
		text.delete(position - editor.getCopiedText().length(),position);
		
	}

}
