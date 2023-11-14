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

	/**
	 * Konstruktor von Kamera. Der Konstruktor der Superclass wird aufgerufen und
	 * einige Variablen initialisiert. Bidirektionale Beziehung mit GamePanel.
	 * Unidirektionale beziehung mit KeyHandler und Spieler.
	 * 
	 * @param gp     Das GamePanel, damit wird eine bidirektionale Beziehung
	 *               erstellt.
	 * @param keyH   Der KeyHandler wird �bergeben, um die Performance zu
	 *               verbessern.
	 * @param player Der Spieler wird �bergeben, um die Performance zu verbessern.
	 */
	public Kamera(GamePanel gp, KeyHandler keyH, Player player) {
		this.gp = gp;
		this.keyH = keyH;
		this.player = player;
		bildschirmX = gp.BildBreite / 2;
		bildschirmY = gp.BildHoehe / 2;
		weltX = player.weltX + gp.feldGroesse * 4;
		weltY = player.weltY;
		player.kamera = this;
	}

	/**
	 * Die Methode bewegt die Kamera, au�er der Spieler kollidiert, die Kamera st��t
	 * am Rand an oder der Spieler ist noch nicht wieder mittig im Bildschirm
	 * platziert. Die Map wird in Referenz zur Kamera gerendert, die Kamera ist
	 * immer in der Mitte des Bildschirms.
	 * 
	 */
	public void update() {
		if (gp.player.kollidiert == false) {
			if ((weltY - bildschirmY) > 0 && player.weltY <= (gp.mapGroesse * gp.feldGroesse) - bildschirmY
					&& keyH.obenGedrueckt) {

				weltY -= player.geschwindigkeit;

			} else if ((weltY + bildschirmY) < (gp.mapGroesse * gp.feldGroesse) && player.weltY >= bildschirmY
					&& keyH.untenGedrueckt) {

				weltY += player.geschwindigkeit;

			} else if ((weltX - bildschirmX) > 0 && player.weltX <= (gp.mapGroesse * gp.feldGroesse) - bildschirmX
					&& keyH.linksGedrueckt && !keyH.obenGedrueckt && !keyH.untenGedrueckt) {

				weltX -= player.geschwindigkeit;

			} else if ((weltX + bildschirmX) < (gp.mapGroesse * gp.feldGroesse) && player.weltX >= bildschirmX
					&& keyH.rechtsGedrueckt && !keyH.obenGedrueckt && !keyH.untenGedrueckt) {

				weltX += player.geschwindigkeit;

			}
		}

	}

	/**
	 * Methode, mit der eine Bewegung der Kamera bzw. der Map in die
	 * entgegengesetzte Richtung ausgef�hrt werden kann, wenn die kamera dadurch
	 * nicht am Rand anst��t. Im Rahmen von Testungen zum R�cksto�en des Spieler
	 * entstanden, aktuell nicht in Verwendung.
	 * 
	 * @param bewegung Die Weite der Bewegung die ausgef�hrt werden soll.
	 */
	public void versucheBewegung(int bewegung) {
		String richtung = player.stossRichtung;
		if (true) {
			if ((weltY - bildschirmY) > 0 && player.weltY <= (gp.mapGroesse * gp.feldGroesse) - bildschirmY
					&& richtung.equals("oben")) {

				weltY -= bewegung;

			} else if ((weltY + bildschirmY) < (gp.mapGroesse * gp.feldGroesse) && player.weltY >= bildschirmY
					&& richtung.equals("unten")) {

				weltY += bewegung;

			} else if ((weltX - bildschirmX) > 0 && player.weltX <= (gp.mapGroesse * gp.feldGroesse) - bildschirmX
					&& richtung.equals("links")) {

				weltX -= bewegung;

			} else if ((weltX + bildschirmX) < (gp.mapGroesse * gp.feldGroesse) && player.weltX >= bildschirmX
					&& richtung.equals("rechts")) {

				weltX += bewegung;

			}
		}

	}

}
