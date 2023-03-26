package entity;

import main.GamePanel;
import main.KeyHandler;

public class Kamera {

	public int bildschirmX;
	public int bildschirmY;
	public int weltX;
	public int weltY;
	GamePanel gp;
	KeyHandler keyH;
	Player player;

	public Kamera(GamePanel gp, KeyHandler keyH, Player player) {
		this.gp = gp;
		this.keyH = keyH;
		this.player = player;
		bildschirmX = gp.BildBreite / 2;
		bildschirmY = gp.BildHoehe / 2;
		weltX = gp.BildBreite / 2;
		weltY = gp.BildHoehe / 2;

	}

	public void update() {
		/** Wenn die Kamera am Rand angekommen ist, bewegt sich der Spieler ohne die Kamera in die entsprechenden Richtungen,
		 * bis der Spieler auf der entsprechenden Achse wieder in der Mitte des Bildschirms ist
		 */

		if ((weltY - bildschirmY) > 0 && player.weltY <= (gp.mapGroe�e * gp.feldGroe�e) - bildschirmY
				&& keyH.obenGedr�ckt) {

			weltY -= player.geschwindigkeit;

		} else if ((weltY + bildschirmY) < (gp.mapGroe�e * gp.feldGroe�e) && player.weltY >= bildschirmY
				&& keyH.untenGedr�ckt) {

			weltY += player.geschwindigkeit;

		} else if ((weltX - bildschirmX) > 0 && player.weltX <= (gp.mapGroe�e * gp.feldGroe�e) - bildschirmX
				&& keyH.linksGedr�ckt && !keyH.obenGedr�ckt && !keyH.untenGedr�ckt ) {

			weltX -= player.geschwindigkeit;

		} else if ((weltX + bildschirmX) < (gp.mapGroe�e * gp.feldGroe�e) && player.weltX >= bildschirmX
				&& keyH.rechtsGedr�ckt && !keyH.obenGedr�ckt && !keyH.untenGedr�ckt) {

			weltX += player.geschwindigkeit;

		}

		/*
		 * if (keyH.obenGedr�ckt || keyH.untenGedr�ckt || keyH.linksGedr�ckt ||
		 * keyH.rechtsGedr�ckt) { if (keyH.obenGedr�ckt == true) { weltY -=
		 * player.geschwindigkeit; } else if (keyH.untenGedr�ckt) { weltY +=
		 * player.geschwindigkeit; } else if (keyH.linksGedr�ckt) { weltX -=
		 * player.geschwindigkeit; } else if (keyH.rechtsGedr�ckt) { weltX +=
		 * player.geschwindigkeit; } }
		 */
	}

}
