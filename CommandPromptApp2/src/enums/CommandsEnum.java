package enums;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import views.CommandWindow;


public enum CommandsEnum {
	
	
	CLEAR {
	
		
		
	},

	DB4O{
		
		
		
	};
	
	
	
	
	
	private CommandWindow cw;
	
	
	public void getCommand(CommandWindow cw, String command){
		
		try {
			Method m = cw.getClass().getMethod("processCommand_" + command, null);
			m.invoke(cw, null);
		
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public CommandWindow getCw() {
		return cw;
	}

	public void setCw(CommandWindow cw) {
		this.cw = cw;
	}

}
