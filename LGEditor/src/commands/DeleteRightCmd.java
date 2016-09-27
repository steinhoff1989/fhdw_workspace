package commands;

import editor.Editor;

public class DeleteRightCmd extends Command{

	private Character c;
	
	public DeleteRightCmd(Character c, Editor e) {
		super(e);
		this.c = c;
		
	}
	
	public static DeleteRightCmd create(Editor e) {

		return new DeleteRightCmd(e.getText().charAt(e.getPosition()),e);
	}

	@Override
	public void execute() {
		StringBuffer text = editor.getText();
		int position = editor.getPosition();
		text.deleteCharAt(position);
		
	}

	@Override
	public void undo() {
		StringBuffer text = editor.getText();
		int position = editor.getPosition();
		text.insert(position, this.c);		
	}

	
	
	

}
