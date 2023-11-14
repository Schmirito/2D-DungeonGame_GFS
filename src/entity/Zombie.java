package entity;

import main.GamePanel;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Zombie extends Entity {

	int diffSpielerX;
	int diffSpielerY;
	String richtungX, richtungY;
	int framesUnbewegt = 0;
	int zaeler = 0;

	/**
	 * Konstruktor f�r Zombie. Geht bidirektionale Beziehung mit GamePanel ein. Weltposition und weitere Variablen wie die der Hitbox werden initialisiert.
	 * @param gp Das GamePanel.
	 * @param weltX Die X-Koordinaate auf der Map.
	 * @param weltY Die Y-Koordinate auf der Map.
	 */
	public Zombie(GamePanel gp, int weltX, int weltY) {
		super(gp);
		this.weltX = weltX;
		this.weltY = weltY;
		hitBox = new Rectangle();
		hitBox.x = gp.feldGroesse / 4;
		hitBox.y = gp.feldGroesse / 2;
		hitBox.height = gp.feldGroesse / 2;
		hitBox.width = gp.feldGroesse / 2;

		richtung = "unten";
		geschwindigkeit = gp.skala;

		getImage();
	}
	
	/**Update-Methode von Zombies. Die richtung in welche diese schauen wird bestimmt.
	 * Die Kollision mit Objekten, Entities und dem Spieler wird �berpr�ft.
	 * Die Methode laufeZumSpieler() wird aufgerufen.
	 * Die bewegungen des Zombies werden bestimmt.
	 * Der Zombie schl�gt nach einiger Zeit den Spieler mithilfe der Methode schlageSpieler(). */
	public void update() {
		diffSpielerX = weltX - gp.player.weltX;
		diffSpielerY = weltY - gp.player.weltY;

		if (diffSpielerX <= 0) {
			richtungX = "rechts";
			diffSpielerX = gp.player.weltX - weltX;
		} else {
			richtungX = "links";
		}

		if (diffSpielerY <= 0) {
			richtungY = "unten";
			diffSpielerY = gp.player.weltY - weltY;
		} else {
			richtungY = "oben";
		}

		if (diffSpielerX > diffSpielerY) {
			richtung = richtungX;
		} else if (diffSpielerY > diffSpielerX) {
			richtung = richtungY;
		}

		kollidiert = false;

		// PRUEFE FELD KOLLISION
		gp.kPruefer.pruefeFeld(this);
		// PRUEFE OBJEKT KOLLISION
		boolean objGetroffen[] = gp.kPruefer.pruefeObjekt(this, true);
		interagiereMitObjekt(objGetroffen);
		// PRUEFE ENTITY KOLLISION
		gp.kPruefer.pruefeEntity(this);
		// PRUEFE PLAYER KOLLISION
		gp.kPruefer.pruefePlayer(this);

		laufeZumSpieler();

		// MOVEMENT
		if (rundenAnzahlGetroffen > 0) {
			rundenAnzahlGetroffen--;
			switch (stossRichtung) {
			case "oben":

				if (gp.kPruefer.pruefeFeldOnlyRueckgabe(this, stossRichtung,
						gp.skala * entityGetroffen.rueckstoss / rundenMaxAnzahlgetroffen) == false
						&& gp.kPruefer.pruefeFeldOnlyRueckgabe(this, stossRichtung,
								gp.skala * entityGetroffen.rueckstoss / rundenMaxAnzahlgetroffen) == false
						&& gp.kPruefer.pruefeObjektOnlyKollidiert(this, stossRichtung) == false) {
					weltY -= gp.skala * entityGetroffen.rueckstoss / rundenMaxAnzahlgetroffen;

				}
				break;

			case "unten":

				if (gp.kPruefer.pruefeFeldOnlyRueckgabe(this, stossRichtung,
						gp.skala * entityGetroffen.rueckstoss / rundenMaxAnzahlgetroffen) == false
						&& gp.kPruefer.pruefeFeldOnlyRueckgabe(this, stossRichtung,
								gp.skala * entityGetroffen.rueckstoss/ rundenMaxAnzahlgetroffen) == false
						&& gp.kPruefer.pruefeObjektOnlyKollidiert(this, stossRichtung) == false) {
					weltY += gp.skala * entityGetroffen.rueckstoss / rundenMaxAnzahlgetroffen;

				}
				break;

			case "links":

				if (gp.kPruefer.pruefeFeldOnlyRueckgabe(this, stossRichtung,
						gp.skala * entityGetroffen.rueckstoss / rundenMaxAnzahlgetroffen) == false
						&& gp.kPruefer.pruefeFeldOnlyRueckgabe(this, stossRichtung,
								gp.skala * entityGetroffen.rueckstoss / rundenMaxAnzahlgetroffen) == false
						&& gp.kPruefer.pruefeObjektOnlyKollidiert(this, stossRichtung) == false) {
					weltX -= gp.skala * entityGetroffen.rueckstoss / rundenMaxAnzahlgetroffen;

				}
				break;

			case "rechts":

				if (gp.kPruefer.pruefeFeldOnlyRueckgabe(this, stossRichtung,
						gp.skala * entityGetroffen.rueckstoss / rundenMaxAnzahlgetroffen) == false
						&& gp.kPruefer.pruefeFeldOnlyRueckgabe(this, stossRichtung,
								gp.skala * entityGetroffen.rueckstoss / rundenMaxAnzahlgetroffen) == false
						&& gp.kPruefer.pruefeObjektOnlyKollidiert(this, stossRichtung) == false) {
					weltX += gp.skala * entityGetroffen.rueckstoss / rundenMaxAnzahlgetroffen;

				}
				break;

			}
		} else {

		}
		if (diffSpielerX <= gp.feldGroesse && diffSpielerY <= gp.feldGroesse) {
			zaeler++;
			if (zaeler == schlageSpielerCooldownSec * 60) {
				zaeler = 0;
				schlageSpieler();
			}
		} else {
			zaeler = 0;
		}

	}

	/**Wird in der Update-Methode aufgerufen.
	 * Es wird �berpr�ft, ob die X oder Y differenz zwischen dem Spieler und dem Zombie gr��er ist.
	 * Der Zombie bewegt sich in die jeweils gr��ere Koordinaten Differenz und kommt somit zum Spieler.
	 */
	public void laufeZumSpieler() {
		diffSpielerX = weltX - gp.player.weltX;
		diffSpielerY = weltY - gp.player.weltY;

		if (diffSpielerX <= 0) {
			richtungX = "rechts";
			diffSpielerX = gp.player.weltX - weltX;
		} else {
			richtungX = "links";
		}

		if (diffSpielerY <= 0) {
			richtungY = "unten";
			diffSpielerY = gp.player.weltY - weltY;
		} else {
			richtungY = "oben";
		}

		if (diffSpielerX > diffSpielerY) {
			richtung = richtungX;
		} else if (diffSpielerY > diffSpielerX) {
			richtung = richtungY;
		}

		if (kollidiert == false) {
			switch (richtung) {
			case "oben":
				weltY -= geschwindigkeit;
				break;
			case "unten":
				weltY += geschwindigkeit;
				break;
			case "links":
				weltX -= geschwindigkeit;
				break;
			case "rechts":
				weltX += geschwindigkeit;
				break;
			}
			framesUnbewegt=0;
			frameCounter++;
		} else {
			framesUnbewegt++;
			frameCounter = 0;
		}

		if (frameCounter > 8) {
			spriteNumber++;
			if (spriteNumber >= 4) {
				spriteNumber = 0;
			}
			frameCounter = 0;
		}
		if (framesUnbewegt >= 16) {
			spriteNumber = 0;
		}


	}

	/**
	 * Wird von der Update-Methode aufgerufen. Verringert die Leben des Spielers.
	 */
	public void schlageSpieler() {
		gp.player.leben -= gp.skala;
	}

	/**
	 * Die Bilder zur Bewegungsanimation werden aus dem res-Ordner mithilfe der Setup-Methode in deren Variablen geladen.
	 */
	public void getImage() {

		up = setup("/zombie/zombie-Up");
		upLV = setup("/zombie/zombie-UpLV");
		upRV = setup("/zombie/zombie-UpRV");
		down = setup("/zombie/zombie-Down");
		downLV = setup("/zombie/zombie-DownLV");
		downRV = setup("/zombie/zombie-DownRV");
		left = setup("/zombie/zombie-Left");
		leftLV = setup("/zombie/zombie-LeftLV");
		leftRV = setup("/zombie/zombie-LeftRV");
		right = setup("/zombie/zombie-Right");
		rightLV = setup("/zombie/zombie-RightLV");
		rightRV = setup("/zombie/zombie-RightRV");

	}
}
