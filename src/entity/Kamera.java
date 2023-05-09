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
		weltX = player.weltX+gp.feldGroe�e*4;
		weltY = player.weltY;
		
	}
	/* Die Methode bewegt die Kamera, au�er der Spieler kollidiert, die Kamera st��t am Rand an oder der Spieler ist noch nicht wieder mittig im Bildschirm platziert. 
	 * 
	 */
	public void update() {
		if (gp.player.kollidiert == false) {
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
		}

	}
	
	public void versucheBewegung(int bewegung) {
		if (gp.player.kollidiert == false) {
			if ((weltY - bildschirmY) > 0 && player.weltY <= (gp.mapGroe�e * gp.feldGroe�e) - bildschirmY
					&& keyH.obenGedr�ckt) {

				weltY -= bewegung;

			} else if ((weltY + bildschirmY) < (gp.mapGroe�e * gp.feldGroe�e) && player.weltY >= bildschirmY
					&& keyH.untenGedr�ckt) {

				weltY += bewegung;

			} else if ((weltX - bildschirmX) > 0 && player.weltX <= (gp.mapGroe�e * gp.feldGroe�e) - bildschirmX
					&& keyH.linksGedr�ckt && !keyH.obenGedr�ckt && !keyH.untenGedr�ckt ) {

				weltX -= bewegung;

			} else if ((weltX + bildschirmX) < (gp.mapGroe�e * gp.feldGroe�e) && player.weltX >= bildschirmX
					&& keyH.rechtsGedr�ckt && !keyH.obenGedr�ckt && !keyH.untenGedr�ckt) {

				weltX += bewegung;

			}
		}

	}

}
