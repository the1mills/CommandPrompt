package start;

import views.CommandWindow;


public class StartCommand {

	public StartCommand() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		String frameTitle = "";

		if (args.length > 0) {
			frameTitle = args[0];
		}

		CommandWindow cw = new CommandWindow();
		cw.setTitle(frameTitle);
		cw.setVisible(true);

	}

}
