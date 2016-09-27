package commands;

import editor.Editor;

public class CutCmd extends Command {

	public CutCmd(Editor e) {
		super(e);
	}

	public static CutCmd create(Editor editor) {
		return new CutCmd(editor);
	}

	@Override
	public void execute() {
		int startMarker = Math.min(editor.getFirstPosition(),editor.getSecondPosition());
		int endMarker = Math.max(editor.getFirstPosition(), editor.getSecondPosition());
		
		editor.setCopiedText(editor.getText().substring(startMarker, endMarker));
		
		editor.getText().delete(startMarker, endMarker);
		
		if(editor.getFirstPosition()>=editor.getSecondPosition()){
			editor.setPosition(editor.getPosition() - (endMarker-startMarker));
		}
		editor.setSecondPosition(editor.getPosition());
		editor.setFirstPosition(editor.getPosition());
		
	}

	@Override
	public void undo() {
		StringBuffer text = editor.getText();
		int position = editor.getPosition();
		text.insert(position, editor.getCopiedText());
		
	}

}
