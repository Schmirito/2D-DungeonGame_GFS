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
	 * @param keyH   Der KeyHandler wird übergeben, um die Performance zu
	 *               verbessern.
	 * @param player Der Spieler wird übergeben, um die Performance zu verbessern.
	 */
	public Kamera(GamePanel gp, KeyHandler keyH, Player player) {
		this.gp = gp;
		this.keyH = keyH;
		this.player = player;
		bildschirmX = gp.BildBreite / 2;
		bildschirmY = gp.BildHoehe / 2;
		weltX = player.weltX + gp.feldGroeße * 4;
		weltY = player.weltY;

	}

	/**
	 * Die Methode bewegt die Kamera, außer der Spieler kollidiert, die Kamera stößt
	 * am Rand an oder der Spieler ist noch nicht wieder mittig im Bildschirm
	 * platziert. Die Map wird in Referenz zur Kamera gerendert, die Kamera ist
	 * immer in der Mitte des Bildschirms.
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
					&& keyH.linksGedrückt && !keyH.obenGedrückt && !keyH.untenGedrückt) {

				weltX -= player.geschwindigkeit;

			} else if ((weltX + bildschirmX) < (gp.mapGroeße * gp.feldGroeße) && player.weltX >= bildschirmX
					&& keyH.rechtsGedrückt && !keyH.obenGedrückt && !keyH.untenGedrückt) {

				weltX += player.geschwindigkeit;

			}
		}

	}

	/**
	 * Methode, mit der eine Bewegung der Kamera bzw. der Map in die
	 * entgegengesetzte Richtung ausgeführt werden kann, wenn die kamera dadurch
	 * nicht am Rand anstößt. Im Rahmen von Testungen zum Rückstoßen des Spieler
	 * entstanden, aktuell nicht in Verwendung.
	 * 
	 * @param bewegung Die Weite der Bewegung die ausgeführt werden soll.
	 */
	public void versucheBewegung(int bewegung) {
		String richtung = player.stoßRichtung;
		if (true) {
			if ((weltY - bildschirmY) > 0 && player.weltY <= (gp.mapGroeße * gp.feldGroeße) - bildschirmY
					&& richtung.equals("oben")) {

				weltY -= bewegung;

			} else if ((weltY + bildschirmY) < (gp.mapGroeße * gp.feldGroeße) && player.weltY >= bildschirmY
					&& richtung.equals("unten")) {

				weltY += bewegung;

			} else if ((weltX - bildschirmX) > 0 && player.weltX <= (gp.mapGroeße * gp.feldGroeße) - bildschirmX
					&& richtung.equals("links")) {

				weltX -= bewegung;

			} else if ((weltX + bildschirmX) < (gp.mapGroeße * gp.feldGroeße) && player.weltX >= bildschirmX
					&& richtung.equals("rechts")) {

				weltX += bewegung;

			}
		}

	}

}
