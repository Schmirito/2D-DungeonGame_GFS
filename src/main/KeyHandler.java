package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entity.Player;

public class KeyHandler implements KeyListener {

	public boolean obenGedrueckt, untenGedrueckt, linksGedrueckt, rechtsGedrueckt, pfeilHochGedrueckt, pfeilRunterGedrueckt,
			pfeilLinksGedrueckt, pfeilRechtsGedrueckt, hGedrueckt, escGedrueckt, enterGedrueckt;

	@Override
	public void keyTyped(KeyEvent e) {

	}

	/**Sobald eine Taste gedr�ckt ist, so wird die jeweils zugeh�rige Variable auf "true" gesetzt. 
	 * @param e KeyEvent, enth�lt welche Tasten gedr�ckt sind.
	 * */
	@Override
	public void keyPressed(KeyEvent e) {

		int tastenCode = e.getKeyCode();

		if (tastenCode == KeyEvent.VK_W) {
			obenGedrueckt = true;
		}
		if (tastenCode == KeyEvent.VK_A) {
			linksGedrueckt = true;
		}
		if (tastenCode == KeyEvent.VK_S) {
			untenGedrueckt = true;
		}
		if (tastenCode == KeyEvent.VK_D) {
			rechtsGedrueckt = true;
		}
		if (tastenCode == KeyEvent.VK_UP) {
			pfeilHochGedrueckt = true;
		}
		if (tastenCode == KeyEvent.VK_DOWN) {
			pfeilRunterGedrueckt = true;
		}
		if (tastenCode == KeyEvent.VK_LEFT) {
			pfeilLinksGedrueckt = true;
		}
		if (tastenCode == KeyEvent.VK_RIGHT) {
			pfeilRechtsGedrueckt = true;
		}
		if (tastenCode == KeyEvent.VK_H) {
			hGedrueckt = true;
		}
		if (tastenCode == KeyEvent.VK_ESCAPE) {
			escGedrueckt = true;
		}
		if (tastenCode == KeyEvent.VK_ENTER) {
			enterGedrueckt = true;
		}

	}

	/**Sobald eine Taste losgelassen wird, so wird die jeweils zugeh�rige Variable auf "false" gesetzt. 
	 * @param e KeyEvent, enth�lt welche Tasten gedr�ckt sind.
	 * */
	@Override
	public void keyReleased(KeyEvent e) {

		int tastenCode = e.getKeyCode();

		if (tastenCode == KeyEvent.VK_W) {
			obenGedrueckt = false;
		}
		if (tastenCode == KeyEvent.VK_A) {
			linksGedrueckt = false;
		}
		if (tastenCode == KeyEvent.VK_S) {
			untenGedrueckt = false;
		}
		if (tastenCode == KeyEvent.VK_D) {
			rechtsGedrueckt = false;
		}
		if (tastenCode == KeyEvent.VK_UP) {
			pfeilHochGedrueckt = false;
		}
		if (tastenCode == KeyEvent.VK_DOWN) {
			pfeilRunterGedrueckt = false;
		}
		if (tastenCode == KeyEvent.VK_LEFT) {
			pfeilLinksGedrueckt = false;
		}
		if (tastenCode == KeyEvent.VK_RIGHT) {
			pfeilRechtsGedrueckt = false;
		}
		if (tastenCode == KeyEvent.VK_H) {
			hGedrueckt = false;
		}
		if (tastenCode == KeyEvent.VK_ESCAPE) {
			escGedrueckt = false;
		}
		if (tastenCode == KeyEvent.VK_ENTER) {
			enterGedrueckt = false;
		}

	}

}
