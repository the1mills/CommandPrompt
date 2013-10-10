package command;

import java.util.List;

import javax.swing.text.AbstractDocument;

import views.CommandWindow;

import commands.Interfaces.ICommand;

public class Command_CLEAR implements ICommand {
	
	private CommandWindow cw;

	public Command_CLEAR(CommandWindow cw) {
		
		this.cw = cw;
	}

	@Override
	public String execute(List<String> command) {
		
		if(command.size() > 1){
		
			return "Clear command may have been attempted, but there were too many parameters.";
		}
			
			((AbstractDocument) cw.getJta().getDocument())
				.setDocumentFilter(null);
			cw.getJta().setText("");
			cw.addNewDocFilter();
			cw.getJta().repaint();
			cw.validate();
			cw.repaint();
			cw.addNewPrompt();
			
		
		
		return "";
	}

	public CommandWindow getCw() {
		return cw;
	}

	public void setCw(CommandWindow cw) {
		this.cw = cw;
	}

	

}
