package helpers;

import java.util.*;
import java.io.*;

import javax.swing.JTextArea;

public class StreamGobbler extends Thread {
	InputStream is;
	JTextArea jta;

	public StreamGobbler(InputStream is, JTextArea jta) {
		this.is = is;
		this.jta = jta;

	}

	public void run() {
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null)
				jta.insert(line, jta.getText().length());
			jta.append("\n");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				isr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}