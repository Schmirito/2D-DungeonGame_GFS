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

		if ((weltY - bildschirmY) > 0 && player.weltY <= (gp.mapGroeße * gp.feldGroeße) - bildschirmY
				&& keyH.obenGedrückt) {

			weltY -= player.geschwindigkeit;

		} else if ((weltY + bildschirmY) < (gp.mapGroeße * gp.feldGroeße) && player.weltY >= bildschirmY
				&& keyH.untenGedrückt) {

			weltY += player.geschwindigkeit;

		} else if ((weltX - bildschirmX) > 0 && player.weltX <= (gp.mapGroeße * gp.feldGroeße) - bildschirmX
				&& keyH.linksGedrückt && !keyH.obenGedrückt && !keyH.untenGedrückt ) {

			weltX -= player.geschwindigkeit;

		} else if ((weltX + bildschirmX) < (gp.mapGroeße * gp.feldGroeße) && player.weltX >= bildschirmX
				&& keyH.rechtsGedrückt && !keyH.obenGedrückt && !keyH.untenGedrückt) {

			weltX += player.geschwindigkeit;

		}

		/*
		 * if (keyH.obenGedrückt || keyH.untenGedrückt || keyH.linksGedrückt ||
		 * keyH.rechtsGedrückt) { if (keyH.obenGedrückt == true) { weltY -=
		 * player.geschwindigkeit; } else if (keyH.untenGedrückt) { weltY +=
		 * player.geschwindigkeit; } else if (keyH.linksGedrückt) { weltX -=
		 * player.geschwindigkeit; } else if (keyH.rechtsGedrückt) { weltX +=
		 * player.geschwindigkeit; } }
		 */
	}

}
