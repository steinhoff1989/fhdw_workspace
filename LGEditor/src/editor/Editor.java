package editor;

import java.util.Stack;

import javax.swing.text.Position;

import commands.Command;
import commands.CutCmd;
import commands.DeleteLeftCmd;
import commands.DeleteRightCmd;
import commands.KeyTypedCmd;
import commands.LeftCmd;
import commands.PasteCmd;
import commands.RightCmd;
import commands.RightMarkedCmd;

public class Editor {
	
	Stack<Command> commandStack;
	Stack<Command> redoStack;

	public static Editor createEditor() {
		return new Editor();
	}

	private StringBuffer text;
	private int firstPosition;
	private int secondPosition;
	private boolean shiftMode;
	private String copiedText;
	
	private Editor (){
		this.commandStack = new Stack<>();
		this.redoStack = new Stack<>();
		this.text = new StringBuffer();
		this.firstPosition = 0;
		this.secondPosition = 0;
		this.shiftMode = false;
		this.copiedText = "";
	}
	public void keyTyped(Character c) {
		
		KeyTypedCmd cmd = KeyTypedCmd.create(c,this);
		commandStack.push(cmd);
		cmd.execute();
	}
	
	public StringBuffer getText() {
		return this.text;
	}
	public int getPosition() {
		return this.getFirstPosition();
	}
	public int getFirstPosition() {
		return this.firstPosition;
	}
	public void setPosition(int position) {
		this.setFirstPosition(position);
		this.setSecondPosition(position);
	}
	public void setFirstPosition(int position) {
		this.firstPosition = position;
	}
	public int getSecondPosition() {
		return this.secondPosition;
	}
	public void setSecondPosition(int position) {
		this.secondPosition = position;
	}
	public void deleteLeft() {
		DeleteLeftCmd cmd = DeleteLeftCmd.create(this);
		commandStack.push(cmd);
		cmd.execute();
			
	}
	public void deleteRight() {
		DeleteRightCmd cmd = DeleteRightCmd.create(this);
		commandStack.push(cmd);
		cmd.execute();	
	}
	public void left() {
		if(this.getShiftMode()){
			
		}
		LeftCmd cmd = LeftCmd.create(this);
		commandStack.push(cmd);
		cmd.execute();
		
	}
	public void right() {
//		if(this.getShiftMode()){
//			RightMarkedCmd cmd = RightMarkedCmd.create(this);
//			commandStack.push(cmd);
//			cmd.execute();
//		}
//		else{
		RightCmd cmd = RightCmd.create(this);
		commandStack.push(cmd);
		cmd.execute();
//		}
	}

	public void newLine() {
		this.getText().insert(this.getPosition(), "\n");
		this.setPosition(this.getPosition() + 1);
	}
	public String getEditorText() {
		return this.getText().toString();
	}
	public void shift() {
		this.setShiftMode(!this.getShiftMode());
	}
	private void setShiftMode(boolean b) {
		this.shiftMode = b;
	}
	public boolean getShiftMode() {
		return this.shiftMode;
	}
	public void undo() {
		Command pop = commandStack.pop();
		pop.undo();
		redoStack.push(pop);
	}
	public void redo() {
		Command var = redoStack.pop();
		commandStack.push(var);
		var.execute();
		
	}
	public void copy() {
		int startMarker = Math.min(firstPosition, secondPosition);
		int endMarker = Math.max(firstPosition, secondPosition);
		
		copiedText = this.getText().substring(startMarker, endMarker);
	}
	public void cut() {
		
		CutCmd cmd = CutCmd.create(this);
		commandStack.push(cmd);
		cmd.execute();

	}
	public void paste() {
		
		PasteCmd cmd = PasteCmd.create(this);
		commandStack.push(cmd);
		cmd.execute();

	}
	public void setCopiedText(String substring) {
		this.copiedText = substring;
		
	}
	public String getCopiedText() {
		return this.copiedText;
	}

}
