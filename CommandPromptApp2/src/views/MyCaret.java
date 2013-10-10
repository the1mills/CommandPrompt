package views;

import java.awt.Graphics;
import java.awt.Point;

import javax.swing.event.ChangeListener;
import javax.swing.text.Caret;
import javax.swing.text.JTextComponent;

public class MyCaret implements Caret {

	public MyCaret() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addChangeListener(ChangeListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deinstall(JTextComponent c) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getBlinkRate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDot() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Point getMagicCaretPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMark() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void install(JTextComponent c) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isSelectionVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isVisible() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void moveDot(int dot) {
		// TODO Auto-generated method stub

	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeChangeListener(ChangeListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setBlinkRate(int rate) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setDot(int dot) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMagicCaretPosition(Point p) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSelectionVisible(boolean v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setVisible(boolean v) {
		// TODO Auto-generated method stub

	}

}
