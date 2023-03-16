package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*Der KeyHandler ließt Tastendrücke und wertet diese aus.
 *Somit kann man auf Tastendrücke mit einer aktion reagieren wie zum Beispiel Laufen*/
public class KeyHandler implements KeyListener{

	/*booleans werden für die jeweiligen richtungen oder Tastendrücke erstellt.*/
	public boolean obenGedrückt,untenGedrückt,linksGedrückt,rechtsGedrückt;
	
	
	/*Bisher nicht benutzt, wenn Taste nur kurz gedrückt.*/
	@Override
	public void keyTyped(KeyEvent e) {	
	}

	/*Wenn eine Taste gedrückt wurde (gehalten) dann soll das passieren:*/
	@Override
	public void keyPressed(KeyEvent e) {
		
		/*Es wird eine nummer in dem integer gespeichert welche assoziiert mit einer bestimmten taste ist.
		 *Zum Beispiel: bei Enter -> 10, bei A = 65 usw..*/
		int tastenCode = e.getKeyCode();
		
		/*Wenn die Nummer der eben gedrückten Taste auf die Nummer von z.b.: "W" zutrifft, dann mache das usw..*/
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
	/*Wenn eine Taste losgelassen wurde, dann soll das passieren:*/
	@Override
	public void keyReleased(KeyEvent e) {
		
		/*Es wird eine nummer in dem integer gespeichert welche assoziiert mit einer bestimmten taste ist.
		 *Zum Beispiel: bei Enter -> 10, bei A = 65 usw..*/
		int tastenCode = e.getKeyCode();
		
		/*Wenn die Nummer der eben losgelassenen Taste auf die Nummer von z.b.: "W" zutrifft, dann mache das usw..*/
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
