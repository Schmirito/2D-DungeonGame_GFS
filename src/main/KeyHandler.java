package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*Der KeyHandler lie�t Tastendr�cke und wertet diese aus.
 *Somit kann man auf Tastendr�cke mit einer aktion reagieren wie zum Beispiel Laufen*/
public class KeyHandler implements KeyListener{

	/*booleans werden f�r die jeweiligen richtungen oder Tastendr�cke erstellt.*/
	public boolean obenGedr�ckt,untenGedr�ckt,linksGedr�ckt,rechtsGedr�ckt;
	
	
	/*Bisher nicht benutzt, wenn Taste nur kurz gedr�ckt.*/
	@Override
	public void keyTyped(KeyEvent e) {	
	}

	/*Wenn eine Taste gedr�ckt wurde (gehalten) dann soll das passieren:*/
	@Override
	public void keyPressed(KeyEvent e) {
		
		/*Es wird eine nummer in dem integer gespeichert welche assoziiert mit einer bestimmten taste ist.
		 *Zum Beispiel: bei Enter -> 10, bei A = 65 usw..*/
		int tastenCode = e.getKeyCode();
		
		/*Wenn die Nummer der eben gedr�ckten Taste auf die Nummer von z.b.: "W" zutrifft, dann mache das usw..*/
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
	/*Wenn eine Taste losgelassen wurde, dann soll das passieren:*/
	@Override
	public void keyReleased(KeyEvent e) {
		
		/*Es wird eine nummer in dem integer gespeichert welche assoziiert mit einer bestimmten taste ist.
		 *Zum Beispiel: bei Enter -> 10, bei A = 65 usw..*/
		int tastenCode = e.getKeyCode();
		
		/*Wenn die Nummer der eben losgelassenen Taste auf die Nummer von z.b.: "W" zutrifft, dann mache das usw..*/
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
