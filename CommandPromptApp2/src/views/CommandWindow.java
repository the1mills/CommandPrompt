package views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

import commands.Interfaces.ICommand;

import models.ModelClass;


public class CommandWindow extends JFrame implements KeyListener {

	private JPanel contentPanel = new JPanel();
	private JScrollPane js = null;
	private JTextArea jta = new JTextArea();
	private Process commandProcess = null;
	private JProgressBar jpb = new JProgressBar();
	private int promptPosition;
	

	public CommandWindow() {

		jta.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "do nothing at all");
		jta.setLineWrap(true);
		((AbstractDocument) jta.getDocument())
				.setDocumentFilter(new DocFilter());
		this.setContentPane(contentPanel);
		contentPanel.setLayout(new BorderLayout());
		contentPanel.add(js = new JScrollPane(jta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	            JScrollPane.HORIZONTAL_SCROLLBAR_NEVER), BorderLayout.CENTER);
		contentPanel.add(jpb, BorderLayout.SOUTH);
		jta.addKeyListener(this);
		addNewPrompt();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(new Dimension(1000,700));
		this.setLocationRelativeTo(null);
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {

		if (arg0.getKeyChar() == KeyEvent.VK_ENTER) {

			System.out.println("enter");

			if (commandProcess == null) {

				String fullCommandText = getNextCommandString();

				if (fullCommandText == null) {
					return;
				}

				jta.getCaret().setVisible(false);
				jta.setEditable(false);
				jpb.setIndeterminate(true);

				runTheNextCommand(fullCommandText);
			} else {
				System.out
						.println("command process was not null...have to wait");
			}
		}

	}

	private void runTheNextCommand(String commandText) {
		try {
			
			jta.append("\n");

		//	commandProcess = Runtime.getRuntime().exec(commandText);
			
			List<String> command = new ArrayList<String>();
			
			StringTokenizer st = new StringTokenizer(commandText);
			
			while(st.hasMoreTokens()){
				 command.add(st.nextToken());
			}
			processCommand(command);
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			jpb.setIndeterminate(false);
			commandProcess = null;
			addNewPrompt();
			jta.getCaret().setVisible(true);
			jta.setEditable(true);
		}

	}
	
	private void processCommand(List<String> command) {
		
//		if(command.get(0).equalsIgnoreCase("CLEAR")){
//			
//			processClearCommand(command);
//		}
//		
//		else if(command.get(0).equalsIgnoreCase("DB4O")){
//			
//			processDB4OCommand(command);
//		}
		
		Class<ICommand> ic = null;
		try {
			ic = (Class<ICommand>) Class.forName("command.Command_" + command.get(0).toUpperCase());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(ic != null){
		
			try {
				ICommand commandInvoker = ic.getConstructor(CommandWindow.class).newInstance(this);
				jta.append(commandInvoker.execute(command));
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else{
		
		processGenericCommand(command);
		
		}
	}

	private void processClearCommand(List<String> command) {
		
		if(command.size() < 2){
			
		((AbstractDocument) jta.getDocument())
			.setDocumentFilter(null);
		jta.setText("");
		((AbstractDocument) jta.getDocument())
		.setDocumentFilter(new DocFilter());
		jta.repaint();
		this.validate();
		this.repaint();
		addNewPrompt();
		}
		
	}

	private void processDB4OCommand(List<String> command) {
		
//		Class<?> z1 = null;
//		
//		try {
//			Object z = z1.getDeclaredConstructor(String.class).newInstance("7");
//		} catch (IllegalArgumentException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (SecurityException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (InstantiationException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (IllegalAccessException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (InvocationTargetException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (NoSuchMethodException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		Class<?> c = null;
		ModelClass o = null;
		try {
		String modelClassName = command.get(1);
		
		
			 c = Class.forName("models." + modelClassName);
			 o = (ModelClass) c.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			jta.insert(e.getMessage(), jta.getText().length());
		} catch (InstantiationException e) {
			e.printStackTrace();
			jta.insert(e.getMessage(), jta.getText().length());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			jta.insert(e.getMessage(), jta.getText().length());
		}
		
		
		String s = o.killMonkey();
		jta.insert(s, jta.getText().length());
		jta.append("\n");
		
	}

	private void processGenericCommand(List<String> command) {
		

		try{
		ProcessBuilder builder = new ProcessBuilder(command);
//		builder.directory(arg0);
		builder.redirectErrorStream(true);
		commandProcess = builder.start();

//
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(
				commandProcess.getInputStream()));
		
//		BufferedReader stdInput = new BufferedReader(new Reader(
//				commandProcess.getOutputStream()));

//		BufferedReader stdError = new BufferedReader(new InputStreamReader(
//				commandProcess.getErrorStream()));
		
		// any output?
//        StreamGobbler outputGobbler = new 
//            StreamGobbler(commandProcess.getInputStream(),jta);
//            
//        outputGobbler.start();
        
		String inputLine = "";

		while ((inputLine = stdInput.readLine()) != null) {
			System.out.println(inputLine);
			jta.insert(inputLine, jta.getText().length());
			jta.append("\n");
		}
		
		stdInput.close();
		
		int exitVal = -1;
		try {
			 exitVal = 	commandProcess.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//int exitVal = commandProcess.exitValue();
      System.out.println("Process exitValue: " + exitVal);
		

//		String sError = "";
//
//		while ((sError = stdError.readLine()) != null) {
//			System.out.println(sError);
//			jta.insert(sError, jta.getText().length());
//			jta.append("\n");
//		}

	} catch (IOException e) {

		e.printStackTrace();
		jta.insert(e.getMessage(), jta.getText().length());
		// jta.insert(e.getCause().getMessage(), jta.getText().length());
	} finally {

//		jpb.setIndeterminate(false);
//		commandProcess = null;
//		addNewPrompt();
//		jta.getCaret().setVisible(true);
//		jta.setEditable(true);
	}		
}

	public void addNewPrompt() {

		jta.append("\n\n");
		jta.append(">>>");
		promptPosition = jta.getText().length();
		jta.setCaretPosition(promptPosition);
	}

	public String getNextCommandString() {

		String command = "";
		int startPos = promptPosition;
		int endPos = jta.getText().length();

		if (endPos - startPos < 1) {
			// so there was an empty command...
			return null;
		}

		try {
			command = jta.getText(startPos, endPos - startPos).trim();
			if(command.length() < 1){
				return null;
			}
		} catch (BadLocationException e) {
			e.printStackTrace();
		}

		return command;
	}

	public Process getCommandProcess() {
		return commandProcess;
	}

	public void setCommandProcess(Process commandProcess) {
		this.commandProcess = commandProcess;
	}

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public void setContentPanel(JPanel contentPanel) {
		this.contentPanel = contentPanel;
	}

	public JProgressBar getJpb() {
		return jpb;
	}

	public void setJpb(JProgressBar jpb) {
		this.jpb = jpb;
	}

	public JScrollPane getJs() {
		return js;
	}

	public JTextArea getJta() {
		return jta;
	}

	public void setJta(JTextArea jta) {
		this.jta = jta;
	}

	public int getPromptPosition() {
		return promptPosition;
	}

	public void setPromptPosition(int promptPosition) {
		this.promptPosition = promptPosition;
	}

	public void setJs(JScrollPane js) {
		this.js = js;
	}

	public void addNewDocFilter(){
		((AbstractDocument) jta.getDocument())
		.setDocumentFilter(new DocFilter());
	}
	
	private class DocFilter extends DocumentFilter {

		public void insertString(final FilterBypass fb, final int offset,
				final String string, final AttributeSet attr)
				throws BadLocationException {
			if (offset >= promptPosition) {
				super.insertString(fb, offset, string, attr);
			}
		}

		public void remove(final FilterBypass fb, final int offset,
				final int length) throws BadLocationException {
			if (offset >= promptPosition) {
				super.remove(fb, offset, length);
			}
		}

		public void replace(final FilterBypass fb, final int offset,
				final int length, final String text, final AttributeSet attrs)
				throws BadLocationException {
			if (offset >= promptPosition) {
				super.replace(fb, offset, length, text, attrs);
			}
		}
	}

}
