package command;

import java.util.List;

import models.ModelClass;
import views.CommandWindow;

import commands.Interfaces.ICommand;

public class Command_DB4O implements ICommand {

	private CommandWindow cw;
	
	public Command_DB4O(CommandWindow cw) {
		
		this.cw = cw;
	}

	@Override
	public String execute(List<String> command) {
		
		if(command.size() < 2){
			return "No type selected to query.";
		}
		
		Class<?> c = null;
		ModelClass o = null;
		try {
		     String modelClassName = command.get(1);
			 c = Class.forName("models." + modelClassName);
			 o = (ModelClass) c.newInstance();
			 String s = o.killMonkey();
			 cw.getJta().insert(s, cw.getJta().getText().length());
			 cw.getJta().append("\n");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return e.getMessage() + e.getLocalizedMessage();
		} catch (InstantiationException e) {
			e.printStackTrace();
			return e.getMessage() + e.getLocalizedMessage();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return e.getMessage() + e.getLocalizedMessage();
		} catch(Exception e){
			e.printStackTrace();
			return e.getMessage() + e.getLocalizedMessage();
		}
		
		return "Completed";
	}
}
