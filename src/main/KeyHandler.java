package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entity.Player;

public class KeyHandler implements KeyListener {

	public boolean obenGedr�ckt, untenGedr�ckt, linksGedr�ckt, rechtsGedr�ckt, pfeilHochGedr�ckt, pfeilRunterGedr�ckt,
			pfeilLinksGedr�ckt, pfeilRechtsGedr�ckt, hGedr�ckt, escGedrueckt, enterGedrueckt;

	@Override
	public void keyTyped(KeyEvent e) {

	}

	/**Sobald eine Taste gedr�ckt ist, wird die jeweils zugeh�rige variable auf true gesetzt. 
	 * @param KeyEvent
	 * */
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
		if (tastenCode == KeyEvent.VK_UP) {
			pfeilHochGedr�ckt = true;
		}
		if (tastenCode == KeyEvent.VK_DOWN) {
			pfeilRunterGedr�ckt = true;
		}
		if (tastenCode == KeyEvent.VK_LEFT) {
			pfeilLinksGedr�ckt = true;
		}
		if (tastenCode == KeyEvent.VK_RIGHT) {
			pfeilRechtsGedr�ckt = true;
		}
		if (tastenCode == KeyEvent.VK_H) {
			hGedr�ckt = true;
		}
		if (tastenCode == KeyEvent.VK_ESCAPE) {
			escGedrueckt = true;
		}
		if (tastenCode == KeyEvent.VK_ENTER) {
			enterGedrueckt = true;
		}

	}

	/**Sobald eine Taste losgelassen wird, dann wird die jeweils zugeh�rige variable auf false gesetzt. 
	 * @param KeyEvent
	 * */
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
		if (tastenCode == KeyEvent.VK_UP) {
			pfeilHochGedr�ckt = false;
		}
		if (tastenCode == KeyEvent.VK_DOWN) {
			pfeilRunterGedr�ckt = false;
		}
		if (tastenCode == KeyEvent.VK_LEFT) {
			pfeilLinksGedr�ckt = false;
		}
		if (tastenCode == KeyEvent.VK_RIGHT) {
			pfeilRechtsGedr�ckt = false;
		}
		if (tastenCode == KeyEvent.VK_H) {
			hGedr�ckt = false;
		}
		if (tastenCode == KeyEvent.VK_ESCAPE) {
			escGedrueckt = false;
		}
		if (tastenCode == KeyEvent.VK_ENTER) {
			enterGedrueckt = false;
		}

	}

}
