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
		weltX = player.weltX+gp.feldGroeße*4;
		weltY = player.weltY;
		
	}
	/* Die Methode bewegt die Kamera, außer der Spieler kollidiert, die Kamera stößt am Rand an oder der Spieler ist noch nicht wieder mittig im Bildschirm platziert. 
	 * 
	 */
	public void update() {
		if (gp.player.kollidiert == false) {
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
		}

	}
	
	public void versucheBewegung(int bewegung) {
		if (gp.player.kollidiert == false) {
			if ((weltY - bildschirmY) > 0 && player.weltY <= (gp.mapGroeße * gp.feldGroeße) - bildschirmY
					&& keyH.obenGedrückt) {

				weltY -= bewegung;

			} else if ((weltY + bildschirmY) < (gp.mapGroeße * gp.feldGroeße) && player.weltY >= bildschirmY
					&& keyH.untenGedrückt) {

				weltY += bewegung;

			} else if ((weltX - bildschirmX) > 0 && player.weltX <= (gp.mapGroeße * gp.feldGroeße) - bildschirmX
					&& keyH.linksGedrückt && !keyH.obenGedrückt && !keyH.untenGedrückt ) {

				weltX -= bewegung;

			} else if ((weltX + bildschirmX) < (gp.mapGroeße * gp.feldGroeße) && player.weltX >= bildschirmX
					&& keyH.rechtsGedrückt && !keyH.obenGedrückt && !keyH.untenGedrückt) {

				weltX += bewegung;

			}
		}

	}

}
