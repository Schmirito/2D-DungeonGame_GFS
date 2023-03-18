package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	public boolean obenGedr�ckt,untenGedr�ckt,linksGedr�ckt,rechtsGedr�ckt;
	
	@Override
	public void keyTyped(KeyEvent e) {	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int tastenCode = e.getKeyCode();
		
		if (tastenCode == KeyEvent.VK_W) {
			obenGedr�ckt = true;
		}
		if (tastenCode == KeyEvent.VK_A) {
			linksGedr�ckt = true;
		}
		if (tastenCode == KeyEvent.VK_S) {
			untenGedr�ckt = true;
		}
		if (tastenCode == KeyEvent.VK_D) {
			rechtsGedr�ckt = true;
		}
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
		int tastenCode = e.getKeyCode();
		
		if (tastenCode == KeyEvent.VK_W) {
			obenGedr�ckt = false;
		}
		if (tastenCode == KeyEvent.VK_A) {
			linksGedr�ckt = false;
		}
		if (tastenCode == KeyEvent.VK_S) {
			untenGedr�ckt = false;
		}
		if (tastenCode == KeyEvent.VK_D) {
			rechtsGedr�ckt = false;
		}
		
	}

}
