package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entity.Player;

public class KeyHandler implements KeyListener {

	public boolean obenGedrückt, untenGedrückt, linksGedrückt, rechtsGedrückt, pfeilHochGedrückt, pfeilRunterGedrückt,
			pfeilLinksGedrückt, pfeilRechtsGedrückt, hGedrückt, escGedrueckt;

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
		if (tastenCode == KeyEvent.VK_UP) {
			pfeilHochGedrückt = true;
		}
		if (tastenCode == KeyEvent.VK_DOWN) {
			pfeilRunterGedrückt = true;
		}
		if (tastenCode == KeyEvent.VK_LEFT) {
			pfeilLinksGedrückt = true;
		}
		if (tastenCode == KeyEvent.VK_RIGHT) {
			pfeilRechtsGedrückt = true;
		}
		if (tastenCode == KeyEvent.VK_H) {
			hGedrückt = true;
		}
		if (tastenCode == KeyEvent.VK_ESCAPE) {
			escGedrueckt = true;
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
		if (tastenCode == KeyEvent.VK_UP) {
			pfeilHochGedrückt = false;
		}
		if (tastenCode == KeyEvent.VK_DOWN) {
			pfeilRunterGedrückt = false;
		}
		if (tastenCode == KeyEvent.VK_LEFT) {
			pfeilLinksGedrückt = false;
		}
		if (tastenCode == KeyEvent.VK_RIGHT) {
			pfeilRechtsGedrückt = false;
		}
		if (tastenCode == KeyEvent.VK_H) {
			hGedrückt = false;
		}
		if (tastenCode == KeyEvent.VK_ESCAPE) {
			escGedrueckt = false;
		}

	}

}
