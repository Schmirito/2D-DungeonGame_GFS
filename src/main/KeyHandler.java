package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	public boolean obenGedrückt,untenGedrückt,linksGedrückt,rechtsGedrückt;
	
	@Override
	public void keyTyped(KeyEvent e) {	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int tastenCode = e.getKeyCode();
		
		if (tastenCode == KeyEvent.VK_W) {
			obenGedrückt = true;
		}
		if (tastenCode == KeyEvent.VK_A) {
			linksGedrückt = true;
		}
		if (tastenCode == KeyEvent.VK_S) {
			untenGedrückt = true;
		}
		if (tastenCode == KeyEvent.VK_D) {
			rechtsGedrückt = true;
		}
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
		int tastenCode = e.getKeyCode();
		
		if (tastenCode == KeyEvent.VK_W) {
			obenGedrückt = false;
		}
		if (tastenCode == KeyEvent.VK_A) {
			linksGedrückt = false;
		}
		if (tastenCode == KeyEvent.VK_S) {
			untenGedrückt = false;
		}
		if (tastenCode == KeyEvent.VK_D) {
			rechtsGedrückt = false;
		}
		
	}

}
